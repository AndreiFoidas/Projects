package Model.adt;
import Exception.InterpretorException;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class MyStack<T> implements IStack<T> {
    private Stack<T> stack;

    public MyStack(){
        this.stack=new Stack<T>();
    }

    @Override
    public T pop() throws InterpretorException {
        if(isEmpty())
            throw new InterpretorException("Stack is empty!");
        else
            return this.stack.pop();
    }

    @Override
    public void push(T v) {
        this.stack.push(v);
    }

    @Override
    public boolean isEmpty() {
        return this.stack.empty();
    }

    @Override
    public String toString(){
        StringBuilder string=new StringBuilder();
        string.append("(");
        for(T element:this.stack){
            string.append(element);
            string.append(",");
        }
        if(!this.stack.empty())
            string.deleteCharAt(string.length()-1);
        string.append(")");
        return string.toString();
    }

    @Override
    public void clearAll() {
        this.stack=new Stack<T>();
    }

    @Override
    public String toFileFormat() {
        StringBuilder string=new StringBuilder();
        for(T element:this.stack){
            string.append(element);
            string.append(System.getProperty("line.separator"));
        }
        return string.toString();
    }

    @Override
    public List<T> getAll() {
        List<T> result=new ArrayList<>();
        for(T element:this.stack) {
            result.add(element);
        }
        return result;
    }
}
