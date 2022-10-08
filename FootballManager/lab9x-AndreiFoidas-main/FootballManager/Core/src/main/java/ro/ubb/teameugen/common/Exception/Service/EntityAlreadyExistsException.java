package ro.ubb.teameugen.common.Exception.Service;

/**
 * A ServiceException in case an Entity that was not supposed to exist, already exists.
 */
public class EntityAlreadyExistsException extends ServiceException {

    /**
     * Default constructor with a default message.
     */
    public EntityAlreadyExistsException() {
        super("An entity with this identifier already exists.");
    }

}
