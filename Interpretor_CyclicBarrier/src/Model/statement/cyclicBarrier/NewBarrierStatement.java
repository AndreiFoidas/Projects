package Model.statement.cyclicBarrier;

import Model.ProgramState;
import Model.adt.*;
import Model.expression.Expression;
import Model.statement.IStatement;
import Model.type.IType;
import Model.type.IntType;
import Model.value.IValue;
import Model.value.IntValue;
import javafx.util.Pair;
import Exception.InterpretorException;
import java.util.ArrayList;
import java.util.List;

public class NewBarrierStatement implements IStatement {
    private final String variableName;
    private final Expression expression;

    public NewBarrierStatement(String variableName, Expression expression){
        this.variableName=variableName;
        this.expression=expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpretorException {
        MyStack<IStatement> stack= (MyStack<IStatement>) state.getExecutionStack();
        Dictionary<String, IValue> symbolTable= (Dictionary<String, IValue>) state.getSymbolTable();
        MyHeap<Integer, IValue> memoryHeap= (MyHeap<Integer, IValue>)state.getMemoryHeap();
        BarrierTable<Integer, Pair<Integer, List<Integer>>> barrierTable = (BarrierTable<Integer, javafx.util.Pair<Integer, List<Integer>>>) state.getBarrierTable();

        IValue num1 = this.expression.evaluate(symbolTable, memoryHeap);
        if(num1.getType() instanceof IntType){
            IntValue intNum1=(IntValue) num1;
            if(symbolTable.isDefined(this.variableName)){
                if(symbolTable.lookup(this.variableName).getType() instanceof IntType){
                    int newAddr = memoryHeap.getNextFreeAddress();
                    symbolTable.update(this.variableName,new IntValue(newAddr));
                    Pair<Integer, java.util.List<Integer>> pair=new Pair<>(intNum1.getValue(),new ArrayList<>());
                    barrierTable.update(newAddr,pair);
                }
                else
                    throw new InterpretorException("Variable "+this.variableName.toString()+" in newBarrier is not int!");
            }
            else
                throw new InterpretorException("Variable "+this.variableName.toString()+" in newBarrier is not defined!");
        }
        else
            throw new InterpretorException("Expression in newBarrier is not an integer!");

        return null;
    }

    @Override
    public IDictionary<String, IType> typeCheck(IDictionary<String, IType> typeEnvironment) throws InterpretorException {
        IType typeVariable=typeEnvironment.lookup(this.variableName);
        IType typeExpression=this.expression.typeCheck(typeEnvironment);
        if(typeVariable.equals(new IntType())){
            if(typeExpression.equals(new IntType()))
                return typeEnvironment;
            else
                throw new InterpretorException("Expression in newBarrier is not int!");
        }
        else
            throw new InterpretorException("Variable "+this.variableName.toString()+" in newBarrier is not int!");
    }

    @Override
    public String toString(){
        return "newBarrier("+this.variableName.toString()+","+this.expression.toString()+")";
    }
}
