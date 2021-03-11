package Model.statement.file;

import Model.ProgramState;
import Exception.InterpretorException;
import Model.adt.Dictionary;
import Model.adt.IDictionary;
import Model.adt.MyHeap;
import Model.expression.Expression;
import Model.statement.IStatement;
import Model.type.IType;
import Model.type.IntType;
import Model.type.StringType;
import Model.value.IValue;
import Model.value.IntValue;
import Model.value.StringValue;

import java.io.BufferedReader;

public class ReadFileStatement implements IStatement {
    private final Expression expression;
    private final String variableName;

    public ReadFileStatement(Expression expression, String name){
        this.expression=expression;
        this.variableName=name;
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpretorException {
        Dictionary<String, IValue> symbolTable= (Dictionary<String, IValue>) state.getSymbolTable();
        Dictionary<StringValue, BufferedReader> fileTable=(Dictionary<StringValue,BufferedReader>) state.getFileTable();
        MyHeap<Integer, IValue> memoryHeap= (MyHeap<Integer, IValue>)state.getMemoryHeap();

        if(symbolTable.lookup(this.variableName).getType().equals(new IntType()))
        {
            IValue value=this.expression.evaluate(symbolTable,memoryHeap);
            if(value.getType().equals(new StringType()))
            {
                StringValue fileName=(StringValue)value;
                if(fileTable.isDefined(fileName)) {
                    BufferedReader reader = fileTable.lookup(fileName);
                    try {
                        int intValue;
                        String line = reader.readLine();
                        if(line.equals("\n"))
                            intValue=0;
                        else
                            intValue=Integer.parseInt(line);

                        symbolTable.update(this.variableName,new IntValue(intValue));
                    }
                    catch(Exception error){
                        throw new InterpretorException("Error reading from the file!");
                    }
                }
                else
                    throw new InterpretorException("File "+fileName+" does not exist!");
            }
            else
                throw new InterpretorException("Read file expression is not a string!");
        }
        else
            throw new InterpretorException("Variable "+this.variableName+" is not an integer!");

        return null;
    }

    @Override
    public IDictionary<String, IType> typeCheck(IDictionary<String, IType> typeEnvironment) throws InterpretorException {
        IType typeVariable=typeEnvironment.lookup(this.variableName);
        IType typeExpression=this.expression.typeCheck(typeEnvironment);
        if(typeVariable.equals(new IntType())){
            if(typeExpression.equals(new StringType())){
                return typeEnvironment;
            }
            else
                throw new InterpretorException("Read file expression is not a string!");
        }
        else
            throw new InterpretorException("Variable "+this.variableName+" is not an integer!");
    }

    @Override
    public String toString(){
        return "read @"+this.variableName+" fromFile("+this.expression.toString()+")";
    }
}
