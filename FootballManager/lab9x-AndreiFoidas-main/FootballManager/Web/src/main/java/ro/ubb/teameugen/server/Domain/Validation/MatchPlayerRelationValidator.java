package ro.ubb.teameugen.server.Domain.Validation;

import ro.ubb.teameugen.common.Domain.Entities.MatchPlayerRelation;
import ro.ubb.teameugen.server.Domain.Validation.Interfaces.BaseValidator;
import ro.ubb.teameugen.common.Exception.Validation.MPRelationValidationException;

import java.util.Optional;

/**
 * Validator for the MatchPlayerRelation entity.
 *
 * @see MatchPlayerRelation
 */
public class MatchPlayerRelationValidator implements BaseValidator<MatchPlayerRelation> {

    /**
     * Attempts to validate the given MatchPlayerRelation entity.
     *
     * @param MPRelation The MatchPlayerRelation entity to be validated.
     * @throws MPRelationValidationException If the entity failed validation.
     */
    @Override
    public void validate(MatchPlayerRelation MPRelation) throws MPRelationValidationException {
        Optional.of(MPRelation.getMatchID())
                .filter(id -> id > 0)
                .orElseThrow(() -> new MPRelationValidationException("Match ID cannot be negative or zero."));

        Optional.of(MPRelation.getPlayerID())
                .filter(id -> id > 0)
                .orElseThrow(() -> new MPRelationValidationException("Player ID cannot be negative or zero."));

        Optional.of(MPRelation.getGoalsScored())
                .filter(goals -> goals >= 0)
                .orElseThrow(() -> new MPRelationValidationException("Number of goals scored cannot be negative."));

        Optional.of(MPRelation.getMinutesPlayed())
                .filter(minutesPlayed -> minutesPlayed >= 0 && minutesPlayed <= 120)
                .orElseThrow(() -> new MPRelationValidationException("Invalid number of minutes played."));

        // TO-DO: check that the player belongs to one of the teams in the match
        // we're gonna die if we transfer players and they no longer belong to a team, perhaps
        // check the date of the match against the transfer date or sth
    }
}