package ro.ubb.teameugen.server.Domain.Validation.Interfaces;

import ro.ubb.teameugen.common.Exception.Validation.ValidatorException;

/**
 * BaseValidator interface for all entity Validators
 *
 * @param <Entity> The entity that will be validated.
 */
public interface BaseValidator<Entity> {

    /**
     * Attempts to validate the given entity.
     *
     * @param entity The entity to be validated.
     * @throws ValidatorException If the entity failed validation.
     */
    void validate(Entity entity) throws ValidatorException;
}
