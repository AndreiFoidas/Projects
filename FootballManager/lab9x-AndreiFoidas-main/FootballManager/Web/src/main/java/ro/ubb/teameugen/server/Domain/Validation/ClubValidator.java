package ro.ubb.teameugen.server.Domain.Validation;

import ro.ubb.teameugen.common.Domain.Entities.Club;
import ro.ubb.teameugen.server.Domain.Validation.Interfaces.BaseValidator;
import ro.ubb.teameugen.common.Exception.Validation.ClubValidationException;

import java.util.Optional;

/**
 * Validator for the Club entity.
 *
 * @see Club
 */
public class ClubValidator implements BaseValidator<Club> {

    /**
     * Attempts to validate the given Club entity.
     *
     * @param club The Club entity to be validated.
     * @throws ClubValidationException If the entity failed validation.
     */
    @Override
    public void validate(Club club) throws ClubValidationException {
        Optional.ofNullable(club.getName())
                .filter(name -> name.length() > 2)
                .orElseThrow(() -> new ClubValidationException("Name is too short."));

        Optional.ofNullable(club.getCountry())
                .filter(country -> country.length() > 2)
                .orElseThrow(() -> new ClubValidationException("Country is too short."));

        Optional.ofNullable(club.getOwnerName())
                .filter(name -> name.length() > 2)
                .orElseThrow(() -> new ClubValidationException("Owner name is too short."));

        Optional.ofNullable(club.getManagerName())
                .filter(name -> name.length() > 2)
                .orElseThrow(() -> new ClubValidationException("Manager name is too short."));

        Optional.ofNullable(club.getStadiumName())
                .filter(name -> name.length() > 2)
                .orElseThrow(() -> new ClubValidationException("Stadium name is too short."));

        Optional.ofNullable(club.getName())
                .filter(name -> !name.contains(",") && !name.contains(";"))
                .orElseThrow(() -> new ClubValidationException("Name contains illegal characters."));

        Optional.ofNullable(club.getCountry())
                .filter(country -> !country.contains(",") && !country.contains(";"))
                .orElseThrow(() -> new ClubValidationException("Country contains illegal characters."));

        Optional.ofNullable(club.getOwnerName())
                .filter(name -> !name.contains(",") && !name.contains(";"))
                .orElseThrow(() -> new ClubValidationException("Owner name contains illegal characters."));

        Optional.ofNullable(club.getManagerName())
                .filter(name -> !name.contains(",") && !name.contains(";"))
                .orElseThrow(() -> new ClubValidationException("Manager name contains illegal characters."));

        Optional.ofNullable(club.getStadiumName())
                .filter(name -> !name.contains(",") && !name.contains(";"))
                .orElseThrow(() -> new ClubValidationException("Stadium name contains illegal characters."));
    }
}
