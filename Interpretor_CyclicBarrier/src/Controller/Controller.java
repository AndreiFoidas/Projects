package Controller;
import Model.ProgramState;
import Model.adt.MyStack;
import Model.statement.IStatement;
import Model.value.IValue;
import Model.value.ReferenceValue;
import Repository.IRepository;
import Exception.InterpretorException;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Controller {
    private final IRepository repository;
    private boolean display=true;
    private ExecutorService executor;
    private String description;
    private Boolean passedTypeCheck;

    //constructor
    public Controller(IRepository repository){
        this.repository=repository;
    }

    public Map<Integer, IValue> conservativeGarbageCollector(List<ProgramState> programStatesList, Map<Integer, IValue> heap){
        List<Integer> referenceAddresses = Stream.concat(
                getReferenceAddressesFromSymbolTables(programStatesList
                        .stream()
                        .map(programState -> programState.getSymbolTable().getValues())),
                getReferenceAddressesFromHeap(heap
                        .values()
                        .stream())
        ).collect(Collectors.toList());

        return heap
                .entrySet()
                .stream()
                .filter(element -> referenceAddresses.contains(element.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Stream<Integer> getReferenceAddressesFromSymbolTables(Stream<Collection<IValue>> symbolTableValuesList){
        return symbolTableValuesList
                .flatMap(Collection::stream)
                .filter(value -> value instanceof ReferenceValue)
                .map(value -> ((ReferenceValue) value).getAddress());
    }

    public Stream<Integer> getReferenceAddressesFromHeap(Stream<IValue> memoryHeapValues){
        return memoryHeapValues
                .filter(value -> value instanceof ReferenceValue)
                .map(value -> ((ReferenceValue) value).getAddress());
    }

    public void addProgram(ProgramState newProgram) {
        this.repository.addProgram(newProgram);
    }

    public List<ProgramState> removeCompletedPrograms(List<ProgramState> inputProgramList){
        return inputProgramList.stream()
                .filter(ProgramState::isNotCompleted)
                .collect(Collectors.toList());
    }

    public void oneStepForAllPrograms(List<ProgramState> programList) throws Exception {
        //before the execution, print the ProgramState List into the log file
        //programList.forEach(program -> this.repository.logProgramStateExecute(program));

        for(ProgramState program: programList){
            this.repository.logProgramStateExecute(program);
        }

        //RUN concurrently one step for each of the existing PrgStates
        // prepare the list of callables
        List<Callable<ProgramState>> callList = programList.stream()
                .map((ProgramState program) -> (Callable<ProgramState>) () -> {
                    return program.oneStep();
                })
                .collect(Collectors.toList());

        //start the execution of the callables
        // it returns the list of new created PrgStates (namely threads)
        List<ProgramState> newProgramList = this.executor.invokeAll(callList).stream()
                .map(future -> {
                    try {
                        return future.get();
                    } catch (InterruptedException | ExecutionException e) {
                        throw new InterpretorException(e.getMessage());
                    }
                })
                .filter(program->program!=null)
                .collect(Collectors.toList());

        //add the new created threads to the list of existing threads
        programList.addAll(newProgramList);

        //after the execution, print the PrgState List into the log file
        for(ProgramState program: programList){
            this.repository.logProgramStateExecute(program);
        }

        //Save the current programs in the repository
        this.repository.setProgramList(programList);
    }


    public void allStep() throws Exception {
        this.repository.clearLogFile();
        this.executor= Executors.newFixedThreadPool(2);

        //remove the completed programs
        List<ProgramState> programList=removeCompletedPrograms(this.repository.getProgramList());
        while(programList.size()>0){
            programList.get(0).getMemoryHeap().setContent((HashMap<Integer, IValue>) conservativeGarbageCollector(programList, programList.get(0).getMemoryHeap().getContent()));
            oneStepForAllPrograms(programList);
            //remove the completed programs
            programList=removeCompletedPrograms((List<ProgramState>) this.repository.getProgramList());
        }
        this.executor.shutdownNow();
        //HERE the repository still contains at least one Completed Prg
        // and its List<PrgState> is not empty. Note that oneStepForAllPrg calls the method
        // setPrgList of repository in order to change the repository
        // update the repository state

        this.repository.setProgramList(programList);

    }

    public void executeOneStep() throws InterpretorException{
        this.executor=Executors.newFixedThreadPool(2);
        List<ProgramState> programList=removeCompletedPrograms(this.repository.getProgramList());
        if(programList.size()>0){
            try{
                programList.get(0).getMemoryHeap().setContent((HashMap<Integer, IValue>) conservativeGarbageCollector(programList, programList.get(0).getMemoryHeap().getContent()));
                oneStepForAllPrograms(this.repository.getProgramList());
                programList=removeCompletedPrograms(this.repository.getProgramList());
            } catch(Exception error){
                throw new InterpretorException(error.getMessage());
            }
            programList.forEach(e->{
                try{
                    this.repository.logProgramStateExecute(e);
                }
                catch (Exception error){
                    throw new InterpretorException(error.getMessage());
                }
            });
        }
        this.executor.shutdownNow();
        this.repository.setProgramList(programList);
    }


    public List<ProgramState> getPrograms(){
        return this.repository.getProgramList();
    }

    public void setDisplay(boolean newDisplay){
        this.display=newDisplay;
    }

    public void display(String displayText){
        if(this.display)
            System.out.println(displayText+'\n');
    }

    public void setDescription(String description){
        this.description=description;
    }

    public String getDescription(){
        return this.description;
    }

    public void setPassedTypeCheck(Boolean booleann){
        this.passedTypeCheck=booleann;
    }

    public Boolean getPassedTypeCheck(){
        return this.passedTypeCheck;
    }
}
