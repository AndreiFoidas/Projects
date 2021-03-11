package Model.type;

import Model.value.IValue;
import Model.value.IntValue;

public class IntType implements IType{

    public boolean equals(Object another){
        if(another instanceof IntType)
            return true;
        else
            return false;
    }

    @Override
    public IValue defaultValue() {
        return new IntValue(0);
    }

    @Override
    public String toString(){
        return "int";
    }
}
