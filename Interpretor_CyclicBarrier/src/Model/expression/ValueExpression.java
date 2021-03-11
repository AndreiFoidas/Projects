package Model.expression;

import Model.adt.IDictionary;
import Model.adt.IHeap;
import Model.type.IType;
import Model.value.IValue;
import Exception.InterpretorException;

public class ValueExpression extends Expression{
    IValue value;

    public ValueExpression(IValue value){
        this.value=value;
    }

    @Override
    public IValue evaluate(IDictionary<String, IValue> symbolTable, IHeap<Integer, IValue> memoryHeap) throws InterpretorException {
        return value;
    }

    @Override
    public IType typeCheck(IDictionary<String, IType> typeEnvironment) throws InterpretorException {
        return this.value.getType();
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
