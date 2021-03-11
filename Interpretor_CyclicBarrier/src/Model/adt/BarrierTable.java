package Model.adt;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import Exception.InterpretorException;

public class BarrierTable<T1, T2> implements IBarrierTable<T1, T2>{
    HashMap<T1,T2> barrierTable;
    Lock lock;

    public BarrierTable(){
        this.barrierTable =new HashMap<T1, T2>();
        this.lock=new ReentrantLock();
    }

    @Override
    public void add(T1 v1, T2 v2) {
        this.lock.lock();
        this.barrierTable.put(v1,v2);
        this.lock.unlock();
    }

    @Override
    public void update(T1 v1, T2 v2) {
        this.lock.lock();
        this.barrierTable.put(v1,v2);
        this.lock.unlock();
    }

    @Override
    public void remove(T1 id) throws InterpretorException {
        if(!isDefined(id))
            throw new InterpretorException("Key does not exist!");
        else{
            this.barrierTable.remove(id);
        }
    }

    @Override
    public T2 lookup(T1 id) {
        this.lock.lock();
        T2 re = this.barrierTable.get(id);
        this.lock.unlock();
        return re;
    }

    @Override
    public Set<T1> getKeySet() {
        return this.barrierTable.keySet();
    }

    @Override
    public Collection<T2> getValues() {
        return this.barrierTable.values();
    }

    @Override
    public boolean isDefined(T1 id) {
        return this.barrierTable.containsKey(id);
    }

    @Override
    public HashMap<T1, T2> getContent() {
        return this.barrierTable;
    }

    @Override
    public void clearAll() {
        this.barrierTable =new HashMap<T1,T2>();
    }

    @Override
    public String toString(){
        StringBuilder string=new StringBuilder();
        for(HashMap.Entry<T1,T2> element:this.barrierTable.entrySet()){
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
    public String toFileFormat() {
        StringBuilder string=new StringBuilder();
        for(HashMap.Entry<T1,T2> element:this.barrierTable.entrySet()){
            string.append(element.getKey());
            string.append(" -> ");
            string.append(element.getValue());
            string.append(System.getProperty("line.separator"));
        }
        return string.toString();
    }
}
