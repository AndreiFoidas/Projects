package Model.statement.operation;

import Model.ProgramState;
import Model.adt.*;
import Model.statement.IStatement;
import Exception.*;
import Model.statement.declaration.NopStatement;
import Model.type.IType;
import Model.value.IValue;

public class ForkStatement implements IStatement{
    private final IStatement statement;

    public ForkStatement(IStatement statement){
        this.statement=statement;
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpretorException {

        ProgramState newProgramState=new ProgramState(
                new MyStack<IStatement>(),
                (Dictionary<String, IValue>)state.getSymbolTable().clone(),
                (List<IValue>)state.getOutputList(),
                state.getFileTable(),
                state.getMemoryHeap(),
                state.getBarrierTable(),
                this.statement);

        return newProgramState;
    }

    @Override
    public IDictionary<String, IType> typeCheck(IDictionary<String, IType> typeEnvironment) throws InterpretorException {
        this.statement.typeCheck(typeEnvironment.clone());
        return typeEnvironment;
    }

    @Override
    public String toString(){
        return "Fork("+this.statement.toString()+")";
    }
}
