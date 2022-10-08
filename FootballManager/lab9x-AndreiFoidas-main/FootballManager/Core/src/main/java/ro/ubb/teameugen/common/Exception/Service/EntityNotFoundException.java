package ro.ubb.teameugen.common.Exception.Service;

/**
 * A ServiceException in case an Entity was not found when queried.
 */
public class EntityNotFoundException extends ServiceException {

    /**
     * Default constructor that includes a default message.
     */
    public EntityNotFoundException() {
        super("The entity was not found.");
    }

}
