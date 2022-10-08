package ro.ubb.teameugen.common.Converter;

import org.springframework.stereotype.Component;
import ro.ubb.teameugen.common.Domain.DTOs.MatchDto;
import ro.ubb.teameugen.common.Domain.Entities.Match;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class MatchConverter extends BaseConverter<Match, MatchDto> {
    @Override
    public Match convertDtoToModel(MatchDto dto) {
        var model = new Match(dto.getClubID1(), dto.getClubID2(), LocalDate.parse(dto.getDate(), DateTimeFormatter.ofPattern("dd-MM-yyyy")), dto.getStadiumName(), dto.getCompetitionID(), dto.getAttendance());
        model.setId(dto.getId());
        return model;
    }

    @Override
    public MatchDto convertModelToDto(Match match) {
        return new MatchDto(match.getId(), match.getClubID1(), match.getClubID2(), match.getDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")), match.getStadiumName(), match.getCompetitionID(), match.getAttendance());
    }
}
