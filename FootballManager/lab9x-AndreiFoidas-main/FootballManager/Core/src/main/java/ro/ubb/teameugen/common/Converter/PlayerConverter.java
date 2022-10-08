package ro.ubb.teameugen.common.Converter;

import org.springframework.stereotype.Component;
import ro.ubb.teameugen.common.Domain.DTOs.PlayerDto;
import ro.ubb.teameugen.common.Domain.Entities.Player;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class PlayerConverter extends BaseConverter<Player, PlayerDto>{
    
    @Override
    public Player convertDtoToModel(PlayerDto dto) {
        var model = new Player(dto.getPlayerName(), LocalDate.parse(dto.getBirthday(), DateTimeFormatter.ofPattern("dd-MM-yyyy")), dto.getWage(), dto.getClubID(), dto.getMainPosition());
        model.setId(dto.getId());
        return model;
    }

    @Override
    public PlayerDto convertModelToDto(Player player) {
        return new PlayerDto(player.getId(), player.getPlayerName(), player.getBirthday().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")), player.getWage(), player.getClubID(), player.getMainPosition());
    }

}
