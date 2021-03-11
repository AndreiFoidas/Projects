package Repository;
import Model.ProgramState;
import java.util.List;
import Exception.InterpretorException;

import java.io.*;
import java.util.LinkedList;

public class Repository implements IRepository {
    private List<ProgramState> myProgramStates;
    private final String logFilePath;

    public Repository(String path) {
        this.myProgramStates = new LinkedList<ProgramState>();
        this.logFilePath=path;
    }

    @Override
    public void addProgram(ProgramState newProgram) {
        this.myProgramStates.add(newProgram);
    }

    @Override
    public List<ProgramState> getProgramList() {
        return this.myProgramStates;
    }

    @Override
    public void setProgramList(List<ProgramState> newList) {
        this.myProgramStates=newList;
    }

    @Override
    public void logProgramStateExecute(ProgramState program) throws InterpretorException {
        PrintWriter logfile=null;
        try {
            logfile = new PrintWriter((new BufferedWriter(new FileWriter(this.logFilePath, true))));
            logfile.println("Program "+Integer.toString(program.getProgramId())+":");
            logfile.println("ExecutionStack:");
            logfile.println(program.getExecutionStack().toFileFormat());
            logfile.println("SymbolTable:");
            logfile.println(program.getSymbolTable().toFileFormat());
            logfile.println("OutputList:");
            logfile.println(program.getOutputList().toFileFormat());
            logfile.println("FileTable:");
            logfile.println(program.getFileTable().toFileFormat());
            logfile.println("MemoryHeap:");
            logfile.println(program.getMemoryHeap().toFileFormat());
            logfile.println("BarrierTable:");
            logfile.println(program.getBarrierTable().toFileFormat());
            logfile.println("\n---------------------------------------------------------------------------\n");

        }
        catch(Exception exception) {
            throw new InterpretorException("Log file does not exist!\n");
        }
        finally {
            if(logfile!=null)
                logfile.close();
        }
    }

    @Override
    public void clearLogFile() throws InterpretorException{
        try{
            PrintWriter writer=new PrintWriter(this.logFilePath);
            writer.println("");
            writer.close();
        }
        catch(FileNotFoundException exception)
        {
            throw new InterpretorException("Log file does not exist!\n");
        }
    }

}
