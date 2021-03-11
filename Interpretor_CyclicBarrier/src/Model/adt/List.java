package Model.adt;

import Exception.InterpretorException;

import java.util.ArrayList;

public class List<T> implements IList<T> {
    private ArrayList<T> list;

    public List(){
        this.list=new ArrayList<T>();
    }

    @Override
    public void add(T v) {
        this.list.add(v);
    }

    @Override
    public void remove(T v) {
        this.list.remove(v);
    }

    @Override
    public int getSize() {
        return this.list.size();
    }

    @Override
    public T get(int index) throws InterpretorException {
        if(index<0 || index>this.getSize())
            throw new InterpretorException("Index out of bounds!");
        else
            return this.list.get(index);
    }

    @Override
    public String toString(){
        StringBuilder string=new StringBuilder();
        string.append("(");
        for(T element:this.list){
            string.append(element);
            if(element!=this.list.get(this.list.size()-1))
                string.append(",");
        }
        string.append(")");
        return string.toString();
    }

    @Override
    public void clearAll() {
        this.list=new ArrayList<T>();
    }

    @Override
    public String toFileFormat() {
        StringBuilder string=new StringBuilder();
        for(T element:this.list){
            string.append(element);
            string.append(System.getProperty("line.separator"));
        }
        return string.toString();
    }

    @Override
    public java.util.List<T> getAll() {
        return this.list;
    }

}
