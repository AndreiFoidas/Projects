package ro.ubb.teameugen.common.Domain.DTOs;

import java.io.Serializable;
import java.util.Set;
import java.util.StringJoiner;

public class MatchesDto implements Serializable {
    private Set<MatchDto> matchDtoSet;

    public MatchesDto() {}

    public MatchesDto(Set<MatchDto> matchDtoSet) {
        this.matchDtoSet = matchDtoSet;
    }

    public Set<MatchDto> getMatchDtoSet() {
        return this.matchDtoSet;
    }

    public void setMatchDtoSet(Set<MatchDto> matchDtoSet) {
        this.matchDtoSet = matchDtoSet;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", MatchesDto.class.getSimpleName() + "[", "]")
                .add("matchDtoSet=" + this.matchDtoSet)
                .toString();
    }
}
