package Model.value;

import Model.type.IType;
import Model.type.ReferenceType;

public class ReferenceValue implements IValue{
    private final int address;
    private final IType locationType;

    public ReferenceValue(int address, IType type){
        this.address=address;
        this.locationType=type;
    }

    public int getAddress(){
        return this.address;
    }

    public boolean equals(Object another){
        if(another instanceof ReferenceValue) //idk if good
            return (((ReferenceValue) another).getAddress()==this.address && ((ReferenceValue) another).getType()==this.locationType);
        else
            return false;
    }

    @Override
    public IType getType() {
        return new ReferenceType(this.locationType);
    }

    public IType getLocationType(){
        return this.locationType;
    }

    @Override
    public String toString(){
        //return Integer.toString(this.address)+"->"+this.getType().toString();
        return "("+Integer.toString(this.address)+","+this.locationType.toString()+")";
    }
}
