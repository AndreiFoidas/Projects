package Model.value;

import Model.type.BoolType;
import Model.type.IType;

public class BoolValue implements IValue{
    private final boolean value;

    public BoolValue(boolean value){
        this.value=value;
    }

    public boolean getValue(){
        return this.value;
    }

    public boolean equals(Object another){
        if(another instanceof BoolValue) {
            return ((BoolValue) another).getValue() == this.value;
        }
        else
            return false;
    }

    @Override
    public IType getType() {
        return new BoolType();
    }

    @Override
    public String toString(){
        return Boolean.toString(this.value);
    }
}
