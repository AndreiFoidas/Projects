package Model.statement.file;

import Model.ProgramState;
import Exception.InterpretorException;
import Model.adt.Dictionary;
import Model.adt.IDictionary;
import Model.adt.MyHeap;
import Model.expression.Expression;
import Model.statement.IStatement;
import Model.type.IType;
import Model.type.StringType;
import Model.value.IValue;
import Model.value.StringValue;

import java.io.BufferedReader;

public class CloseReadFileStatement implements IStatement {
    private final Expression expression;

    public CloseReadFileStatement(Expression expression){
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
            if(fileTable.isDefined(fileName))
            {
                try {
                    BufferedReader reader = fileTable.lookup(fileName);
                    reader.close();
                    fileTable.remove(fileName);
                }
                catch (Exception error) {
                    throw new InterpretorException("Could not close file!");
                }
            }
            else
                throw new InterpretorException("File "+fileName.getValue()+" does not exist!");
        }
        else
            throw new InterpretorException("Close read file expression is not string!");

        return null;
    }

    @Override
    public IDictionary<String, IType> typeCheck(IDictionary<String, IType> typeEnvironment) throws InterpretorException {
        IType typeExpression=this.expression.typeCheck(typeEnvironment);
        if(typeExpression.equals(new StringType()))
            return typeEnvironment;
        else
            throw new InterpretorException("Close read file expression is not string!");
    }

    @Override
    public String toString() {
        return "closeFile("+this.expression.toString()+")";
    }
}
