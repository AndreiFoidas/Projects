package Model.adt;

import Exception.InterpretorException;

import java.util.List;

public interface IList<T> {
    void add(T v);
    void remove(T v);
    int getSize();
    T get(int index) throws InterpretorException;
    String toString();
    void clearAll();
    String toFileFormat();
    List<T> getAll();
}
