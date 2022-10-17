package ro.ubb.teameugen.common.Converter;

import org.springframework.stereotype.Component;
import ro.ubb.teameugen.common.Domain.DTOs.ClubDto;
import ro.ubb.teameugen.common.Domain.DTOs.CompetitionDto;
import ro.ubb.teameugen.common.Domain.DTOs.MatchDto;
import ro.ubb.teameugen.common.Domain.Entities.Club;
import ro.ubb.teameugen.common.Domain.Entities.Competition;
import ro.ubb.teameugen.common.Domain.Entities.Match;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class MatchConverter extends BaseConverter<Match, MatchDto> {
    @Override
    public Match convertDtoToModel(MatchDto dto) {
        ClubConverter clc = new ClubConverter();
        CompetitionConverter coc = new CompetitionConverter();
        Club club1 = clc.convertDtoToModel(dto.getClub1());
        Club club2 = clc.convertDtoToModel(dto.getClub2());
        Competition competition = coc.convertDtoToModel(dto.getCompetition());

        var model = Match.builder().club1(club1).club2(club2).date(LocalDate.parse(dto.getDate(), DateTimeFormatter.ofPattern("dd-MM-yyyy")))
                .stadiumName(dto.getStadiumName()).competition(competition).attendance(dto.getAttendance())
                .build();
        model.setId(dto.getId());
        return model;
    }

    @Override
    public MatchDto convertModelToDto(Match match) {
        ClubConverter clc = new ClubConverter();
        CompetitionConverter coc = new CompetitionConverter();
        ClubDto clubDto1 = clc.convertModelToDto(match.getClub1());
        ClubDto clubDto2 = clc.convertModelToDto(match.getClub2());
        CompetitionDto competitionDto = coc.convertModelToDto(match.getCompetition());
        MatchDto matchDto = new MatchDto(clubDto1, clubDto2, match.getDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")), match.getStadiumName(), competitionDto, match.getAttendance());
        matchDto.setId(match.getId());
        return matchDto;
    }
}
