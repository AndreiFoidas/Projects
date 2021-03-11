package Model.type;

import Model.value.IValue;

public interface IType {
    String toString();
    IValue defaultValue();
}
