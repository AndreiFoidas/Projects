package ro.ubb.teameugen.server.Domain.Validation;

import ro.ubb.teameugen.common.Domain.Entities.Competition;
import ro.ubb.teameugen.server.Domain.Validation.Interfaces.BaseValidator;
import ro.ubb.teameugen.common.Exception.Validation.CompetitionValidationException;

import java.util.Optional;

/**
 * Validator for the Competition entity.
 *
 * @see Competition
 */
public class CompetitionValidator implements BaseValidator<Competition> {

    /**
     * Attempts to validate the given Competition entity.
     *
     * @param competition The Competition entity to be validated.
     * @throws CompetitionValidationException If the entity failed validation.
     */
    @Override
    public void validate(Competition competition) throws CompetitionValidationException {
        Optional.ofNullable(competition.getName())
                .filter(name -> name.length() > 2)
                .orElseThrow(() -> new CompetitionValidationException("Name is too short."));

        Optional.of(competition.getPrizePool())
                .filter(prizePool -> prizePool >= 0)
                .orElseThrow(() -> new CompetitionValidationException("Prize pool cannot be negative."));

        Optional.of(competition.getFirstYear())
                .filter(firstYear -> firstYear >= 1800)
                .orElseThrow(() -> new CompetitionValidationException("Starting year is too small."));

        Optional.ofNullable(competition.getName())
                .filter(name -> !name.contains(",") && !name.contains(";"))
                .orElseThrow(() -> new CompetitionValidationException("Name contains illegal characters."));
    }
}