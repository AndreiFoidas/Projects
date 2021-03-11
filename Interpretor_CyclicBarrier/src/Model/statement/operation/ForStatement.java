package Model.statement.operation;

import Model.ProgramState;
import Model.adt.Dictionary;
import Model.adt.IDictionary;
import Model.adt.MyHeap;
import Model.adt.MyStack;
import Model.expression.Expression;
import Model.expression.RelationalExpression;
import Model.expression.VariableExpression;
import Model.statement.IStatement;
import Model.statement.declaration.AssignmentStatement;
import Model.statement.declaration.CompoundStatement;
import Model.statement.declaration.NopStatement;
import Model.statement.declaration.VariableDeclarationStatement;
import Model.type.BoolType;
import Model.type.IType;
import Exception.InterpretorException;
import Model.type.IntType;
import Model.value.IValue;

public class ForStatement implements IStatement {
    private final String variable;
    private final Expression expression1;
    private final Expression expression2;
    private final Expression expression3;
    private final IStatement statement;

    public ForStatement(String variable, Expression expression1, Expression expression2, Expression expression3, IStatement statement){
        this.variable=variable;
        this.expression1=expression1;
        this.expression2=expression2;
        this.expression3=expression3;
        this.statement=statement;
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpretorException {
        MyStack<IStatement> stack= (MyStack<IStatement>) state.getExecutionStack();
        Dictionary<String, IValue> symbolTable= (Dictionary<String, IValue>) state.getSymbolTable();
        MyHeap<Integer, IValue> memoryHeap= (MyHeap<Integer, IValue>)state.getMemoryHeap();

        IStatement newStatement=new CompoundStatement(
                new VariableDeclarationStatement("v",new IntType()),
                new CompoundStatement(new AssignmentStatement(this.variable,this.expression1),
                        new WhileStatement(new RelationalExpression("<", new VariableExpression("v"), this.expression2),
                        new CompoundStatement(this.statement, new AssignmentStatement(this.variable,this.expression3)))));

        stack.push(newStatement);

        return null;
    }

    @Override
    public IDictionary<String, IType> typeCheck(IDictionary<String, IType> typeEnvironment) throws InterpretorException {
        typeEnvironment.add(this.variable,new IntType());
        IType variableType=typeEnvironment.lookup(this.variable);
        IType typeExpression1=this.expression1.typeCheck(typeEnvironment);
        IType typeExpression2=this.expression2.typeCheck(typeEnvironment);
        IType typeExpression3=this.expression3.typeCheck(typeEnvironment);

        if(typeExpression1.equals(new IntType()) && typeExpression2.equals(new IntType()) && typeExpression3.equals(new IntType()) && variableType.equals(new IntType())){
            return typeEnvironment;
        }
        else
            throw new InterpretorException("For loop doesn't have integer expressions!");
    }

    @Override
    public String toString(){
        return "for("+this.variable.toString()+"="+this.expression1.toString()+";"+this.variable.toString()+"<"+this.expression2.toString()+
                ";"+this.variable.toString()+"="+this.expression3.toString()+" "+this.statement.toString();
    }
}
