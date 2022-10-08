package ro.ubb.teameugen.client.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ro.ubb.teameugen.common.Converter.PlayerConverter;
import ro.ubb.teameugen.common.Domain.DTOs.PlayerDto;
import ro.ubb.teameugen.common.Domain.DTOs.PlayersDto;
import ro.ubb.teameugen.common.Domain.Entities.Player;
import ro.ubb.teameugen.common.Exception.Service.EntityNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PlayerClientService {
    private final Logger logger = LoggerFactory.getLogger(PlayerClientService.class);
    private static final String BASE_URL = "http://localhost:8080/footballmanager";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PlayerConverter playerConverter;

    public Player getPlayerById(int id) {
        logger.trace("GetPlayerById started --- with id {}", id);
        var playerDto = restTemplate.getForObject(BASE_URL + "/players/" + id, PlayerDto.class);

        if (playerDto == null) {
            throw new EntityNotFoundException();
        }

        return playerConverter.convertDtoToModel(playerDto);

    }

    public Iterable<Player> getPlayers() {
        logger.trace("GetPlayers started --- ");
        var playersDto = restTemplate.getForObject(BASE_URL + "/players", PlayersDto.class);

        if (playersDto == null) {
            return new ArrayList<>();
        }

        return playersDto.getPlayerDtoSet().stream().map(playerDto -> playerConverter.convertDtoToModel(playerDto)).collect(Collectors.toList());
    }

    public void addPlayer(Player player) {
        logger.trace("AddPlayer started -- with {}", player);
        restTemplate.put(BASE_URL + "/players", playerConverter.convertModelToDto(player));
        logger.trace("AddPlayer returned ---");
    }

    public void deletePlayer(int id) {
        logger.trace("DeletePlayer started -- with id {}", id);
        restTemplate.delete(BASE_URL + "/players/" + id);
        logger.trace("DeletePlayer returned ---");
    }

    public void updatePlayer(Player player) {
        logger.trace("UpdatePlayer started -- with {}", player);
        restTemplate.postForObject(BASE_URL + "/players", playerConverter.convertModelToDto(player), PlayerDto.class);
        logger.trace("UpdatePlayer returned ---");
    }

    public List<Player> getYoungestPlayer() {
        logger.trace("GetYoungestPlayer started --- ");

        Map<String, String> parameters = new HashMap<>();
        parameters.put("youngestPlayer", "true");

        var playersDto = restTemplate.getForObject(BASE_URL + "/players?youngestPlayer={youngestPlayer}", PlayersDto.class, parameters);

        if (playersDto == null) {
            return new ArrayList<>();
        }

        logger.trace("GetYoungestPlayer returned ---");
        return playersDto.getPlayerDtoSet().stream().map(playerDto -> playerConverter.convertDtoToModel(playerDto)).collect(Collectors.toList());
    }

    public List<Player> filterPlayersByPosition(String position) {
        logger.trace("FilterPlayersByPosition started --- ");

        Map<String, String> parameters = new HashMap<>();
        parameters.put("position", position);

        var playersDto = restTemplate.getForObject(BASE_URL + "/players?mainPosition={position}", PlayersDto.class, parameters);

        if (playersDto == null) {
            return new ArrayList<>();
        }

        logger.trace("FilterPlayersByPosition returned ---");
        return playersDto.getPlayerDtoSet().stream().map(playerDto -> playerConverter.convertDtoToModel(playerDto)).collect(Collectors.toList());
    }
}
