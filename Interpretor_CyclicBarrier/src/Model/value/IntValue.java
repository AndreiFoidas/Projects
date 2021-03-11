package Model.value;

import Model.type.IType;
import Model.type.IntType;

public class IntValue implements IValue{
    private final int value;

    public IntValue(int value){
        this.value=value;
    }

    public int getValue(){
        return this.value;
    }

    public boolean equals(Object another){
        if(another instanceof IntValue) {
            return ((IntValue) another).getValue() == this.value;
        }
        else
            return false;
    }

    @Override
    public IType getType() {
        return new IntType();
    }

    @Override
    public String toString(){
        return Integer.toString(this.value);
    }
}
