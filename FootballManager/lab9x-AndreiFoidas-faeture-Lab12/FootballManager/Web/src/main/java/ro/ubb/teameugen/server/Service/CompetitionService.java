package ro.ubb.teameugen.server.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.teameugen.common.Domain.Entities.Competition;
import ro.ubb.teameugen.common.Exception.Service.EntityNotFoundException;
import ro.ubb.teameugen.server.Domain.Validation.Interfaces.BaseValidator;
import ro.ubb.teameugen.server.Repository.CompetitionRepository;
import ro.ubb.teameugen.server.Service.Interfaces.ICompetitionService;

import java.util.LinkedList;
import java.util.List;

@Service
public class CompetitionService implements ICompetitionService {
    private final Logger logger = LoggerFactory.getLogger(CompetitionService.class);

    @Autowired
    private CompetitionRepository competitionRepository;

    @Autowired
    private BaseValidator<Competition> competitionValidator;

    @Override
    public Competition getCompetitionById(int id) {
        logger.trace("GetCompetitionById entered -- id = {}", id);
        var result = this.competitionRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        logger.trace("GetCompetitionById returned -- {}", result);

        return result;

    }

    @Override
    public Iterable<Competition> getCompetitions() {
        logger.trace("GetCompetitions entered ---");
        //var result = competitionRepository.findAll();
        var result = competitionRepository.findAllCompetitions();
        logger.trace("GetCompetitions returned -- {}", result);

        return result;
    }

    @Override
    public void addCompetition(Competition competition) {
        logger.trace("AddCompetition entered -- with Competition {}", competition);
        competitionValidator.validate(competition);
        competitionRepository.save(competition);
        logger.trace("AddCompetition returned --- competition added");
    }

    @Override
    @Transactional
    public void deleteCompetition(int id) {
        logger.trace("DeleteCompetition entered -- with id = {}", id);
        this.competitionRepository.deleteById(id);
        logger.trace("DeleteCompetition returned -- competition deleted");
    }

    @Override
    @Transactional
    public void updateCompetition(Competition competition) {
        logger.trace("UpdateCompetition entered -- with Competition {}", competition);
        competitionValidator.validate(competition);

        this.competitionRepository.findById(competition.getId())
                .ifPresent(c->{
                    c.setFirstYear(competition.getFirstYear());
                    c.setName(competition.getName());
                    c.setPrizePool(competition.getPrizePool());
                });

        logger.trace("UpdateCompetition returned -- competition updated");
    }

    @Override
    public List<Competition> getCompetitionWithBiggestPrizePool() {
        logger.trace("GetCompetitionWithBiggestPrizePool entered ---");
        //var result = competitionRepository.findFirstByOrderByPrizePoolDesc();
        var competition = competitionRepository.findCompetitionWithBiggestPrizePool();
        List<Competition> result = new LinkedList<>();
        result.add(competition);
        logger.trace("GetCompetitionWithBiggestPrizePool returned -- result {}", result);
        return result;
    }

    @Override
    public List<Competition> filterCompetitionByYear(Integer year) {
        logger.trace("FilterCompetitionByYear entered -- with year: {}", year);

        if (year == null) {
            throw new IllegalArgumentException();
        }

        //var result = competitionRepository.findAllByFirstYear(year);
        var result = competitionRepository.findAllCompetitionsByFirstYear(year);
        logger.trace("FilterCompetitionByYear returned -- result {}", result);
        return result;

    }

    @Override
    public List<Competition> filterCompetitionByName(String name) {
        logger.trace("FilterCompetitionByName entered -- with name: {}", name);

        if (name == null) {
            throw new IllegalArgumentException();
        }

        var result = competitionRepository.findCompetitionsByName(name);
        logger.trace("FilterCompetitionByName returned -- result {}", result);
        return result;

    }

    @Override
    public List<Competition> sortCompetitionsByYear() {
        logger.trace("SortCompetitionByYear entered ---");
        var result = competitionRepository.findAllByOrderByFirstYearAsc();
        logger.trace("SortCompetitionByYear returned -- result {}", result);
        return result;
    }
}
