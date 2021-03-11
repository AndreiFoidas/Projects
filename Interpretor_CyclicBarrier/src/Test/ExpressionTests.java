package Test;
import Model.adt.Dictionary;
import Model.adt.MyHeap;
import Model.expression.*;
import Model.value.BoolValue;
import Model.value.IValue;
import Model.value.IntValue;
import Exception.InterpretorException;
import org.junit.Test;

import static org.junit.Assert.*;

public class ExpressionTests {

    /*
    @Test
    public void testArithmeticExpression(){
        Dictionary<String, IValue> symbolTable=new Dictionary<>();
        MyHeap<Integer, IValue> memoryHeap= new MyHeap<>();
        Expression expression1=new ArithmeticExpression('+', new ValueExpression(new IntValue(3)),new ValueExpression(new IntValue(5)));
        Expression expression2=new ArithmeticExpression('/',new ValueExpression(new IntValue(10)),new ValueExpression(new IntValue(0)));
        Expression expression3=new ArithmeticExpression('*',new ValueExpression(new BoolValue(true)),new ValueExpression(new IntValue(1)));
        Expression expression4=new ArithmeticExpression(new ValueExpression(new IntValue(3)),new ValueExpression(new IntValue(5)),'-');
        Expression expression5=new ArithmeticExpression(new ValueExpression(new IntValue(3)),new ValueExpression(new IntValue(5)),'*');
        Expression expression6=new ArithmeticExpression(new ValueExpression(new IntValue(3)),new ValueExpression(new IntValue(5)),'/');
        Expression expression7=new ArithmeticExpression(new ValueExpression(new IntValue(3)),new ValueExpression(new IntValue(5)),'A');
        Expression expression8=new ArithmeticExpression(new ValueExpression(new IntValue(3)),new ValueExpression(new BoolValue(false)),'-');

        ArithmeticExpression expression0=(ArithmeticExpression) expression1;
        assertEquals(expression0.getOperator(),'+');
        assertEquals(expression0.getFirst().toString(), "3");
        assertEquals(expression0.getSecond().toString(),"5");

        try {
            IntValue result1 = (IntValue) expression1.evaluate(symbolTable,memoryHeap);
            assertEquals(result1.getValue(),8);
            assertEquals(result1.toString(), "8");
            assertEquals(expression1.toString(),"3 + 5");
            IntValue result4=(IntValue) expression4.evaluate(symbolTable,memoryHeap);
            IntValue result5=(IntValue) expression5.evaluate(symbolTable,memoryHeap);
            IntValue result6=(IntValue) expression6.evaluate(symbolTable,memoryHeap);
            assertEquals(result4.getValue(),-2);
            assertEquals(result5.getValue(),15);
            assertEquals(result6.getValue(),0);
        }
        catch(InterpretorException error) {
                assert(false);
        }

        try{
            assertEquals(expression2.toString(),"10 / 0");
            IValue result2=expression2.evaluate(symbolTable,memoryHeap);
            assert(false);
        }
        catch(InterpretorException error){
            assert(true);
        }

        try{
            assertEquals(expression3.toString(), "true * 1");
            IValue result3=expression3.evaluate(symbolTable,memoryHeap);
            assert(false);
        }
        catch(InterpretorException error) {
            assert (true);
        }

        try{
            assertEquals(expression7.toString(), "3 A 5");
            IValue result7=expression7.evaluate(symbolTable,memoryHeap);
            assert(false);
        }
        catch(InterpretorException error) {
            assert (true);
        }

        try{
            assertEquals(expression8.toString(), "3 - false");
            IValue result8=expression8.evaluate(symbolTable,memoryHeap);
            assert(false);
        }
        catch(InterpretorException error) {
            assert (true);
        }
    }

    @Test
    public void testLogicExpression(){
        Dictionary<String, IValue> symbolTable=new Dictionary<>();
        MyHeap<Integer, IValue> memoryHeap=new MyHeap<>();

        Expression expression1=new LogicExpression("AND",new ValueExpression(new BoolValue(true)),new ValueExpression(new BoolValue(false)));
        Expression expression2=new LogicExpression("+",new ValueExpression(new BoolValue(true)),new ValueExpression(new BoolValue(false)));
        Expression expression3=new LogicExpression(new ValueExpression(new BoolValue(true)),new ValueExpression(new IntValue(0)),"OR");
        Expression expression4=new LogicExpression(new ValueExpression(new IntValue(0)),new ValueExpression(new BoolValue(true)),"OR");
        Expression expression5=new LogicExpression(new ValueExpression(new BoolValue(true)),new ValueExpression(new BoolValue(false)),"OR");

        LogicExpression expression0=(LogicExpression) expression1;
        assertEquals(expression0.getOperator(),"AND");
        assertEquals(expression0.getFirst().toString(),"true");
        assertEquals(expression0.getSecond().toString(),"false");

        try{
            BoolValue result1=(BoolValue) expression1.evaluate(symbolTable,memoryHeap);
            assertFalse(result1.getValue());
            assertEquals(result1.toString(), "false");
            assertEquals(expression1.toString(),"true AND false");
        }
        catch(InterpretorException error) {
            assert(false);
        }

        try{
            assertEquals(expression2.toString(),"true + false");
            IValue result2=expression2.evaluate(symbolTable,memoryHeap);
            assert(false);
        }
        catch(InterpretorException error) {
            assert(true);
        }

        try{
            assertEquals(expression3.toString(),"true OR 0");
            IValue result3=expression3.evaluate(symbolTable,memoryHeap);
            assert(false);
        }
        catch(InterpretorException error) {
            assert(true);
        }

        try{
            assertEquals(expression4.toString(),"0 OR true");
            IValue result4=expression4.evaluate(symbolTable,memoryHeap);
            assert(false);
        }
        catch(InterpretorException error) {
            assert(true);
        }

        try{
            assertEquals(expression5.toString(),"true OR false");
            BoolValue result5=(BoolValue)expression5.evaluate(symbolTable,memoryHeap);
            assertTrue(result5.getValue());
        }
        catch(InterpretorException error) {
            assert(false);
        }
    }

    @Test
    public void testValueExpression(){
        Dictionary<String, IValue> symbolTable=new Dictionary<>();
        MyHeap<Integer, IValue> memoryHeap=new MyHeap<>();

        Expression expression0=new ValueExpression(new IntValue(100));
        Expression expression1=new ValueExpression(new BoolValue(true));

        try {
            IntValue result0 = (IntValue) expression0.evaluate(symbolTable,memoryHeap);
            assertEquals(result0.getValue(),100);
            assertEquals(expression0.toString(),"100");

            BoolValue result1=(BoolValue) expression1.evaluate(symbolTable,memoryHeap);
            assertTrue(result1.getValue());
            assertEquals(expression1.toString(),"true");
        }
        catch(InterpretorException exception) {
            assert(false);
        }
    }

    @Test
    public void testVariableExpression(){
        Dictionary<String, IValue> symbolTable=new Dictionary<>();
        MyHeap<Integer, IValue> memoryHeap=new MyHeap<>();

        symbolTable.update("a",new IntValue(5));
        symbolTable.update("b",new BoolValue(true));

        Expression expression0=new VariableExpression("a");
        Expression expression1=new VariableExpression("b");
        Expression expression2=new VariableExpression("c");

        try {
            IntValue result0=(IntValue)expression0.evaluate(symbolTable,memoryHeap);
            assertEquals(result0.getValue(),5);
            assertEquals(expression0.toString(),"a");

            BoolValue result1=(BoolValue)expression1.evaluate(symbolTable,memoryHeap);
            assertTrue(result1.getValue());
            assertEquals(expression1.toString(),"b");
        }
        catch(InterpretorException error) {
            assert(false);
        }
    }

     */
}
