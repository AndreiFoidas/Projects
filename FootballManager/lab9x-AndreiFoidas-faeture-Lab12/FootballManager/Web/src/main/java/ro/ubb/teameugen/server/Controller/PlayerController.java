package ro.ubb.teameugen.server.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ro.ubb.teameugen.common.Converter.PlayerConverter;
import ro.ubb.teameugen.common.Domain.DTOs.PlayerDto;
import ro.ubb.teameugen.common.Domain.DTOs.PlayersDto;
import ro.ubb.teameugen.common.Exception.Service.EntityNotFoundException;
import ro.ubb.teameugen.server.Service.Interfaces.IPlayerService;

import java.util.HashSet;

@RestController
public class PlayerController {
    private final Logger logger = LoggerFactory.getLogger(PlayerController.class);

    @Autowired
    private IPlayerService playerService;

    @Autowired
    private PlayerConverter playerConverter;

    @RequestMapping(value = "/players", method = RequestMethod.GET)
    public PlayersDto getAllPlayers(
                                    @RequestParam(required = false) String mainPosition,
                                    @RequestParam(required = false) boolean youngestPlayer) {

        logger.trace("[GET] /players - getAllPlayers entered with parameters mainPosition = {}, youngestPlayer = {}", mainPosition, youngestPlayer);
        var players = new HashSet<PlayerDto>();

        if(mainPosition != null){
            logger.trace("Parameters for #filterPlayersByPosition(mainPosition)");
            try {
                playerService.filterPlayersByPosition(mainPosition).forEach(player -> players.add(playerConverter.convertModelToDto(player)));
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        else if (youngestPlayer){
            logger.trace("Parameters for #getYoungestPlayer()");
            playerService.getYoungestPlayer().forEach(player -> players.add(playerConverter.convertModelToDto(player)));
        }
        else {
            logger.trace("Parameters for #getPlayers()");
            playerService.getPlayers().forEach(player -> players.add(playerConverter.convertModelToDto(player)));
        }

        logger.trace("getAllPlayers returned - {}", players);
        return new PlayersDto(players);
    }

    @RequestMapping(value = "/players/{id}", method = RequestMethod.GET)
    public PlayerDto getPlayer(@PathVariable int id) {
        logger.trace("[GET] /players/{id} - getPlayer(id = {}) entered", id);

        try {
            var player = playerService.getPlayerById(id);
            logger.trace("getPlayer returned - {}", player);
            return playerConverter.convertModelToDto(player);
        } catch (EntityNotFoundException e){
            logger.trace("getPlayer threw ResponseStatusException - Player not found");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The requested Player was not found.");
        }
    }

    @RequestMapping(value = "/players", method = RequestMethod.PUT)
    public void addPlayer(@RequestBody PlayerDto playerDto) {
        logger.trace("[PUT] /players - addPlayer(player = {}) entered", playerDto);
        playerService.addPlayer(playerConverter.convertDtoToModel(playerDto));
        logger.trace("addPlayer returned ---");
    }

    @RequestMapping(value = "/players", method = RequestMethod.POST)
    public void updatePlayer(@RequestBody PlayerDto playerDto) {
        logger.trace("[POST] /players - updatePlayer(player = {}) entered", playerDto);
        playerService.updatePlayer(playerConverter.convertDtoToModel(playerDto));
        logger.trace("updatePlayer returned ---");
    }

    @RequestMapping(value = "/players/{id}", method = RequestMethod.DELETE)
    public void deletePlayer(@PathVariable int id) {
        logger.trace("[DELETE] /players/{} - deletePlayer() entered", id);
        playerService.deletePlayer(id);
        logger.trace("deletePlayer returned ---");
    }



}
