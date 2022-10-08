package ro.ubb.teameugen.common.Converter;

import org.springframework.stereotype.Component;
import ro.ubb.teameugen.common.Domain.DTOs.ClubDto;
import ro.ubb.teameugen.common.Domain.Entities.Club;

@Component
public class ClubConverter extends BaseConverter<Club, ClubDto> {

    @Override
    public Club convertDtoToModel(ClubDto dto) {
        var model = new Club(dto.getName(), dto.getCountry(), dto.getBudget(), dto.getOwnerName(), dto.getManagerName(), dto.getStadiumName());
        model.setId(dto.getId());
        return model;
    }

    @Override
    public ClubDto convertModelToDto(Club club) {
        return new ClubDto(club.getId(), club.getName(), club.getCountry(), club.getBudget(), club.getOwnerName(), club.getManagerName(), club.getStadiumName());
    }

}
