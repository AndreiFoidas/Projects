package Model.statement.declaration;

import Model.ProgramState;
import Model.adt.Dictionary;
import Model.adt.IDictionary;
import Model.adt.MyStack;
import Exception.InterpretorException;
import Model.statement.IStatement;
import Model.type.IType;

public class CompoundStatement implements IStatement {
    private final IStatement firstStatement;
    private final IStatement secondStatement;

    public CompoundStatement(IStatement first, IStatement second){
        this.firstStatement=first;
        this.secondStatement=second;
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpretorException{
        MyStack<IStatement> stack= (MyStack<IStatement>) state.getExecutionStack();
        stack.push(secondStatement);
        stack.push(firstStatement);

        return null;
    }

    @Override
    public IDictionary<String, IType> typeCheck(IDictionary<String, IType> typeEnvironment) throws InterpretorException {
        Dictionary<String, IType> typeEnvironment1 = (Dictionary<String, IType>) this.firstStatement.typeCheck(typeEnvironment);
        Dictionary<String, IType> typeEnvironment2 = (Dictionary<String, IType>) this.secondStatement.typeCheck(typeEnvironment1);
        return typeEnvironment2;

        //return this.secondStatement.typeCheck(this.firstStatement.typeCheck(typeEnvironment));
    }

    @Override
    public String toString(){
        return "("+firstStatement.toString()+" | "+secondStatement.toString()+")";
    }
}
