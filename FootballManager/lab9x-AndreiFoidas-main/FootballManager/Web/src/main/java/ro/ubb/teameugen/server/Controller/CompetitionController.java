package ro.ubb.teameugen.server.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ro.ubb.teameugen.common.Converter.CompetitionConverter;
import ro.ubb.teameugen.common.Domain.DTOs.CompetitionDto;
import ro.ubb.teameugen.common.Domain.DTOs.CompetitionsDto;
import ro.ubb.teameugen.common.Exception.Service.EntityNotFoundException;
import ro.ubb.teameugen.server.Service.Interfaces.ICompetitionService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.CopyOnWriteArraySet;

@RestController
public class CompetitionController {
    private final Logger logger = LoggerFactory.getLogger(CompetitionController.class);

    @Autowired
    private ICompetitionService competitionService;

    @Autowired
    private CompetitionConverter competitionConverter;

    @RequestMapping(value = "/competitions", method = RequestMethod.GET)
    public CompetitionsDto getAllCompetitions(@RequestParam(required = false) Boolean biggestPrizePool,
                                              @RequestParam(required = false) Integer firstYear,
                                              @RequestParam(required = false) Boolean sortByFirstYear) {
        logger.trace("[GET] /competitions - getAllCompetitions entered with parameters biggestPrizePool = {}, firstYear = {}, sortByFirstYear = {}", biggestPrizePool, firstYear, sortByFirstYear);
        var competitions = new CopyOnWriteArraySet<CompetitionDto>();

        if (biggestPrizePool != null) {
            logger.trace("Parameters for #getCompetitionWithBiggestPrizePool()");
            competitionService.getCompetitionWithBiggestPrizePool().forEach(competition -> competitions.add(competitionConverter.convertModelToDto(competition)));
        } else if (firstYear != null) {
            logger.trace("Parameters for #filterCompetitionByYear(firstYear)");
            competitionService.filterCompetitionByYear(firstYear).forEach(competition -> competitions.add(competitionConverter.convertModelToDto(competition)));
        } else if (sortByFirstYear != null) {
            logger.trace("Parameters for #sortCompetitionsByYear()");
            competitionService.sortCompetitionsByYear().forEach(competition -> competitions.add(competitionConverter.convertModelToDto(competition)));
        } else {
            logger.trace("Parameters for #getCompetitions()");
            competitionService.getCompetitions().forEach(competition -> competitions.add(competitionConverter.convertModelToDto(competition)));
        }

        logger.trace("getAllCompetitions returned - {}", competitions);
        return new CompetitionsDto(competitions);
    }

    @RequestMapping(value = "/competitions/{id}", method = RequestMethod.GET)
    public CompetitionDto getCompetition(@PathVariable int id) {
        logger.trace("[GET] /competitions/{id} - getCompetition(id = {}) entered", id);

        try {
            var competition = competitionService.getCompetitionById(id);
            logger.trace("getCompetition returned - {}", competition);
            return competitionConverter.convertModelToDto(competition);
        } catch (EntityNotFoundException e) {
            logger.trace("getCompetition threw ResponseStatusException - Competition not found");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The requested competition was not found.");
        }
    }

    @RequestMapping(value = "/competitions", method = RequestMethod.PUT)
    public void addCompetition(@RequestBody CompetitionDto competitionDto) {
        logger.trace("[PUT] /competitions - addCompetition(competition = {}) entered", competitionDto);
        competitionService.addCompetition(competitionConverter.convertDtoToModel(competitionDto));
        logger.trace("addCompetition returned ---");
    }

    @RequestMapping(value = "/competitions", method = RequestMethod.POST)
    public void updateCompetition(@RequestBody CompetitionDto competitionDto) {
        logger.trace("[POST] /competitions - updateCompetition(competition = {}) entered", competitionDto);
        competitionService.updateCompetition(competitionConverter.convertDtoToModel(competitionDto));
        logger.trace("updateCompetition returned ---");
    }

    @RequestMapping(value = "/competitions/{id}", method = RequestMethod.DELETE)
    public void deleteCompetition(@PathVariable int id) {
        logger.trace("[DELETE] /competitions/{} - deleteCompetition() entered", id);
        competitionService.deleteCompetition(id);
        logger.trace("deleteCompetition returned ---");
    }
}
