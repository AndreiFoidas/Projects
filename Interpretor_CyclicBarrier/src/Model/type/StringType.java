package Model.type;

import Model.value.IValue;
import Model.value.IntValue;
import Model.value.StringValue;

public class StringType implements IType{

    public boolean equals(Object another){
        if(another instanceof StringType)
            return true;
        else
            return false;
    }

    @Override
    public IValue defaultValue() {
        return new StringValue("");
    }

    @Override
    public String toString(){
        return "string";
    }
}
