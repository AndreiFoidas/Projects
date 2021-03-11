package Model.expression;
import Model.adt.IDictionary;
import Model.adt.IHeap;
import Model.type.IType;
import Model.value.IValue;
import Exception.InterpretorException;

public abstract class Expression {

    public abstract IValue evaluate(IDictionary<String, IValue> symbolTable, IHeap<Integer, IValue> memoryHeap) throws InterpretorException;
    public abstract IType typeCheck(IDictionary<String, IType> typeEnvironment) throws InterpretorException;
    public abstract String toString();
}
