package ro.ubb.teameugen.common.Domain.DTOs;

import java.io.Serializable;
import java.util.Set;
import java.util.StringJoiner;

public class ClubsDto implements Serializable {

    private Set<ClubDto> clubDtoSet;

    public ClubsDto() {}

    public ClubsDto(Set<ClubDto> clubDtoSet) {
        this.clubDtoSet = clubDtoSet;
    }

    public Set<ClubDto> getClubDtoSet() {
        return clubDtoSet;
    }

    public void setClubDtoSet(Set<ClubDto> clubDtoSet) {
        this.clubDtoSet = clubDtoSet;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ClubsDto.class.getSimpleName() + "[", "]")
                .add("clubDtoSet=" + clubDtoSet)
                .toString();
    }
}
