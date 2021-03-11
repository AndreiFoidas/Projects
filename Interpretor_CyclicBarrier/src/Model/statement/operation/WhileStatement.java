package Model.statement.operation;

import Model.ProgramState;
import Model.adt.Dictionary;
import Model.adt.IDictionary;
import Model.adt.MyHeap;
import Model.adt.MyStack;
import Model.expression.Expression;
import Model.statement.IStatement;
import Exception.*;
import Model.statement.declaration.CompoundStatement;
import Model.type.BoolType;
import Model.type.IType;
import Model.value.BoolValue;
import Model.value.IValue;

public class WhileStatement implements IStatement {
    private final Expression expression;
    private final IStatement statement;

    public WhileStatement(Expression expression, IStatement statement){
        this.expression=expression;
        this.statement=statement;
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpretorException {
        MyStack<IStatement> stack= (MyStack<IStatement>) state.getExecutionStack();
        Dictionary<String, IValue> symbolTable= (Dictionary<String, IValue>) state.getSymbolTable();
        MyHeap<Integer, IValue> memoryHeap= (MyHeap<Integer, IValue>)state.getMemoryHeap();

        IValue condition=this.expression.evaluate(symbolTable,memoryHeap);
        if(condition.getType().equals(new BoolType())) {
            BoolValue booleanCondition=(BoolValue)condition;
            if(booleanCondition.getValue())
                stack.push(new CompoundStatement(this.statement,new WhileStatement(this.expression,this.statement)));
        }
        else
            throw new InterpretorException("Conditional expression is not boolean!");

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
            throw new InterpretorException("Conditional expression for while loop is not boolean!");
    }

    @Override
    public String toString(){
        return "(While("+this.expression.toString()+") "+this.statement.toString()+")";
    }
}
