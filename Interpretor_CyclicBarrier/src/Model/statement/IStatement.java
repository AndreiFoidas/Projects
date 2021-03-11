package Model.statement;

import Model.ProgramState;
import Exception.InterpretorException;
import Model.adt.IDictionary;
import Model.type.IType;

public interface IStatement {
    ProgramState execute(ProgramState state) throws InterpretorException;
    IDictionary<String, IType>typeCheck(IDictionary<String,IType> typeEnvironment) throws InterpretorException;
    String toString();
}
