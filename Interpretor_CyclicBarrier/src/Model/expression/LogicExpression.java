package Model.expression;

import Model.adt.IDictionary;
import Model.adt.IHeap;
import Model.type.BoolType;
import Model.type.IType;
import Model.type.IntType;
import Model.value.BoolValue;
import Model.value.IValue;
import Exception.InterpretorException;

public class LogicExpression extends Expression{
    private final Expression expression1;
    private final Expression expression2;
    private final String operator; //AND=&&, OR=||

    public LogicExpression(Expression expression1, Expression expression2, String operator){
        this.expression1=expression1;
        this.expression2=expression2;
        this.operator=operator;
    }

    public LogicExpression(String operator, Expression expression1, Expression expression2){
        this.expression1=expression1;
        this.expression2=expression2;
        this.operator=operator;
    }

    @Override
    public IValue evaluate(IDictionary<String, IValue> symbolTable, IHeap<Integer, IValue> memoryHeap) throws InterpretorException {
        IValue value1, value2;
        value1=this.expression1.evaluate(symbolTable,memoryHeap);

        if(value1.getType().equals(new BoolType()))
        {
            value2=this.expression2.evaluate(symbolTable,memoryHeap);
            if(value2.getType().equals(new BoolType()))
            {
                BoolValue bool1=(BoolValue) value1;
                BoolValue bool2=(BoolValue) value2;
                boolean boolean1, boolean2;
                boolean1=bool1.getValue();
                boolean2=bool2.getValue();
                if(this.operator.equals("AND"))
                    return new BoolValue(boolean1 && boolean2);
                else if(this.operator.equals("OR"))
                    return new BoolValue(boolean1 || boolean2);
                else
                    throw new InterpretorException("Invalid operator for boolean!");
            }
            else
                throw new InterpretorException("Second operand is not boolean!");
        }
        else
            throw new InterpretorException("First operand is not boolean!");
    }

    @Override
    public IType typeCheck(IDictionary<String, IType> typeEnvironment) throws InterpretorException {
        IType typeExpression1, typeExpression2;
        typeExpression1=this.expression1.typeCheck(typeEnvironment);
        typeExpression2=this.expression2.typeCheck(typeEnvironment);

        if(typeExpression1.equals(new BoolType())){
            if(typeExpression2.equals(new BoolType())){
                return new BoolType();
            }
            else
                throw new InterpretorException("Second operand is not boolean!");
        }
        else
            throw new InterpretorException("First operand is not boolean!");
    }

    public String getOperator() {
        return this.operator;
    }

    public Expression getFirst() {
        return this.expression1;
    }

    public Expression getSecond() {
        return this.expression2;
    }

    @Override
    public String toString() {
        return this.expression1.toString()+" "+this.operator+" "+this.expression2.toString();
    }
}
