package Model.statement.operation;

import Model.ProgramState;
import Model.adt.Dictionary;
import Model.adt.IDictionary;
import Model.adt.MyHeap;
import Model.adt.MyStack;
import Model.expression.Expression;
import Model.statement.IStatement;
import Model.type.BoolType;
import Model.type.IType;
import Model.value.BoolValue;
import Model.value.IValue;
import Exception.InterpretorException;

public class IfStatement implements IStatement {
    private final Expression expression;
    private final IStatement thenStatement;
    private final IStatement elseStatement;

    public IfStatement(Expression expression, IStatement thenStatement, IStatement elseStatement){
        this.expression=expression;
        this.thenStatement=thenStatement;
        this.elseStatement=elseStatement;
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpretorException {
        MyStack<IStatement> stack= (MyStack<IStatement>) state.getExecutionStack();
        Dictionary<String, IValue> symbolTable= (Dictionary<String, IValue>) state.getSymbolTable();
        MyHeap<Integer, IValue> memoryHeap= (MyHeap<Integer, IValue>)state.getMemoryHeap();

        IValue condition=this.expression.evaluate(symbolTable,memoryHeap);
        if(condition.getType().equals(new BoolType())){
            BoolValue boolCondition=(BoolValue) condition;
            if(boolCondition.getValue()){
                stack.push(this.thenStatement);
            }
            else
                stack.push(this.elseStatement);
        }
        else
            throw new InterpretorException("Conditional expression is not boolean!");

        return null;
    }

    @Override
    public IDictionary<String, IType> typeCheck(IDictionary<String, IType> typeEnvironment) throws InterpretorException {
        IType typeExpression=this.expression.typeCheck(typeEnvironment);
        if(typeExpression.equals(new BoolType())){
            this.thenStatement.typeCheck(typeEnvironment.clone());
            this.elseStatement.typeCheck(typeEnvironment.clone());
            return typeEnvironment;
        }
        else
            throw new InterpretorException("Conditional expression for if statement is not boolean!");
    }

    @Override
    public String toString(){
        return "(if("+this.expression.toString()+") then("+this.thenStatement.toString()+
                ") else("+this.elseStatement.toString()+"))";
    }
}
