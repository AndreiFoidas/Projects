package ro.ubb.teameugen.common.Exception.Service;

/**
 * Exception to be thrown by the Service part of the Application.
 */
public class ServiceException extends RuntimeException {

    /**
     * @param message User-friendly message to describe the reason for the exception.
     */
    public ServiceException(String message) {
        super(message);
    }

    public ServiceException() {
        super("A service error has occurred.");
    }

}
