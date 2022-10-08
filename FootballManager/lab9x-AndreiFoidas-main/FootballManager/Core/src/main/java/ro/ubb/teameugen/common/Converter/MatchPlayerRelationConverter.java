package ro.ubb.teameugen.common.Converter;

import org.springframework.stereotype.Component;
import ro.ubb.teameugen.common.Domain.DTOs.MatchPlayerRelationDto;
import ro.ubb.teameugen.common.Domain.Entities.MatchPlayerRelation;

@Component
public class MatchPlayerRelationConverter extends BaseConverter<MatchPlayerRelation, MatchPlayerRelationDto> {

    @Override
    public MatchPlayerRelation convertDtoToModel(MatchPlayerRelationDto dto) {
        var model=new MatchPlayerRelation(dto.getMatchID(), dto.getPlayerID(), dto.getGoalsScored(), dto.getMinutesPlayed());
        model.setId(dto.getId());
        return model;
    }

    @Override
    public MatchPlayerRelationDto convertModelToDto(MatchPlayerRelation matchPlayerRelation) {
        return new MatchPlayerRelationDto(matchPlayerRelation.getId(), matchPlayerRelation.getMatchID(),
                matchPlayerRelation.getPlayerID(), matchPlayerRelation.getGoalsScored(), matchPlayerRelation.getMinutesPlayed());
    }
}
