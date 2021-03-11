package Model.expression;
import Model.adt.IDictionary;
import Model.adt.IHeap;
import Model.type.IType;
import Model.value.IValue;
import Exception.InterpretorException;

public class VariableExpression extends Expression {
    private final String id;

    public VariableExpression(String id){
        this.id=id;
    }

    @Override
    public IValue evaluate(IDictionary<String, IValue> symbolTable, IHeap<Integer, IValue> memoryHeap) throws InterpretorException {
        return symbolTable.lookup(id);
    }

    @Override
    public IType typeCheck(IDictionary<String, IType> typeEnvironment) throws InterpretorException {
        return typeEnvironment.lookup(this.id);
    }

    @Override
    public String toString() {
        return id;
    }

}
