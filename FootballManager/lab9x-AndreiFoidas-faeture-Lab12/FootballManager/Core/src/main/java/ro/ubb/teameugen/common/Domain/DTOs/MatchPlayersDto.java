package ro.ubb.teameugen.common.Domain.DTOs;

import java.io.Serializable;
import java.util.Set;
import java.util.StringJoiner;

public class MatchPlayersDto implements Serializable {

    private Set<MatchPlayerDto> mprDtoSet;

    public MatchPlayersDto() {}

    public MatchPlayersDto(Set<MatchPlayerDto> mprDtoSet){
        this.mprDtoSet=mprDtoSet;
    }

    public Set<MatchPlayerDto> getMprDtoSet(){
        return this.mprDtoSet;
    }

    public void setMprDtoSet(Set<MatchPlayerDto> mprDtoSet) {
        this.mprDtoSet = mprDtoSet;
    }

    @Override
    public String toString(){
        return new StringJoiner(", ", MatchPlayersDto.class.getSimpleName()+"[","]")
                .add("mprDtoSet="+this.mprDtoSet)
                .toString();
    }
}
