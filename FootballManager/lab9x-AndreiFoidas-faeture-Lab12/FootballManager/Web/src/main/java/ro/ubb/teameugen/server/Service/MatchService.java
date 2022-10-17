package ro.ubb.teameugen.server.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.teameugen.common.Domain.Entities.Match;
import ro.ubb.teameugen.common.Domain.Entities.MatchPlayer;
import ro.ubb.teameugen.common.Exception.Service.EntityNotFoundException;
import ro.ubb.teameugen.server.Domain.Validation.Interfaces.BaseValidator;
import ro.ubb.teameugen.server.Repository.MatchRepository;
import ro.ubb.teameugen.server.Service.Interfaces.IMatchService;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MatchService implements IMatchService {
    private final Logger logger = LoggerFactory.getLogger(MatchService.class);

    @Autowired
    private MatchRepository matchRepository;

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
        //var result = this.matchRepository.findAll();
        //var result = this.matchRepository.findAllWithMatchPlayers();
        var result = this.matchRepository.findAllWithMatchPlayersAndClubsAndCompetitions();
        logger.trace("GetMatches returned -- {}", result);

        return result;
    }

    @Override
    public void addMatch(Match match) {
        logger.trace("AddMatch entered -- with Match {}", match);
        matchValidator.validate(match);
        match.setMatchPlayers(new HashSet<MatchPlayer>());
        this.matchRepository.save(match);
        logger.trace("AddMatch returned --- Match added");
    }

    @Override
    public void deleteMatch(int id) {
        logger.trace("DeleteMatch entered -- with id = {}", id);


        //var ceva = this.matchPlayerService.getAll().stream().filter(p->p.getMatch().getId().equals(id)).collect(Collectors.toList());
        //for(MatchPlayer m:ceva)
        //    this.matchPlayerService.deleteMatchPlayer(m.getMatch().getId(), m.getPlayer().getId());
        this.matchRepository.deleteById(id);
        logger.trace("DeleteMatch returned -- Match deleted");
    }

    @Override
    @Transactional
    public void updateMatch(Match match) {
        try {
            logger.trace("UpdateMatch entered -- with Match {}", match);
            matchValidator.validate(match);

            match.setMatchPlayers(this.matchRepository.findById(match.getId()).get().getMatchPlayers());
            this.matchRepository.save(match);

            logger.trace("UpdateMatch returned --Match updated");
        } catch(Exception e) {
            e.printStackTrace();
        }
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

    @Override
    public List<Match> filterMatchByStadiumName(String stadiumName) {
        logger.trace("\n\n\n\n\n\n\n\n\n\n\n\n\nFilterMatchByStadiumName entered -- with stadiumName: {}", stadiumName);
        var result = matchRepository.findMatchesByStadiumNameWithMatchPlayersAndClubsAndCompetitions(stadiumName);
        logger.trace("FilterMatchByAttendance returned -- result {}", result);
        return result;
    }
}
