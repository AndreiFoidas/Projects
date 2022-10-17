package ro.ubb.teameugen.server.Domain.Validation;

import ro.ubb.teameugen.common.Domain.Entities.Match;
import ro.ubb.teameugen.server.Domain.Validation.Interfaces.BaseValidator;
import ro.ubb.teameugen.common.Exception.Validation.MatchValidationException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

/**
 * Validator for the Match entity.
 *
 * @see Match
 */
public class MatchValidator implements BaseValidator<Match> {

    /**
     * Attempts to validate the given Match entity.
     *
     * @param match The Match entity to be validated.
     * @throws MatchValidationException If the entity failed validation.
     */
    @Override
    public void validate(Match match) throws MatchValidationException {

        Optional.of(match.getStadiumName())
                .filter(name -> name.length() >= 2)
                .orElseThrow(() -> new MatchValidationException("Stadium name is too short."));

        Optional.of(match.getAttendance())
                .filter(id -> id >= 0)
                .orElseThrow(() -> new MatchValidationException("Attendance cannot be negative."));

        Optional.of(match.getDate())
                .filter(date->date.compareTo(LocalDate.parse("01-01-1700", DateTimeFormatter.ofPattern("dd-MM-yyyy"))) > 0)
                .orElseThrow(() -> new MatchValidationException("Invalid match date."));

        Optional.ofNullable(match.getStadiumName())
                .filter(name -> !name.contains(",") && !name.contains(";"))
                .orElseThrow(() -> new MatchValidationException("Stadium name contains illegal characters."));
    }
}
