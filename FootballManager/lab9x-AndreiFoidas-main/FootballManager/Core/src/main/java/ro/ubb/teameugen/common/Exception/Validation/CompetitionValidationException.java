package ro.ubb.teameugen.common.Exception.Validation;

import ro.ubb.teameugen.common.Domain.Entities.Competition;

/**
 * Exception in case the validation of the Competition entity fails.
 *
 * @see Competition
 */
public class CompetitionValidationException extends ValidatorException {

    /**
     * @param message User-friendly message to describe the reason for the exception.
     */
    public CompetitionValidationException(String message) {
        super(message);
    }

    public CompetitionValidationException() {
        super("Validation of the provided Competition entity failed.");
    }
}