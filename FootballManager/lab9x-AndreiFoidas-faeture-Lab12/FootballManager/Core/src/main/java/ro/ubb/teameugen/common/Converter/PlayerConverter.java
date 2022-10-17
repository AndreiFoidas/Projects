package ro.ubb.teameugen.common.Converter;

import org.springframework.stereotype.Component;
import ro.ubb.teameugen.common.Domain.DTOs.ClubDto;
import ro.ubb.teameugen.common.Domain.DTOs.PlayerDto;
import ro.ubb.teameugen.common.Domain.Entities.Address;
import ro.ubb.teameugen.common.Domain.Entities.Club;
import ro.ubb.teameugen.common.Domain.Entities.Player;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class PlayerConverter extends BaseConverter<Player, PlayerDto>{
    
    @Override
    public Player convertDtoToModel(PlayerDto dto) {
        ClubConverter cc = new ClubConverter();
        Club club=cc.convertDtoToModel(dto.getClub());
        var model = Player.builder().playerName(dto.getPlayerName()).birthday(LocalDate.parse(dto.getBirthday(), DateTimeFormatter.ofPattern("dd-MM-yyyy"))).wage(dto.getWage()).mainPosition(dto.getMainPosition())
                .address(Address.builder().street(dto.getStreet()).city(dto.getCity()).build())
                .club(club)
                .build();
        model.setId(dto.getId());
        return model;
    }

    @Override
    public PlayerDto convertModelToDto(Player player) {
        ClubConverter cc = new ClubConverter();
        ClubDto clubDto=cc.convertModelToDto(player.getClub());
        PlayerDto playerDto = new PlayerDto(player.getPlayerName(), player.getBirthday().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")), player.getWage(), clubDto, player.getMainPosition(),
                player.getAddress().getStreet(), player.getAddress().getCity());
        playerDto.setId(player.getId());
        return playerDto;
    }

}
