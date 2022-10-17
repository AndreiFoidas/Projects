package ro.ubb.teameugen.server.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.teameugen.common.Domain.Entities.Player;
import ro.ubb.teameugen.common.Exception.Service.EntityNotFoundException;
import ro.ubb.teameugen.server.Domain.Validation.Interfaces.BaseValidator;
import ro.ubb.teameugen.server.Repository.PlayerRepository;
import ro.ubb.teameugen.server.Service.Interfaces.IPlayerService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PlayerService implements IPlayerService {
    private final Logger logger = LoggerFactory.getLogger(PlayerService.class);

    @Autowired
    private BaseValidator<Player> playerBaseValidator;

    @Autowired
    private PlayerRepository playerRepository;

    @Override
    public Player getPlayerById(int id) {
        logger.trace("GetPlayerById entered -- id = {}", id);

        var result = playerRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        logger.trace("GetPlayerById returned -- {}", result);

        return result;
    }

    @Override
    public Iterable<Player> getPlayers() {
        logger.trace("GetPlayers entered ---");

        //var result = playerRepository.findAll();
        //var result = playerRepository.findAllWithMatchPlayers();
        var result = playerRepository.findAllWithMatchPlayersAndClubs();

        logger.trace("GetPlayers returned -- {}", result);

        return result;
    }

    @Override
    public void addPlayer(Player player) {
        logger.trace("AddPlayer entered -- with Player {}", player);
        playerBaseValidator.validate(player);
        playerRepository.save(player);
        logger.trace("AddPlayer returned --- player added");
    }

    @Override
    @Transactional
    public void deletePlayer(int id) {
        logger.trace("DeletePlayer entered -- with id = {}", id);
        playerRepository.deleteById(id);
        logger.trace("DeletePlayer returned -- player deleted");
    }

    @Override
    @Transactional
    public void updatePlayer(Player player) {
        logger.trace("UpdatePlayer entered -- with Player {}", player);
        playerBaseValidator.validate(player);
        playerRepository.findById(player.getId())
                .ifPresent(p -> playerRepository.save(player));
        logger.trace("UpdatePlayer returned -- player updated");
    }

    @Override
    public List<Player> getYoungestPlayer() {
        logger.trace("GetYoungestPlayer entered ---");
        var result = playerRepository.findFirstByOrderByBirthdayDesc();
        logger.trace("GetYoungestPlayer returned -- result {}", result);
        return result;
    }

    @Override
    public List<Player> filterPlayersByPosition(String mainPosition) {
        logger.trace("FilterPlayersByPosition entered -- with mainPosition: {}", mainPosition);

        if (mainPosition == null)
            throw new IllegalArgumentException("mainPosition String cannot be null");

        //var result = playerRepository.findAllByMainPosition(mainPosition);
        var result = playerRepository.findPlayersByMainPositionWithMatchPlayersAndClubs(mainPosition);
        logger.trace("FilterPlayersByPosition returned -- result {}", result);
        return result;
    }
}
