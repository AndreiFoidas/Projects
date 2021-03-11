package Model.statement.operation;

import Model.ProgramState;
import Model.adt.Dictionary;
import Model.adt.IDictionary;
import Model.adt.MyHeap;
import Model.adt.MyStack;
import Model.statement.IStatement;
import Model.type.IType;
import Exception.*;
import Model.value.IValue;

public class SleepStatement implements IStatement {
    private final int number;

    public SleepStatement(int number){
        this.number=number;
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpretorException {
        MyStack<IStatement> stack= (MyStack<IStatement>) state.getExecutionStack();
        Dictionary<String, IValue> symbolTable= (Dictionary<String, IValue>) state.getSymbolTable();
        MyHeap<Integer, IValue> memoryHeap= (MyHeap<Integer, IValue>)state.getMemoryHeap();

        if(this.number>0)
            stack.push(new SleepStatement(this.number-1));

        return null;
    }

    @Override
    public IDictionary<String, IType> typeCheck(IDictionary<String, IType> typeEnvironment) throws InterpretorException {
        return typeEnvironment;
    }

    @Override
    public String toString(){
        return "sleep("+this.number+")";
    }
}
