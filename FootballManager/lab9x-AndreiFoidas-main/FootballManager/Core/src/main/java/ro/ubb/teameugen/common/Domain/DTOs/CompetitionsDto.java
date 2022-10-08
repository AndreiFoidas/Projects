package ro.ubb.teameugen.common.Domain.DTOs;

import java.io.Serializable;
import java.util.Set;
import java.util.StringJoiner;

public class CompetitionsDto implements Serializable {
    private Set<CompetitionDto> competitionDtoSet;

    public CompetitionsDto() {}

    public CompetitionsDto(Set<CompetitionDto> competitionDtoSet) {
        this.competitionDtoSet = competitionDtoSet;
    }

    public Set<CompetitionDto> getCompetitionDtoSet() {
        return competitionDtoSet;
    }

    public void setCompetitionDtoSet(Set<CompetitionDto> competitionDtoSet) {
        this.competitionDtoSet = competitionDtoSet;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", CompetitionsDto.class.getSimpleName() + "[", "]")
                .add("competitionDtoSet=" + this.competitionDtoSet)
                .toString();
    }
}
