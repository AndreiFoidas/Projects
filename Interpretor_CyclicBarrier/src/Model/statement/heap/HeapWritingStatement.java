package Model.statement.heap;

import Model.ProgramState;
import Model.adt.Dictionary;
import Model.adt.IDictionary;
import Model.adt.MyHeap;
import Model.expression.Expression;
import Model.expression.HeapReadingExpression;
import Model.statement.IStatement;
import Exception.InterpretorException;
import Model.type.IType;
import Model.type.ReferenceType;
import Model.value.IValue;
import Model.value.ReferenceValue;

public class HeapWritingStatement implements IStatement {
    private final String variableName;
    private final Expression expression;

    public HeapWritingStatement(String name, Expression expression){
        this.variableName=name;
        this.expression=expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpretorException {
        Dictionary<String, IValue> symbolTable= (Dictionary<String, IValue>) state.getSymbolTable();
        MyHeap<Integer, IValue> memoryHeap=(MyHeap<Integer,IValue>) state.getMemoryHeap();

        if(symbolTable.isDefined(this.variableName)){

            IValue variableValue=symbolTable.lookup(this.variableName);
            if(variableValue.getType() instanceof ReferenceType){

                ReferenceValue variableReferenceValue=(ReferenceValue) variableValue;
                if(memoryHeap.isDefined(variableReferenceValue.getAddress())){

                    IValue expressionValue=this.expression.evaluate(symbolTable,memoryHeap);
                    if(expressionValue.getType().equals(variableReferenceValue.getLocationType())){

                        memoryHeap.update(variableReferenceValue.getAddress(), expressionValue);
                    }
                    else
                        throw new InterpretorException("Variable type and expression type don't match!");
                }
                else
                    throw new InterpretorException("Address is not a key in the memoryHeap!");
            }
            else
                throw new InterpretorException("Variable is not of ReferenceType!");
        }
        else
            throw new InterpretorException("Variable "+this.variableName+" is not defined!");

        return null;
    }

    @Override
    public IDictionary<String, IType> typeCheck(IDictionary<String, IType> typeEnvironment) throws InterpretorException {
        IType typeVariable=typeEnvironment.lookup(this.variableName);
        IType typeExpression=this.expression.typeCheck(typeEnvironment);
        if(typeVariable.equals(new ReferenceType(typeExpression))){
            return typeEnvironment;
        }
        else
            throw new InterpretorException("writeH statement: Variable type and expression type don't match!");
    }

    @Override
    public String toString(){
        return "writeHeap("+this.variableName+","+this.expression.toString()+")";
    }
}
