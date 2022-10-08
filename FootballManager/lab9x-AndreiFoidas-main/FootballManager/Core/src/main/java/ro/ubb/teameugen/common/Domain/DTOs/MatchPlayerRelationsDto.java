package ro.ubb.teameugen.common.Domain.DTOs;

import java.io.Serializable;
import java.util.Set;
import java.util.StringJoiner;

public class MatchPlayerRelationsDto implements Serializable {

    private Set<MatchPlayerRelationDto> mprDtoSet;

    public MatchPlayerRelationsDto() {}

    public MatchPlayerRelationsDto(Set<MatchPlayerRelationDto> mprDtoSet){
        this.mprDtoSet=mprDtoSet;
    }

    public Set<MatchPlayerRelationDto> getMprDtoSet(){
        return this.mprDtoSet;
    }

    public void setMprDtoSet(Set<MatchPlayerRelationDto> mprDtoSet) {
        this.mprDtoSet = mprDtoSet;
    }

    @Override
    public String toString(){
        return new StringJoiner(", ", MatchPlayerRelationsDto.class.getSimpleName()+"[","]")
                .add("mprDtoSet="+this.mprDtoSet)
                .toString();
    }
}
