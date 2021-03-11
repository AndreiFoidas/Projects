package Model.type;

import Model.value.BoolValue;
import Model.value.IValue;

public class BoolType implements IType{

    public boolean equals(Object another){
        if(another instanceof BoolType)
            return true;
        else
            return false;
    }

    @Override
    public IValue defaultValue() {
        return new BoolValue(false);
    }

    @Override
    public String toString(){
        return "boolean";
    }

}
