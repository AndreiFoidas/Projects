package ro.ubb.teameugen.common.Exception.Validation;

import ro.ubb.teameugen.common.Domain.Entities.Club;

/**
 * Exception in case the validation of the Club entity fails.
 *
 * @see Club
 */
public class ClubValidationException extends ValidatorException {

    /**
     * @param message User-friendly message to describe the reason for the exception.
     */
    public ClubValidationException(String message) {
        super(message);
    }

    public ClubValidationException() {
        super("Validation of the provided Club entity failed.");
    }

}
