package ro.ubb.teameugen.common.Domain.DTOs;

import java.io.Serializable;
import java.util.Set;
import java.util.StringJoiner;

public class PlayersDto implements Serializable {
    private Set<PlayerDto> playerDtoSet;

    public PlayersDto() {}

    public PlayersDto(Set<PlayerDto> playerDtoSet) {
        this.playerDtoSet = playerDtoSet;
    }

    public Set<PlayerDto> getPlayerDtoSet() {
        return playerDtoSet;
    }

    public void setPlayerDtoSet(Set<PlayerDto> playerDtoSet) {
        this.playerDtoSet = playerDtoSet;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", PlayersDto.class.getSimpleName() + "[", "]")
                .add("playerDtoSet=" + playerDtoSet)
                .toString();
    }

}
