package Model;
import Model.adt.*;
import Model.statement.IStatement;
import Model.value.IValue;
import Model.value.StringValue;
import Exception.*;
import javafx.util.Pair;

import java.io.BufferedReader;

public class ProgramState {
    private final int programId;
    private IStack<IStatement> executionStack;
    private IDictionary<String, IValue> symbolTable;
    private IList<IValue> outputList;
    private IDictionary<StringValue, BufferedReader> fileTable;
    private IHeap<Integer, IValue> memoryHeap;
    private IStatement originalProgram; //optional field, but good to have
    private IBarrierTable<Integer, Pair<Integer, java.util.List<Integer>>> barrierTable;
    private static Integer currentId = 0;

    public ProgramState(MyStack<IStatement> stack, Dictionary<String, IValue> symbolTable,
                 List<IValue> outputList, IDictionary<StringValue, BufferedReader> fileTable, IHeap<Integer, IValue> memoryHeap, IBarrierTable<Integer, Pair<Integer, java.util.List<Integer>>> barrierTable, IStatement program){
        this.executionStack=stack;
        this.symbolTable=symbolTable;
        this.outputList=outputList;
        this.fileTable=fileTable;
        this.memoryHeap=memoryHeap;
        this.barrierTable=barrierTable;
        this.programId=nextID();
        //this.originalProgram=deepCopy(program);

        this.executionStack.push(program);
    }

    static synchronized int nextID(){
        currentId=currentId+1;
        return currentId;
    }

    public ProgramState oneStep() throws InterpretorException{
        if(this.executionStack.isEmpty())
            throw new InterpretorException("Program stack is empty!");
        IStatement currentStatement=this.executionStack.pop();

        return currentStatement.execute(this);
    }

    public IStack<IStatement> getExecutionStack(){
        return this.executionStack;
    }

    public IDictionary<String, IValue> getSymbolTable(){
        return this.symbolTable;
    }

    public IList<IValue> getOutputList(){
        return this.outputList;
    }

    public IDictionary<StringValue, BufferedReader> getFileTable(){
        return this.fileTable;
    }

    public void setExecutionStack(IStack<IStatement> newStack){
        this.executionStack=newStack;
    }

    public void setSymbolTable(IDictionary<String, IValue> newDictionary){
        this.symbolTable=newDictionary;
    }

    public void setOutputList(IList<IValue> newList){
        this.outputList=newList;
    }

    public void setFileTable(IDictionary<StringValue, BufferedReader> newFileTable){
        this.fileTable=newFileTable;
    }

    public IHeap<Integer, IValue> getMemoryHeap(){
        return this.memoryHeap;
    }

    public void setMemoryHeap(IHeap<Integer, IValue> newHeap){
        this.memoryHeap=newHeap;
    }

    public IBarrierTable<Integer, Pair<Integer, java.util.List<Integer>>> getBarrierTable(){
        return this.barrierTable;
    }

    public void setBarrierTable(IBarrierTable<Integer, Pair<Integer, java.util.List<Integer>>> newBarrierTable){
        this.barrierTable=newBarrierTable;
    }

    public int getProgramId(){
        return this.programId;
    }

    public Boolean isNotCompleted(){
        return !this.executionStack.isEmpty();
    }

    public String toString(){
        StringBuilder string=new StringBuilder();
        string.append("Program ");
        string.append(this.programId);
        string.append(":\n");
        string.append("Execution stack: ");
        string.append(this.executionStack.toString());
        string.append("\nSymbol table: ");
        string.append(this.symbolTable.toString());
        string.append("\nOutput: ");
        string.append(this.outputList.toString());
        string.append("\nFile Table: ");
        string.append(this.fileTable.toString());
        string.append("\nMemory heap: ");
        string.append(this.memoryHeap.toString());
        return string.toString();
    }
}