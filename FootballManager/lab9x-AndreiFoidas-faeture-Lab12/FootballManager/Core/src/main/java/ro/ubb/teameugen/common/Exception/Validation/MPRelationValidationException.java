package ro.ubb.teameugen.common.Exception.Validation;

import ro.ubb.teameugen.common.Domain.Entities.MatchPlayer;

/**
 * Exception in case the validation of the MatchPlayerRelation entity fails.
 *
 * @see MatchPlayer
 */
public class MPRelationValidationException extends ValidatorException {

    /**
     * @param message User-friendly message to describe the reason for the exception.
     */
    public MPRelationValidationException(String message) {
        super(message);
    }

    public MPRelationValidationException() {
        super("Validation of the provided Match-Player Relation entity failed.");
    }

}