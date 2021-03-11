package Model.statement.declaration;

import Model.ProgramState;
import Exception.InterpretorException;
import Model.adt.IDictionary;
import Model.statement.IStatement;
import Model.type.IType;

public class NopStatement implements IStatement {

    public NopStatement(){};

    @Override
    public ProgramState execute(ProgramState state) throws InterpretorException {
        return null;
    }

    @Override
    public IDictionary<String, IType> typeCheck(IDictionary<String, IType> typeEnvironment) throws InterpretorException {
        return typeEnvironment;
    }

    @Override
    public String toString(){
        return "";
    }

}
