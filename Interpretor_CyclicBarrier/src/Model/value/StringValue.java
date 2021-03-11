package Model.value;

import Model.type.IType;
import Model.type.IntType;
import Model.type.StringType;

public class StringValue implements IValue{
    private final String value;

    public StringValue(String value){
        this.value=value;
    }

    public String getValue(){
        return this.value;
    }

    public boolean equals(Object another){
        if(another instanceof StringValue) {
            return ((StringValue) another).getValue().equals(this.value);
        }
        else
            return false;
    }

    @Override
    public IType getType() {
        return new StringType();
    }

    @Override
    public String toString(){
        return this.value;
    }
}
