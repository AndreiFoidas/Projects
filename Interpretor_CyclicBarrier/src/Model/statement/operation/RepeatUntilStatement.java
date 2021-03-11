package Model.statement.operation;

import Model.ProgramState;
import Model.adt.Dictionary;
import Model.adt.IDictionary;
import Model.adt.MyHeap;
import Model.adt.MyStack;
import Model.expression.Expression;
import Model.statement.IStatement;
import Model.statement.declaration.CompoundStatement;
import Model.type.BoolType;
import Model.type.IType;
import Exception.*;
import Model.value.BoolValue;
import Model.value.IValue;

public class RepeatUntilStatement implements IStatement {
    private final Expression expression;
    private final IStatement statement;
    private final boolean firstExecution;

    public RepeatUntilStatement(Expression expression, IStatement statement) {
        this.expression = expression;
        this.statement = statement;
        this.firstExecution = true;
    }

    public RepeatUntilStatement(Expression expression, IStatement statement, boolean firstExecution){
        this.expression=expression;
        this.statement=statement;
        this.firstExecution=firstExecution;
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpretorException {
        MyStack<IStatement> stack= (MyStack<IStatement>) state.getExecutionStack();
        Dictionary<String, IValue> symbolTable= (Dictionary<String, IValue>) state.getSymbolTable();
        MyHeap<Integer, IValue> memoryHeap= (MyHeap<Integer, IValue>)state.getMemoryHeap();

        IValue condition=this.expression.evaluate(symbolTable,memoryHeap);
        if(condition.getType().equals(new BoolType())) {
            BoolValue booleanCondition=(BoolValue)condition;
            if(!booleanCondition.getValue() || this.firstExecution)
                stack.push(new CompoundStatement(this.statement, new RepeatUntilStatement(this.expression,this.statement,false)));
        }
        else
            throw new InterpretorException("Conditional expression in repeatUntil is not boolean!");


        return null;
    }

    @Override
    public IDictionary<String, IType> typeCheck(IDictionary<String, IType> typeEnvironment) throws InterpretorException {
        IType typeExpression=this.expression.typeCheck(typeEnvironment);
        if(typeExpression.equals(new BoolType())){
            this.statement.typeCheck(typeEnvironment.clone());
            return typeEnvironment;
        }
        else
            throw new InterpretorException("Conditional expression for repeatUntil is not boolean!");
    }

    @Override
    public String toString(){
        return "repeat("+this.statement.toString()+") until "+this.expression.toString();
    }
}
