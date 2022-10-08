package ro.ubb.teameugen.server.Domain.Validation;

import ro.ubb.teameugen.common.Domain.Entities.Player;
import ro.ubb.teameugen.server.Domain.Validation.Interfaces.BaseValidator;
import ro.ubb.teameugen.common.Exception.Validation.PlayerValidationException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

/**
 * Validator for the Player entity.
 *
 * @see Player
 */
public class PlayerValidator implements BaseValidator<Player> {

    /**
     * Attempts to validate the given Player entity.
     *
     * @param player The Player entity to be validated.
     * @throws PlayerValidationException If the entity failed validation.
     */
    @Override
    public void validate(Player player) throws PlayerValidationException {
        Optional.of(player.getPlayerName())
                .filter(name -> name.length() >= 2)
                .orElseThrow(() -> new PlayerValidationException("Player name is too short."));

        Optional.of(player.getWage())
                .filter(wage -> wage >= 0)
                .orElseThrow(() -> new PlayerValidationException("Player wage cannot be negative."));

        Optional.of(player.getClubID())
                .filter(id -> id >= 0)
                .orElseThrow(() -> new PlayerValidationException("Player club ID cannot be negative."));

        Optional.of(player.getMainPosition())
                .filter(position -> position.equals("Goalkeeper") || position.equals("Defender") || position.equals("Midfielder") || position.equals("Striker"))
                .orElseThrow(() -> new PlayerValidationException("Invalid player position."));

        Optional.of(player.getBirthday())
                .filter(bday->bday.compareTo(LocalDate.parse("01-01-1950", DateTimeFormatter.ofPattern("dd-MM-yyyy"))) > 0)
                .orElseThrow(() -> new PlayerValidationException("Invalid birthday."));

        Optional.ofNullable(player.getPlayerName())
                .filter(name -> !name.contains(",") && !name.contains(";"))
                .orElseThrow(() -> new PlayerValidationException("Name contains illegal characters."));
    }

}
