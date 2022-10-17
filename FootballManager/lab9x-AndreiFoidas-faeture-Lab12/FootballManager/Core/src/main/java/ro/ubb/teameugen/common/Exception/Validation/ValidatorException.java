package ro.ubb.teameugen.common.Exception.Validation;

/**
 * Exception in case of a Validation violation.
 */
public class ValidatorException extends RuntimeException {

    /**
     * @param message User-friendly message to describe the reason for the exception.
     */
    public ValidatorException(String message) {
        super(message);
    }

    public ValidatorException() {
        super("Validation of the provided entity failed.");
    }

}
