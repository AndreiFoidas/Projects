package ro.ubb.teameugen.common.Converter;

import org.springframework.stereotype.Component;
import ro.ubb.teameugen.common.Domain.DTOs.MatchDto;
import ro.ubb.teameugen.common.Domain.DTOs.MatchPlayerDto;
import ro.ubb.teameugen.common.Domain.DTOs.PlayerDto;
import ro.ubb.teameugen.common.Domain.Entities.Match;
import ro.ubb.teameugen.common.Domain.Entities.MatchPlayer;
import ro.ubb.teameugen.common.Domain.Entities.Player;

@Component
public class MatchPlayerConverter {

    public MatchPlayer convertDtoToModel(MatchPlayerDto dto) {
        MatchConverter mc = new MatchConverter();
        PlayerConverter pc = new PlayerConverter();
        Match match = mc.convertDtoToModel(dto.getMatch());
        Player player = pc.convertDtoToModel(dto.getPlayer());
        var model=new MatchPlayer(match, player, dto.getGoalsScored(), dto.getMinutesPlayed());
        return model;
    }

    public MatchPlayerDto convertModelToDto(MatchPlayer matchPlayer) {
        MatchConverter mc = new MatchConverter();
        PlayerConverter pc = new PlayerConverter();
        MatchDto matchDto = mc.convertModelToDto(matchPlayer.getMatch());
        PlayerDto playerDto = pc.convertModelToDto(matchPlayer.getPlayer());
        var model = new MatchPlayerDto(matchDto, playerDto, matchPlayer.getGoalsScored(), matchPlayer.getMinutesPlayed());
        return model;
    }
}
