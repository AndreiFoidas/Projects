package ro.ubb.teameugen.server.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.teameugen.common.Domain.Entities.Club;
import ro.ubb.teameugen.common.Exception.Service.EntityNotFoundException;
import ro.ubb.teameugen.server.Domain.Validation.Interfaces.BaseValidator;
import ro.ubb.teameugen.server.Repository.ClubRepository;
import ro.ubb.teameugen.server.Service.Interfaces.IClubService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ClubService implements IClubService {
    private final Logger logger = LoggerFactory.getLogger(ClubService.class);

    @Autowired
    private ClubRepository clubRepository;

    @Autowired
    private BaseValidator<Club> clubBaseValidator;

    @Override
    public Club getClubById(int id) {
        logger.trace("getClubById entered -- id = {}", id);
        var result = clubRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        logger.trace("getClubById returned -- {}", result);
        return result;
    }

    @Override
    public Iterable<Club> getClubs() {
        logger.trace("getClubs entered ---");
        var result = clubRepository.findAll();
        logger.trace("getClubs returned -- {}", result);
        return result;
    }

    @Override
    public void addClub(Club club) {
        logger.trace("AddClub entered -- with Club {}", club);
        clubBaseValidator.validate(club);
        clubRepository.save(club);
        logger.trace("AddClub returned --- club added");
    }

    @Override
    @Transactional
    public void deleteClub(int id) {
        logger.trace("deleteClub entered -- with id = {}", id);
        clubRepository.deleteById(id);
        logger.trace("deleteClub returned -- club deleted");
    }

    @Override
    @Transactional
    public void updateClub(Club club) {
        logger.trace("updateClub entered -- with Club {}", club);
        clubBaseValidator.validate(club);
        clubRepository
                .findById(club.getId())
                .ifPresent(c -> {
                    clubRepository.save(club);
                });
        logger.trace("updateClub returned -- club updated");
    }

    @Override
    public List<Club> filterClubsByOwner(String ownerName) {
        logger.trace("filterClubsByOwner entered -- with owner name: {}", ownerName);
        var result = clubRepository.findAllByOwnerName(ownerName);
        logger.trace("filterClubsByOwner returned -- result: {}", result);
        return result;
    }

    @Override
    public List<Club> filterClubsByCountry(String country) {
        logger.trace("filterClubsByCountry entered -- with country: {}", country);
        var result = clubRepository.findAllByCountry(country);
        logger.trace("filterClubsByCountry returned -- result: {}", result);
        return result;
    }

    @Override
    public List<Club> getClubWithBiggestBudget() {
        logger.trace("getClubWithBiggestBudget entered ---");
        var result = clubRepository.findFirstByOrderByBudgetDesc();
        logger.trace("getClubWithBiggestBudget returned -- result {}", result);
        return result;
    }

    @Override
    public List<Club> sortClubsByName(){
        logger.trace("sortClubsByName entered ---");
        var result = clubRepository.findAllByOrderByNameAsc();
        logger.trace("sortClubsByName returned -- result {}", result);
        return result;
    }

    @Override
    @Transactional
    public void deleteClubByCountry(String country) {
        logger.trace("deleteClubByCountry entered -- with country {}", country);
        int result = clubRepository.deleteAllByCountry(country);
        logger.trace("deleteClubByCountry returned -- {} clubs deleted", result);
    }
}
