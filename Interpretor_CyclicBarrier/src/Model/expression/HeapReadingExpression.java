package Model.expression;

import Model.adt.Dictionary;
import Model.adt.IDictionary;
import Model.adt.IHeap;
import Model.adt.MyHeap;
import Model.type.IType;
import Model.type.ReferenceType;
import Model.value.IValue;
import Exception.InterpretorException;
import Model.value.ReferenceValue;

public class HeapReadingExpression extends Expression{
    private final Expression expression;

    public HeapReadingExpression(Expression expression){
        this.expression=expression;
    }

    @Override
    public IValue evaluate(IDictionary<String, IValue> symbolTable, IHeap<Integer, IValue> memoryHeap) throws InterpretorException {
        IValue value=this.expression.evaluate(symbolTable, memoryHeap);
        if(value.getType() instanceof ReferenceType) {
            ReferenceValue referenceValue=(ReferenceValue) value;
            if(memoryHeap.isDefined(referenceValue.getAddress())) {
                return memoryHeap.lookup(referenceValue.getAddress());
            }
            else
                throw new InterpretorException("Address is not a key in the memoryHeap!");

        }
        else
            throw new InterpretorException("Expression is not a ReferenceType!");
    }

    @Override
    public IType typeCheck(IDictionary<String, IType> typeEnvironment) throws InterpretorException {
        IType typeExpression;
        typeExpression=this.expression.typeCheck(typeEnvironment);
        if(typeExpression instanceof ReferenceType) {
            ReferenceType referenceTypeExpression=(ReferenceType)typeExpression;
            return referenceTypeExpression.getInner();
        }
        else
            throw new InterpretorException("Expression is not a ReferenceType!");
    }

    @Override
    public String toString() {
        return "readHeap("+this.expression.toString()+")";
    }
}
