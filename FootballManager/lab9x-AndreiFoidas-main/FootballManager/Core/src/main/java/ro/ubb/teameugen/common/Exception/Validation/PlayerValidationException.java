package ro.ubb.teameugen.common.Exception.Validation;

import ro.ubb.teameugen.common.Domain.Entities.Player;

/**
 * Exception in case the validation of the Player entity fails.
 *
 * @see Player
 */
public class PlayerValidationException extends ValidatorException {

    /**
     * @param message User-friendly message to describe the reason for the exception.
     */
    public PlayerValidationException(String message) {
        super(message);
    }

    public PlayerValidationException() {
        super("Validation of the provided Player entity failed");
    }

}
