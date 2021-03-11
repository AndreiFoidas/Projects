package Model.statement.file;

import Model.ProgramState;
import Model.adt.*;
import Model.expression.Expression;
import Model.statement.IStatement;
import Model.type.IType;
import Model.type.StringType;
import Model.value.IValue;
import Exception.InterpretorException;
import Model.value.StringValue;

import java.io.BufferedReader;
import java.io.FileReader;

public class OpenReadFileStatement implements IStatement {
    private final Expression expression;

    //constructor
    public OpenReadFileStatement(Expression expression){
        this.expression=expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpretorException {
        Dictionary<String, IValue> symbolTable= (Dictionary<String, IValue>) state.getSymbolTable();
        Dictionary<StringValue, BufferedReader> fileTable=(Dictionary<StringValue,BufferedReader>) state.getFileTable();
        MyHeap<Integer, IValue> memoryHeap= (MyHeap<Integer, IValue>)state.getMemoryHeap();

        IValue value=this.expression.evaluate(symbolTable,memoryHeap);

        if(value.getType().equals(new StringType()))
        {
            StringValue fileName=(StringValue)value;
            if(!fileTable.isDefined(fileName)) {
                try {
                    BufferedReader newBuffer = new BufferedReader(new FileReader(fileName.getValue()));
                    
                    fileTable.update(fileName,newBuffer);
                }
                catch(Exception error)
                {
                    throw new InterpretorException("Could not open file!");
                }
            }
            else
                throw new InterpretorException("File name "+ fileName+" is already defined!");
        }
        else
            throw new InterpretorException("Open read file expression is not string!");

        return null;
    }

    @Override
    public IDictionary<String, IType> typeCheck(IDictionary<String, IType> typeEnvironment) throws InterpretorException {
        IType typeExpression=this.expression.typeCheck(typeEnvironment);
        if(typeExpression.equals(new StringType()))
            return typeEnvironment;
        else
            throw new InterpretorException("Open read file expression is not string!");
    }

    @Override
    public String toString()
    {
        return "openFile("+this.expression.toString()+")";
    }
}
