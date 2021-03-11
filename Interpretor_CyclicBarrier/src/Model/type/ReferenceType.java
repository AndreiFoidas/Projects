package Model.type;

import Model.value.IValue;
import Model.value.ReferenceValue;

public class ReferenceType implements IType{
    private final IType inner;

    public ReferenceType(IType inner){
        this.inner=inner;
    }

    public IType getInner(){
        return this.inner;
    }

    public boolean equals(Object another){
        if(another instanceof ReferenceType)
            return inner.equals(((ReferenceType) another).getInner());
        else
            return false;
    }

    @Override
    public IValue defaultValue() {
        return new ReferenceValue(0,inner);
    }

    @Override
    public String toString(){
        return "Ref("+this.inner.toString()+")";
    }
}
