package ro.ubb.teameugen.server.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.teameugen.common.Domain.Entities.Match;
import ro.ubb.teameugen.common.Exception.Service.EntityNotFoundException;
import ro.ubb.teameugen.server.Domain.Validation.Interfaces.BaseValidator;
import ro.ubb.teameugen.server.Repository.MatchPlayerRelationRepository;
import ro.ubb.teameugen.server.Repository.MatchRepository;
import ro.ubb.teameugen.server.Service.Interfaces.IMatchService;
import java.util.List;

@Service
public class MatchService implements IMatchService {
    private final Logger logger = LoggerFactory.getLogger(MatchService.class);

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private MatchPlayerRelationRepository mprRepository;

    @Autowired
    private BaseValidator<Match> matchValidator;

    @Override
    public Match getMatchById(int id) {
        logger.trace("GetMatchById entered -- id = {}", id);
        var result = matchRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        logger.trace("GetMatchById returned -- {}", result);

        return result;
    }

    @Override
    public Iterable<Match> getMatches() {
        logger.trace("GetMatches entered ---");
        var result = this.matchRepository.findAll();
        logger.trace("GetMatches returned -- {}", result);

        return result;
    }

    @Override
    public void addMatch(Match match) {
        logger.trace("AddMatch entered -- with Match {}", match);
        matchValidator.validate(match);
        this.matchRepository.save(match);
        logger.trace("AddMatch returned --- Match added");
    }

    @Override
    public void deleteMatch(int id) {
        logger.trace("DeleteMatch entered -- with id = {}", id);

        this.mprRepository.findAll()
                .stream()
                .filter(mpr -> mpr.getMatchID() == id)
                .forEach(mpr -> {
                    try {
                        logger.trace("Deleting attached MPR with id = {}", mpr.getId());
                        this.mprRepository.deleteById(mpr.getId());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

        this.matchRepository.deleteById(id);
        logger.trace("DeleteMatch returned -- Match deleted");
    }

    @Override
    @Transactional
    public void updateMatch(Match match) {
        logger.trace("UpdateMatch entered -- with Match {}", match);
        matchValidator.validate(match);

        this.matchRepository.findById(match.getId())
                .ifPresent(m->{
                    m.setAttendance(match.getAttendance());
                    m.setClubID1(match.getClubID1());
                    m.setClubID2(match.getClubID2());
                    m.setDate(match.getDate());
                    m.setStadiumName(match.getStadiumName());
                    m.setCompetitionID(match.getCompetitionID());
                });

        logger.trace("UpdateMatch returned --Match updated");
    }

    @Override
    public List<Match> filterMatchByAttendance(int attendance) {
        logger.trace("FilterMatchByAttendance entered -- with attendance: {}", attendance);
        var result = matchRepository.findAllByAttendance(attendance);
        logger.trace("FilterMatchByAttendance returned -- result {}", result);
        return result;
    }

    @Override
    public List<Match> getMatchWithLowestAttendance() {
        logger.trace("GetMatchWithLowestAttendance entered ---");
        var result = matchRepository.findFirstByOrderByAttendanceAsc();
        logger.trace("GetMatchWithLowestAttendance returned -- result {}", result);
        return result;
    }

    @Override
    public Integer collectNumberOfMatchesPlayedInStadium(String stadiumName) {
        logger.trace("CollectNumberOfMatchesPlayedInStadium entered -- with stadiumName: {}", stadiumName);
        var result = matchRepository.countAllByStadiumName(stadiumName);
        logger.trace("CollectNumberOfMatchesPlayedInStadium returned -- result {}", result);
        return result;
    }
}
