package Model.statement.operation;

import Model.ProgramState;
import Model.adt.Dictionary;
import Model.adt.IDictionary;
import Model.adt.MyHeap;
import Model.adt.MyStack;
import Model.expression.Expression;
import Model.expression.RelationalExpression;
import Model.statement.IStatement;
import Model.statement.declaration.CompoundStatement;
import Model.type.BoolType;
import Model.type.IType;
import Exception.InterpretorException;
import Model.value.IValue;

public class SwitchStatement implements IStatement {
    private final Expression condExpression;
    private final Expression expression1;
    private final Expression expression2;
    private final IStatement statement1;
    private final IStatement statement2;
    private final IStatement statement3;

    public SwitchStatement(Expression condExpression, Expression expression1, IStatement statement1, Expression expression2, IStatement statement2, IStatement statement3){
        this.condExpression=condExpression;
        this.expression1=expression1;
        this.expression2=expression2;
        this.statement1=statement1;
        this.statement2=statement2;
        this.statement3=statement3;
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpretorException {
        MyStack<IStatement> stack= (MyStack<IStatement>) state.getExecutionStack();
        Dictionary<String, IValue> symbolTable= (Dictionary<String, IValue>) state.getSymbolTable();
        MyHeap<Integer, IValue> memoryHeap= (MyHeap<Integer, IValue>)state.getMemoryHeap();

        IStatement newStatement=new IfStatement(new RelationalExpression("==",this.condExpression,this.expression1),
                this.statement1,
                new IfStatement(new RelationalExpression("==",this.condExpression,this.expression2),
                        this.statement2,
                        this.statement3));

        stack.push(newStatement);

        return null;
    }

    @Override
    public IDictionary<String, IType> typeCheck(IDictionary<String, IType> typeEnvironment) throws InterpretorException {
        IType typeExpression1=this.condExpression.typeCheck(typeEnvironment);
        IType typeExpression2=this.expression1.typeCheck(typeEnvironment);
        IType typeExpression3=this.expression2.typeCheck(typeEnvironment);
        if(typeExpression1.equals(typeExpression2) && typeExpression2.equals(typeExpression3)){
            this.statement1.typeCheck(typeEnvironment.clone());
            this.statement2.typeCheck(typeEnvironment.clone());
            this.statement3.typeCheck(typeEnvironment.clone());

            return typeEnvironment;
        }
        else
            throw new InterpretorException("Expressions for switch statements don't have the same type!");
    }

    @Override
    public String toString(){
        return "switch("+this.condExpression.toString()+") (case("+this.expression1.toString()+"): "+this.statement1.toString()+
                "), (case("+this.statement2.toString()+"): "+this.statement2.toString()+"), (default: "+this.statement3.toString()+")";
    }
}
