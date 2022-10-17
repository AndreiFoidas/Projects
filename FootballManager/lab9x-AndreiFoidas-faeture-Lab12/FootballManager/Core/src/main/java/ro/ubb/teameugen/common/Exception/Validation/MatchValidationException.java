package ro.ubb.teameugen.common.Exception.Validation;

import ro.ubb.teameugen.common.Domain.Entities.Match;

/**
 * Exception in case the validation of the Match entity fails.
 *
 * @see Match
 */
public class MatchValidationException extends ValidatorException {

    /**
     * @param message User-friendly message to describe the reason for the exception.
     */
    public MatchValidationException(String message) {
        super(message);
    }

    public MatchValidationException() {
        super("Validation of the provided Match entity failed.");
    }

}