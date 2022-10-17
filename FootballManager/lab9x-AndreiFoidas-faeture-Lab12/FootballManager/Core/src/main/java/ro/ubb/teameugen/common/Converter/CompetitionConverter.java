package ro.ubb.teameugen.common.Converter;

import org.springframework.stereotype.Component;
import ro.ubb.teameugen.common.Domain.DTOs.CompetitionDto;
import ro.ubb.teameugen.common.Domain.Entities.Competition;

@Component
public class CompetitionConverter extends BaseConverter<Competition, CompetitionDto> {
    @Override
    public Competition convertDtoToModel(CompetitionDto dto) {

        var model = Competition.builder().name(dto.getName()).prizePool(dto.getPrizePool()).firstYear(dto.getFirstYear())
                .build();
        model.setId(dto.getId());
        return model;
    }

    @Override
    public CompetitionDto convertModelToDto(Competition competition) {
        CompetitionDto competitionDto = new CompetitionDto(competition.getName(), competition.getPrizePool(), competition.getFirstYear());
        competitionDto.setId(competition.getId());
        return competitionDto;
    }
}
