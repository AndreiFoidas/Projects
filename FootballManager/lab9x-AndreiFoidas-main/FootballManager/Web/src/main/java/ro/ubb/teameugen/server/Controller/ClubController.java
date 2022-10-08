package ro.ubb.teameugen.server.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ro.ubb.teameugen.common.Converter.ClubConverter;
import ro.ubb.teameugen.common.Domain.DTOs.ClubDto;
import ro.ubb.teameugen.common.Domain.DTOs.ClubsDto;
import ro.ubb.teameugen.common.Exception.Service.EntityNotFoundException;
import ro.ubb.teameugen.server.Service.Interfaces.IClubService;

import java.util.HashSet;
import java.util.concurrent.CopyOnWriteArraySet;

@RestController
public class ClubController {
    private final Logger logger = LoggerFactory.getLogger(ClubController.class);

    @Autowired
    private IClubService clubService;

    @Autowired
    private ClubConverter clubConverter;

    @RequestMapping(value = "/clubs", method = RequestMethod.GET)
    public ClubsDto getAllClubs(@RequestParam(required = false) String owner,
                                @RequestParam(required = false) String country,
                                @RequestParam(required = false) boolean maxBudget,
                                @RequestParam(required = false) boolean sortByName) {
        logger.trace("[GET] /clubs - getAllClubs entered with parameters owner = {}, country = {}, maxBudget = {}, sortByName = {}", owner, country, maxBudget, sortByName);
        var clubs = new CopyOnWriteArraySet<ClubDto>();

        if (owner != null) {
            logger.trace("Parameters for #filterClubsByOwner(owner)");
            clubService.filterClubsByOwner(owner).forEach(club -> clubs.add(clubConverter.convertModelToDto(club)));
        } else if (country != null) {
            logger.trace("Parameters for #filterClubsByCountry(country)");
            clubService.filterClubsByCountry(country).forEach(club -> clubs.add(clubConverter.convertModelToDto(club)));
        } else if (maxBudget) {
            logger.trace("Parameters for #getClubWithBiggestBudget()");
            clubService.getClubWithBiggestBudget().forEach(club -> clubs.add(clubConverter.convertModelToDto(club)));
        } else if (sortByName) {
            logger.trace("Parameters for #sortClubsByName()");
            clubService.sortClubsByName().forEach(club -> clubs.add(clubConverter.convertModelToDto(club)));
        } else {
            logger.trace("Parameters for #getClubs()");
            clubService.getClubs().forEach(club -> clubs.add(clubConverter.convertModelToDto(club)));
        }

        logger.trace("getAllClubs returned - {}", clubs);
        return new ClubsDto(clubs);
    }

    @RequestMapping(value = "/clubs/{id}", method = RequestMethod.GET)
    public ClubDto getClub(@PathVariable int id) {
        logger.trace("[GET] /clubs/{id} - getClub(id = {}) entered", id);

        try {
            var club = clubService.getClubById(id);
            logger.trace("getClub returned - {}", club);
            return clubConverter.convertModelToDto(club);
        } catch (EntityNotFoundException e) {
            logger.trace("getClub threw ResponseStatusException - Club not found");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The requested club was not found.");
        }
    }

    @RequestMapping(value = "/clubs", method = RequestMethod.PUT)
    public void addClub(@RequestBody ClubDto clubDto) {
        logger.trace("[PUT] /clubs - addClub(club = {}) entered", clubDto);
        clubService.addClub(clubConverter.convertDtoToModel(clubDto));
        logger.trace("addClub returned ---");
    }

    @RequestMapping(value = "/clubs", method = RequestMethod.POST)
    public void updateClub(@RequestBody ClubDto clubDto) {
        logger.trace("[POST] /clubs - updateClub(club = {}) entered", clubDto);
        clubService.updateClub(clubConverter.convertDtoToModel(clubDto));
        logger.trace("updateClub returned ---");
    }

    @RequestMapping(value = "/clubs/{id}", method = RequestMethod.DELETE)
    public void deleteClub(@PathVariable int id) {
        logger.trace("[DELETE] /clubs/{} - deleteClub() entered", id);
        clubService.deleteClub(id);
        logger.trace("deleteClub returned ---");
    }

    @RequestMapping(value = "/clubs", method = RequestMethod.DELETE)
    public void deleteClubsByCountry(@RequestParam String country) {
        logger.trace("[DELETE] /clubs - deleteClub(country = {}) entered", country);
        clubService.deleteClubByCountry(country);
        logger.trace("deleteClub returned ---");
    }
}
