package Test;
import Model.ProgramState;
import Model.adt.*;
import Model.expression.ValueExpression;
import Model.expression.VariableExpression;
import Model.statement.*;
import Model.statement.declaration.AssignmentStatement;
import Model.statement.declaration.CompoundStatement;
import Model.statement.declaration.NopStatement;
import Model.statement.declaration.VariableDeclarationStatement;
import Model.statement.operation.IfStatement;
import Model.statement.operation.PrintStatement;
import Model.type.BoolType;
import Model.type.IntType;
import Model.value.BoolValue;
import Model.value.IValue;
import Model.value.IntValue;
import Exception.InterpretorException;
import Model.value.StringValue;
import org.junit.Test;

import java.io.BufferedReader;

import static org.junit.Assert.assertEquals;

public class StatementTests {

    /*
    @Test
    public void testAssignmentStatement(){
        MyStack<IStatement> executionStack = new MyStack<>();
        Dictionary<String, IValue> symbolTable = new Dictionary<>();
        List<IValue> outputList = new List<>();
        IDictionary<StringValue, BufferedReader> fileTable=new Dictionary<>();

        symbolTable.add("a",new IntValue(0));
        IStatement statement1 = new AssignmentStatement("a", new ValueExpression(new IntValue(2)));
        IStatement statement2 =  new AssignmentStatement("a", new ValueExpression(new BoolValue(false)));
        IStatement statement3 = new AssignmentStatement("b", new ValueExpression(new IntValue(2)));

        assertEquals(statement1.toString(), "a = 2");

        try{
            ProgramState program = new ProgramState(executionStack,symbolTable,outputList,fileTable,new MyHeap<>(),statement1);
            ProgramState programState = statement1.execute(program);
            assertEquals(symbolTable.lookup("a").toString(),"2");
        }
        catch(InterpretorException error) {
            assert (false);
        }

        try{
            ProgramState program = new ProgramState(executionStack,symbolTable,outputList,fileTable,new MyHeap<>(),statement2);
            ProgramState programState=statement2.execute(program);
            assert(false);
        }
        catch(InterpretorException error){
            assert(true);
        }
        try{
            ProgramState program = new ProgramState(executionStack,symbolTable,outputList,fileTable,new MyHeap<>(),statement3);
            ProgramState programState=statement3.execute(program);
            assert(false);
        }
        catch(InterpretorException error){
            assert(true);
        }
    }

    @Test
    public void testCompoundStatement(){
        MyStack<IStatement> executionStack = new MyStack<>();
        Dictionary<String, IValue> symbolTable = new Dictionary<>();
        List<IValue> outputList = new List<>();
        IDictionary<StringValue, BufferedReader> fileTable=new Dictionary<>();

        IStatement statement1 = new CompoundStatement(new VariableDeclarationStatement("a",new BoolType()),new NopStatement());

        assertEquals(statement1.toString(),"(boolean a | )");

        try{
            ProgramState program=new ProgramState(executionStack,symbolTable,outputList,fileTable,new MyHeap<>(),statement1);
            ProgramState programState=statement1.execute(program);
            assertEquals(executionStack.pop().toString(),"boolean a");
        }
        catch(InterpretorException error) {
            assert(false);
        }
    }

    @Test
    public void testIfStatement(){
        MyStack<IStatement> executionStack = new MyStack<>();
        Dictionary<String, IValue> symbolTable = new Dictionary<>();
        List<IValue> outputList = new List<>();
        IDictionary<StringValue, BufferedReader> fileTable=new Dictionary<>();

        symbolTable.add("T",new BoolValue(true));
        symbolTable.add("F",new BoolValue(false));
        symbolTable.add("a",new IntValue(0));
        symbolTable.add("b",new IntValue(0));

        IStatement statement1=new IfStatement(new VariableExpression("T"),
                new AssignmentStatement("a",new ValueExpression(new IntValue(1))),
                new AssignmentStatement("a",new ValueExpression(new IntValue(2))));

        IStatement statement2=new IfStatement(new VariableExpression("F"),
                new AssignmentStatement("b",new ValueExpression(new IntValue(1))),
                new AssignmentStatement("b",new ValueExpression(new IntValue(2))));

        IStatement statement3=new IfStatement(new VariableExpression("a"),
                new NopStatement(),new NopStatement());

        assertEquals(statement1.toString(),"(if(T) then(a = 1) else(a = 2))");

        try{
            ProgramState program=new ProgramState(executionStack,symbolTable,outputList,fileTable,new MyHeap<>(),statement1);
            ProgramState programState=statement1.execute(program);
            assertEquals(executionStack.pop().toString(),"a = 1");
        }
        catch(InterpretorException error){
            assert(false);
        }

        try{
            ProgramState program=new ProgramState(executionStack,symbolTable,outputList,fileTable,new MyHeap<>(),statement2);
            ProgramState programState=statement2.execute(program);
            assertEquals(executionStack.pop().toString(),"b = 2");
        }
        catch(InterpretorException error){
            assert(false);
        }

        try{
            ProgramState program=new ProgramState(executionStack,symbolTable,outputList,fileTable,new MyHeap<>(),statement3);
            ProgramState programState=statement3.execute(program);
            assert(false);
        }
        catch(InterpretorException error){
            assert(true);
        }
    }

    @Test
    public void testNopStatement(){
        MyStack<IStatement> executionStack = new MyStack<>();
        Dictionary<String, IValue> symbolTable = new Dictionary<>();
        List<IValue> outputList = new List<>();
        IDictionary<StringValue, BufferedReader> fileTable=new Dictionary<>();

        IStatement statement1=new NopStatement();
        assertEquals(statement1.toString(),"");

        try{
            ProgramState program=new ProgramState(executionStack,symbolTable,outputList,fileTable,new MyHeap<>(),statement1);
            ProgramState programState=statement1.execute(program);
            assert(executionStack.pop() instanceof NopStatement);
        }
        catch(InterpretorException error){
            assert(false);
        }
    }

    @Test
    public void testPrintStatement(){
        MyStack<IStatement> executionStack = new MyStack<>();
        Dictionary<String, IValue> symbolTable = new Dictionary<>();
        List<IValue> outputList = new List<>();
        IDictionary<StringValue, BufferedReader> fileTable=new Dictionary<>();
        symbolTable.add("a",new IntValue(100));

        IStatement statement1=new PrintStatement(new VariableExpression("a"));

        assertEquals(statement1.toString(),"print(a)");

        try{
            ProgramState program=new ProgramState(executionStack,symbolTable,outputList,fileTable,new MyHeap<>(),statement1);
            ProgramState programState=statement1.execute(program);
            assertEquals(outputList.get(0).toString(),"100");
        }
        catch (InterpretorException error){
            assert(false);
        }

    }

    @Test
    public void testVariableDeclarationStatement(){
        MyStack<IStatement> executionStack = new MyStack<>();
        Dictionary<String, IValue> symbolTable = new Dictionary<>();
        List<IValue> outputList = new List<>();
        IDictionary<StringValue, BufferedReader> fileTable=new Dictionary<>();
        symbolTable.add("c",new IntValue(1));

        IStatement statement1=new VariableDeclarationStatement("a", new IntType());
        IStatement statement2=new VariableDeclarationStatement("b",new BoolType());
        IStatement statement3=new VariableDeclarationStatement("c",new BoolType());

        assertEquals(statement1.toString(),"int a");
        assertEquals(statement2.toString(),"boolean b");

        try{
            ProgramState program=new ProgramState(executionStack,symbolTable,outputList,fileTable,new MyHeap<>(),statement1);
            ProgramState programState=statement1.execute(program);
            assertEquals(symbolTable.lookup("a").toString(),"0");
        }
        catch(InterpretorException error){
            assert (false);
        }

        try{
            ProgramState program=new ProgramState(executionStack,symbolTable,outputList,fileTable,new MyHeap<>(),statement2);
            ProgramState programState=statement2.execute(program);
            assertEquals(symbolTable.lookup("b").toString(),"false");
        }
        catch(InterpretorException error){
            assert (false);
        }

        try{
            ProgramState program=new ProgramState(executionStack,symbolTable,outputList,fileTable,new MyHeap<>(),statement3);
            ProgramState programState=statement3.execute(program);
            assert (false);
        }
        catch(InterpretorException error){
            assert (true);
        }

    }

     */
}
