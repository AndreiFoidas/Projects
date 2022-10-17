package ro.ubb.teameugen.server.Domain.Validation;

import ro.ubb.teameugen.common.Domain.Entities.MatchPlayer;
import ro.ubb.teameugen.server.Domain.Validation.Interfaces.BaseValidator;
import ro.ubb.teameugen.common.Exception.Validation.MPRelationValidationException;

import java.util.Optional;

/**
 * Validator for the MatchPlayerRelation entity.
 *
 * @see MatchPlayer
 */
public class MatchPlayerRelationValidator implements BaseValidator<MatchPlayer> {

    /**
     * Attempts to validate the given MatchPlayerRelation entity.
     *
     * @param MPRelation The MatchPlayerRelation entity to be validated.
     * @throws MPRelationValidationException If the entity failed validation.
     */
    @Override
    public void validate(MatchPlayer MPRelation) throws MPRelationValidationException {

        /*Optional.of(MPRelation.getGoalsScored())
                .filter(goals -> goals >= 0)
                .orElseThrow(() -> new MPRelationValidationException("Number of goals scored cannot be negative."));

        Optional.of(MPRelation.getMinutesPlayed())
                .filter(minutesPlayed -> minutesPlayed >= 0 && minutesPlayed <= 120)
                .orElseThrow(() -> new MPRelationValidationException("Invalid number of minutes played."));*/

        // TO-DO: check that the player belongs to one of the teams in the match
        // we're gonna die if we transfer players and they no longer belong to a team, perhaps
        // check the date of the match against the transfer date or sth
    }
}