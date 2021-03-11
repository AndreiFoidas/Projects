package Model.adt;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;
import Exception.InterpretorException;

public interface IBarrierTable<T1, T2>{
    void add(T1 v1, T2 v2);
    void update(T1 v1, T2 v2);
    void remove(T1 id) throws InterpretorException;
    T2 lookup(T1 id);
    public Set<T1> getKeySet();
    public Collection<T2> getValues();
    boolean isDefined(T1 id);
    HashMap<T1,T2> getContent();
    String toString();
    void clearAll();
    String toFileFormat();
}
