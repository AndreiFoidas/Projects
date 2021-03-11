package Model.statement.cyclicBarrier;

import Model.ProgramState;
import Model.adt.*;
import Model.statement.IStatement;
import Model.type.IType;
import Exception.InterpretorException;
import Model.type.IntType;
import Model.value.IValue;
import Model.value.IntValue;
import javafx.util.Pair;

import java.util.List;

public class AwaitStatement implements IStatement {
    private final String variableName;

    public AwaitStatement(String variableName){
        this.variableName=variableName;
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpretorException {
        MyStack<IStatement> stack= (MyStack<IStatement>) state.getExecutionStack();
        Dictionary<String, IValue> symbolTable= (Dictionary<String, IValue>) state.getSymbolTable();
        MyHeap<Integer, IValue> memoryHeap= (MyHeap<Integer, IValue>)state.getMemoryHeap();
        BarrierTable<Integer, Pair<Integer, List<Integer>>> barrierTable = (BarrierTable<Integer, javafx.util.Pair<Integer, List<Integer>>>) state.getBarrierTable();

        if(symbolTable.isDefined(this.variableName)){
            IValue value = symbolTable.lookup(this.variableName);
            if(value.getType() instanceof IntType){
                IntValue foundIndex=(IntValue) value;
                if(barrierTable.isDefined(foundIndex.getValue())){
                    Pair<Integer, List<Integer>> pair = barrierTable.lookup(foundIndex.getValue());
                    int N1=pair.getKey();
                    List<Integer> List1=pair.getValue();
                    int NL=List1.size();

                    if(N1>NL) {
                        if(List1.contains(state.getProgramId())){
                            stack.push(this);
                        }
                        else{
                            List1.add(state.getProgramId());
                            stack.push(this);
                        }
                    }
                }
                else
                    throw new InterpretorException("Barrier does not exist in barrierTable (await statement)!");
            }
            else
                throw new InterpretorException("Variable "+this.variableName.toString()+" is not of type int in await statement!");
        }
        else
            throw new InterpretorException("Variable "+this.variableName.toString()+" is not defined in await statement!");

        return null;
    }

    @Override
    public IDictionary<String, IType> typeCheck(IDictionary<String, IType> typeEnvironment) throws InterpretorException {
        IType typeVariable=typeEnvironment.lookup(this.variableName);
        if(typeVariable.equals(new IntType()))
            return typeEnvironment;
        else
            throw new InterpretorException("Variable "+this.variableName.toString()+" in await is not int!");
    }

    @Override
    public String toString(){
        return "await("+this.variableName.toString()+")";
    }
}
