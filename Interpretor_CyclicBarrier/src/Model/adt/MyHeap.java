package Model.adt;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import Exception.InterpretorException;

public class MyHeap<T1, T2> implements IHeap<T1, T2>{
    private AtomicInteger freeAddress;
    private HashMap<T1, T2> heap;

    public MyHeap() {
        this.heap = new HashMap<T1,T2>();
        this.freeAddress=new AtomicInteger(0);
    }

    public void setContent(HashMap<T1, T2> newMap){
        this.heap =newMap;
    }

    public HashMap<T1, T2> getContent(){
        return this.heap;
    }

    @Override
    public void add(T1 v1, T2 v2) {
        this.heap.put(v1,v2);
    }

    @Override
    public void update(T1 v1, T2 v2) {
        this.heap.put(v1,v2);
    }

    @Override
    public void remove(T1 id) throws InterpretorException {
        if(!isDefined(id))
            throw new InterpretorException("Key does not exist!");
        else{
            this.heap.remove(id);
        }
    }

    @Override
    public T2 lookup(T1 id) {
        return this.heap.get(id);
    }

    @Override
    public Set<T1> getKeySet() {
        return this.heap.keySet();
    }

    @Override
    public Collection<T2> getValues() {
        return this.heap.values();
    }

    @Override
    public boolean isDefined(T1 id) {
        return this.heap.containsKey(id);
    }

    @Override
    public String toString(){
        StringBuilder string=new StringBuilder();
        for(HashMap.Entry<T1,T2> element:this.heap.entrySet()){
            string.append("(");
            string.append(element.getKey());
            string.append("->");
            string.append(element.getValue());
            string.append(")");
            string.append(",");
        }
        if(string.length()!=0)
            string.deleteCharAt(string.length()-1);
        return string.toString();
    }

    @Override
    public int getNextFreeAddress() {
        this.freeAddress.incrementAndGet();
        return this.freeAddress.get();
    }

    public String toStringFile(){
        StringBuilder string=new StringBuilder();
        for(HashMap.Entry<T1,T2> element:this.heap.entrySet()){
            string.append("(");
            string.append(element.getKey());
            string.append(")");
            string.append(",");
        }
        if(string.length()!=0)
            string.deleteCharAt(string.length()-1);
        return string.toString();
    }

    @Override
    public void clearAll() {
        this.heap =new HashMap<T1,T2>();
    }

    @Override
    public String toFileFormat() {
        StringBuilder string=new StringBuilder();
        for(HashMap.Entry<T1,T2> element:this.heap.entrySet()){
            string.append(element.getKey());
            string.append(" -> ");
            string.append(element.getValue());
            string.append(System.getProperty("line.separator"));
        }
        return string.toString();
    }
}
