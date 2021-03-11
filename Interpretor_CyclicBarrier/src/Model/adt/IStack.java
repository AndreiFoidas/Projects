package Model.adt;

import Exception.InterpretorException;

import java.util.List;

public interface IStack<T> {

    T pop() throws InterpretorException;
    void push(T v);
    boolean isEmpty();
    String toString();
    void clearAll();
    String toFileFormat();
    List<T> getAll();
}

