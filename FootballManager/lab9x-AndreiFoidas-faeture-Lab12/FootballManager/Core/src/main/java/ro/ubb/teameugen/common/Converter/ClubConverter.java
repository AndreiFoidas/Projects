package ro.ubb.teameugen.common.Converter;

import org.springframework.stereotype.Component;
import ro.ubb.teameugen.common.Domain.DTOs.ClubDto;
import ro.ubb.teameugen.common.Domain.Entities.Club;

@Component
public class ClubConverter extends BaseConverter<Club, ClubDto> {

    @Override
    public Club convertDtoToModel(ClubDto dto) {

        var model = Club.builder().name(dto.getName()).country(dto.getCountry()).budget(dto.getBudget()).ownerName(dto.getOwnerName())
                .managerName(dto.getManagerName()).stadiumName(dto.getStadiumName())
                .build();
        model.setId(dto.getId());
        return model;
    }

    @Override
    public ClubDto convertModelToDto(Club club) {
        ClubDto clubDto = new ClubDto(club.getName(), club.getCountry(), club.getBudget(), club.getOwnerName(), club.getManagerName(), club.getStadiumName());
        clubDto.setId(club.getId());
        return clubDto;
    }

}
