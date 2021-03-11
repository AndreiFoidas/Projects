package Model.statement.operation;

import Model.ProgramState;
import Model.adt.Dictionary;
import Model.adt.IDictionary;
import Model.adt.MyHeap;
import Model.adt.MyStack;
import Model.expression.Expression;
import Model.statement.IStatement;
import Model.statement.declaration.AssignmentStatement;
import Model.type.BoolType;
import Model.type.IType;
import Exception.InterpretorException;
import Model.value.IValue;

public class ConditionalAssignmentStatement implements IStatement {
    private final String variable;
    private final Expression conditionalExpression;
    private final Expression expression1;
    private final Expression expression2;

    public ConditionalAssignmentStatement(String variable, Expression conditionalExpression, Expression expression1, Expression expression2){
        this.variable=variable;
        this.conditionalExpression=conditionalExpression;
        this.expression1=expression1;
        this.expression2=expression2;
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpretorException {
        MyStack<IStatement> stack= (MyStack<IStatement>) state.getExecutionStack();
        Dictionary<String, IValue> symbolTable= (Dictionary<String, IValue>) state.getSymbolTable();
        MyHeap<Integer, IValue> memoryHeap= (MyHeap<Integer, IValue>)state.getMemoryHeap();

        IfStatement newIf=new IfStatement(this.conditionalExpression,
                new AssignmentStatement(this.variable,this.expression1),
                new AssignmentStatement(this.variable,this.expression2));

        stack.push(newIf);

        return null;
    }

    @Override
    public IDictionary<String, IType> typeCheck(IDictionary<String, IType> typeEnvironment) throws InterpretorException {
        IType typeExpression=this.conditionalExpression.typeCheck(typeEnvironment);
        if(typeExpression.equals(new BoolType())){
            IType varType=typeEnvironment.lookup(this.variable);
            IType type1=this.expression1.typeCheck(typeEnvironment);
            IType type2=this.expression2.typeCheck(typeEnvironment);
            if(!type1.equals(type2) || !type2.equals(varType))
                throw new InterpretorException("Expressions in condAsStmt don't have the same type!");
            return typeEnvironment;
        }
        else
            throw new InterpretorException("Conditional expression for while loop is not boolean!");
    }

    @Override
    public String toString(){
        return this.variable.toString()+"="+this.conditionalExpression.toString()+"?"+this.expression1.toString()+":"+this.expression2.toString();
    }
}
