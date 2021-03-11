package Model.statement.declaration;

import Model.ProgramState;
import Model.adt.Dictionary;
import Model.adt.IDictionary;
import Model.adt.MyHeap;
import Model.expression.Expression;
import Model.statement.IStatement;
import Model.type.IType;
import Model.value.IValue;
import Exception.InterpretorException;

public class AssignmentStatement implements IStatement {
    private final String id;
    private final Expression expression;

    public AssignmentStatement(String id, Expression expression){
        this.id=id;
        this.expression=expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpretorException {
        //MyStack<IStatement> stack= (MyStack<IStatement>) state.getExecutionStack();
        Dictionary<String, IValue> symbolTable= (Dictionary<String, IValue>) state.getSymbolTable();
        MyHeap<Integer, IValue> memoryHeap= (MyHeap<Integer, IValue>)state.getMemoryHeap();

        if (symbolTable.isDefined(this.id)){
            IValue value=this.expression.evaluate(symbolTable,memoryHeap);
            IType typeID=(symbolTable.lookup(this.id)).getType();
            if(value.getType().equals(typeID))
                symbolTable.update(this.id, value);
            else
                throw new InterpretorException("Declared type of variable "+this.id
                        +" and the type of the assigned expression do not match!");

        }
        else
            throw new InterpretorException("Variable "+this.id+" is not declared!");

        return null;
    }

    @Override
    public IDictionary<String, IType> typeCheck(IDictionary<String, IType> typeEnvironment) throws InterpretorException {
        IType typeVariable=typeEnvironment.lookup(this.id);
        IType typeExpression=this.expression.typeCheck(typeEnvironment);
        if(typeVariable.equals(typeExpression)){
            return typeEnvironment;
        }
        else
            throw new InterpretorException("Declared type of variable "+this.id
                    +" and the type of the assigned expression do not match!");
    }

    @Override
    public String toString(){
        return this.id+" = "+this.expression.toString();
    }
}
