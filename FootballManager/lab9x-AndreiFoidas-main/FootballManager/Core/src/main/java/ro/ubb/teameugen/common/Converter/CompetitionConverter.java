package ro.ubb.teameugen.common.Converter;

import org.springframework.stereotype.Component;
import ro.ubb.teameugen.common.Domain.DTOs.CompetitionDto;
import ro.ubb.teameugen.common.Domain.Entities.Competition;

@Component
public class CompetitionConverter extends BaseConverter<Competition, CompetitionDto> {
    @Override
    public Competition convertDtoToModel(CompetitionDto dto) {
        var model = new Competition(dto.getName(), dto.getPrizePool(), dto.getFirstYear());
        model.setId(dto.getId());
        return model;
    }

    @Override
    public CompetitionDto convertModelToDto(Competition competition) {
        return new CompetitionDto(competition.getId(), competition.getName(), competition.getPrizePool(), competition.getFirstYear());
    }
}
