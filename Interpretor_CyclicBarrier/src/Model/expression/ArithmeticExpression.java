package Model.expression;
import Model.adt.IDictionary;
import Model.adt.IHeap;
import Model.type.IType;
import Model.type.IntType;
import Model.value.IValue;
import Model.value.IntValue;
import Exception.InterpretorException;

public class ArithmeticExpression extends Expression {
    char operator; //+=plus, -=minus, *=star, /=divide
    Expression expression1, expression2;

    //constructor
    public ArithmeticExpression(Expression expression1, Expression expression2, char operator){
        this.operator=operator;
        this.expression1=expression1;
        this.expression2=expression2;
    }
    public ArithmeticExpression(char operator, Expression expression1, Expression expression2){
        this.operator=operator;
        this.expression1=expression1;
        this.expression2=expression2;
    }

    @Override
    public IValue evaluate(IDictionary<String, IValue> symbolTable, IHeap<Integer, IValue> memoryHeap) throws InterpretorException {
        IValue value1, value2;
        value1=this.expression1.evaluate(symbolTable,memoryHeap);

        if(value1.getType().equals(new IntType())){
            value2=this.expression2.evaluate(symbolTable,memoryHeap);
            if(value2.getType().equals(new IntType())){
                IntValue int1=(IntValue)value1;
                IntValue int2=(IntValue)value2;
                int number1, number2;
                number1=int1.getValue();
                number2=int2.getValue();
                if(this.operator=='+')
                    return new IntValue(number1+number2);
                else if (this.operator=='-')
                    return new IntValue(number1-number2);
                else if (this.operator=='*')
                    return new IntValue(number1*number2);
                else if(this.operator=='/') {
                    if (number2 == 0)
                        throw new InterpretorException("Division by zero!");
                    else
                        return new IntValue(number1 / number2);
                }
                else
                    throw new InterpretorException("Invalid operator!");
            }
            else
                throw new InterpretorException("Second operand is not an integer!");
        }
        else
            throw new InterpretorException("First operand is not an integer!");
    }

    @Override
    public IType typeCheck(IDictionary<String, IType> typeEnvironment) throws InterpretorException {
        IType typeExpression1, typeExpression2;
        typeExpression1=this.expression1.typeCheck(typeEnvironment);
        typeExpression2=this.expression2.typeCheck(typeEnvironment);

        if(typeExpression1.equals(new IntType())){
            if(typeExpression2.equals(new IntType())){
                return new IntType();
            }
            else
                throw new InterpretorException("Second operand is not an integer!");
        }
        else
            throw new InterpretorException("First operand is not an integer!");
    }

    public char getOperator() {
        return this.operator;
    }

    public Expression getFirst() {
        return this.expression1;
    }

    public Expression getSecond() {
        return this.expression2;
    }

    public String toString() {
        return this.expression1.toString() + " " + this.operator + " " + this.expression2.toString();
    }
}
