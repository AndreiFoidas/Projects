package ro.ubb.teameugen.server.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ro.ubb.teameugen.common.Converter.MatchConverter;
import ro.ubb.teameugen.common.Domain.DTOs.MatchDto;
import ro.ubb.teameugen.common.Domain.DTOs.MatchesDto;
import ro.ubb.teameugen.common.Domain.Entities.Match;
import ro.ubb.teameugen.common.Domain.Entities.MatchPlayer;
import ro.ubb.teameugen.common.Domain.Entities.Player;
import ro.ubb.teameugen.common.Exception.Service.EntityNotFoundException;
import ro.ubb.teameugen.server.Service.Interfaces.IMatchPlayerService;
import ro.ubb.teameugen.server.Service.Interfaces.IMatchService;
import java.util.HashSet;
import java.util.stream.Collectors;

@RestController
public class MatchController {
    private final Logger logger = LoggerFactory.getLogger(MatchController.class);

    @Autowired
    private IMatchService matchService;

    @Autowired
    private MatchConverter matchConverter;

    @Autowired
    private IMatchPlayerService matchPlayerService;

    @RequestMapping(value = "/matches", method = RequestMethod.GET)
    public MatchesDto getAllMatches(@RequestParam(required = false) Boolean lowestAttendance,
                                @RequestParam(required = false) Integer attendance,
                                    @RequestParam(required = false) String stadiumName){
        logger.trace("[GET] /matches - getAllMatches entered with parameters lowestAttendance = {}, attendance = {}, stadiumName = {}",
                lowestAttendance, attendance, stadiumName);
        var matches = new HashSet<MatchDto>();

        if (lowestAttendance != null) {
            logger.trace("Parameters for #getMatchWithLowestAttendance()");
            matchService.getMatchWithLowestAttendance().forEach(match -> matches.add(matchConverter.convertModelToDto(match)));
        } else if (attendance != null) {
            logger.trace("Parameters for #filterMatchByAttendance(attendance)");
            matchService.filterMatchByAttendance(attendance).forEach(match -> matches.add(matchConverter.convertModelToDto(match)));
        } else if (stadiumName != null) {
            logger.trace("Parameters for #filterMatchByAttendance(stadiumName)");
            matchService.filterMatchByStadiumName(stadiumName).forEach(match -> matches.add(matchConverter.convertModelToDto(match)));
        }
        else {
            logger.trace("Parameters for #getMatches()");
            matchService.getMatches().forEach(match -> matches.add(matchConverter.convertModelToDto(match)));
        }

        logger.trace("getAllMatches returned - {}", matches);
        return new MatchesDto(matches);
    }

    @RequestMapping(value = "/matches/{id}", method = RequestMethod.GET)
    public MatchDto getMatch(@PathVariable int id) {
        logger.trace("[GET] /matches/{id} - getMatch(id = {}) entered", id);

        try {
            var match = matchService.getMatchById(id);
            logger.trace("getMatch returned - {}", match);
            return matchConverter.convertModelToDto(match);
        } catch (EntityNotFoundException e) {
            logger.trace("getMatch threw ResponseStatusException - Match not found");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The requested match was not found.");
        }
    }

    @RequestMapping(value = "/matches/stadium", method = RequestMethod.GET)
    public Integer getMatchesPlayedInStadium(@RequestParam(required = false) String stadiumName) {
        logger.trace("[GET] /matches/stadium - getMatchesPlayedInStadium(stadiumName = {}) entered", stadiumName);

        try {
            var matchCount = matchService.collectNumberOfMatchesPlayedInStadium(stadiumName);
            logger.trace("getMatchesPlayedInStadium returned - {}", matchCount);
            return matchCount;
        } catch (EntityNotFoundException e) {
            logger.trace("getMatchesPlayedInStadium threw ResponseStatusException - Match not found");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The requested match was not found.");
        }
    }

    @RequestMapping(value = "/matches", method = RequestMethod.PUT)
    public void addMatch(@RequestBody MatchDto matchDto) {
        logger.trace("[PUT] /matches - addMatch(match = {}) entered", matchDto);
        matchService.addMatch(matchConverter.convertDtoToModel(matchDto));
        logger.trace("addMatch returned ---");
    }

    @RequestMapping(value = "/matches", method = RequestMethod.POST)
    public void updateMatch(@RequestBody MatchDto matchDto) {
        logger.trace("[POST] /matches - updateMatch(match = {}) entered", matchDto);
        matchService.updateMatch(matchConverter.convertDtoToModel(matchDto));
        logger.trace("updateMatch returned ---");
    }

    @RequestMapping(value = "/matches/{id}", method = RequestMethod.DELETE)
    public void deleteMatch(@PathVariable int id) {
        logger.trace("[DELETE] /matches/{} - deleteMatch() entered", id);

        matchService.deleteMatch(id);

        logger.trace("deleteMatch returned ---");
    }
}
