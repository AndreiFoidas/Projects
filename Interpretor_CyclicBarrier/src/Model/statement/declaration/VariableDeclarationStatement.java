package Model.statement.declaration;

import Model.ProgramState;
import Model.adt.Dictionary;
import Model.adt.IDictionary;
import Model.adt.List;
import Model.adt.MyStack;
import Model.statement.IStatement;
import Model.type.*;
import Model.value.BoolValue;
import Model.value.IValue;
import Model.value.IntValue;
import Exception.InterpretorException;
import Model.value.ReferenceValue;

public class VariableDeclarationStatement implements IStatement {
    private final String name;
    private final IType type;

    public VariableDeclarationStatement(String name, IType type){
        this.name=name;
        this.type=type;
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpretorException {
        MyStack<IStatement> stack= (MyStack<IStatement>) state.getExecutionStack();
        Dictionary<String, IValue> symbolTable= (Dictionary<String, IValue>) state.getSymbolTable();
        List<IValue> output=(List<IValue>) state.getOutputList();
        if(symbolTable.isDefined(this.name)){
            throw new InterpretorException("Variable "+name+" already declared!");
        }
        else{
            if(this.type.equals(new IntType()))
                symbolTable.update(this.name, new IntType().defaultValue());
            else if(this.type.equals(new BoolType()))
                symbolTable.update(this.name, new BoolType().defaultValue());
            else if(this.type.equals(new StringType()))
                symbolTable.update(this.name,new StringType().defaultValue());
            else if(this.type instanceof ReferenceType) {
                ReferenceType referenceType=(ReferenceType)this.type;
                symbolTable.update(this.name, new ReferenceType(referenceType.getInner()).defaultValue());
            }
            else
                throw new InterpretorException("Type is not correct!");
        }

        return null;
    }

    @Override
    public IDictionary<String, IType> typeCheck(IDictionary<String, IType> typeEnvironment) throws InterpretorException {
        typeEnvironment.add(this.name,this.type);
        return typeEnvironment;
    }

    @Override
    public String toString(){
        return this.type+" "+this.name;
    }
}
