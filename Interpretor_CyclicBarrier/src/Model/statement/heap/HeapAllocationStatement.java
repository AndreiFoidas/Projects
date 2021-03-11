package Model.statement.heap;

import Model.ProgramState;
import Model.adt.Dictionary;
import Model.adt.IDictionary;
import Model.adt.MyHeap;
import Model.statement.IStatement;
import Exception.*;
import Model.type.IType;
import Model.type.ReferenceType;
import Model.value.IValue;

import Model.expression.*;
import Model.value.ReferenceValue;

public class HeapAllocationStatement implements IStatement {
    private final String variableName;
    private final Expression expression;

    public HeapAllocationStatement(String variableName, Expression expression){
        this.variableName=variableName;
        this.expression=expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpretorException {
        Dictionary<String, IValue> symbolTable= (Dictionary<String, IValue>) state.getSymbolTable();
        MyHeap<Integer, IValue> memoryHeap=(MyHeap<Integer,IValue>) state.getMemoryHeap();

        if(symbolTable.isDefined(this.variableName)){
            IValue variableValue= symbolTable.lookup(this.variableName);

            if(variableValue.getType() instanceof ReferenceType){

                ReferenceValue referenceVariableValue=(ReferenceValue) variableValue;
                IValue expresionValue=this.expression.evaluate(symbolTable,memoryHeap);
                if(expresionValue.getType().equals(referenceVariableValue.getLocationType())){

                    IType locationType=referenceVariableValue.getLocationType();
                    int allocatedAddress=memoryHeap.getNextFreeAddress();
                    memoryHeap.add(allocatedAddress, expresionValue);
                    symbolTable.update(this.variableName, new ReferenceValue(allocatedAddress, locationType));

                }
                else
                    throw new InterpretorException("Expression type does not match variable type!");
            }
            else
                throw new InterpretorException("Variable type is not a ReferenceType");
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
            throw new InterpretorException("NEW statement: Expression type does not match variable type!");
    }

    @Override
    public String toString(){
        return "new("+this.variableName+","+this.expression.toString()+")";
    }
}
