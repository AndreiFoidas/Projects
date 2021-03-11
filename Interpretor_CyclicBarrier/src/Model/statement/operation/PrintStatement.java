package Model.statement.operation;

import Model.ProgramState;
import Model.adt.Dictionary;
import Model.adt.IDictionary;
import Model.adt.List;
import Model.adt.MyHeap;
import Model.expression.Expression;
import Model.statement.IStatement;
import Model.type.IType;
import Model.value.IValue;
import Exception.InterpretorException;

public class PrintStatement implements IStatement {
    private final Expression expression;

    public PrintStatement(Expression expression){
        this.expression=expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpretorException {
        //MyStack<IStatement> stack= (MyStack<IStatement>) state.getExecutionStack();
        Dictionary<String, IValue> symbolTable= (Dictionary<String, IValue>) state.getSymbolTable();
        List<IValue> output=(List<IValue>) state.getOutputList();
        MyHeap<Integer, IValue> memoryHeap= (MyHeap<Integer, IValue>)state.getMemoryHeap();

        output.add(expression.evaluate(symbolTable,memoryHeap));

        return null;
    }

    @Override
    public IDictionary<String, IType> typeCheck(IDictionary<String, IType> typeEnvironment) throws InterpretorException {
        this.expression.typeCheck(typeEnvironment);
        return typeEnvironment;
    }

    @Override
    public String toString(){
        return "print("+expression.toString()+")";
    }
}
