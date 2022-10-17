package ro.ubb.teameugen.server.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.teameugen.common.Domain.Entities.Match;
import ro.ubb.teameugen.common.Domain.Entities.MatchPlayer;
import ro.ubb.teameugen.common.Domain.Entities.Player;
import ro.ubb.teameugen.common.Exception.Validation.ValidatorException;
import ro.ubb.teameugen.server.Repository.MatchRepository;
import ro.ubb.teameugen.server.Repository.PlayerRepository;
import ro.ubb.teameugen.server.Service.Interfaces.IMatchPlayerService;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class MatchPlayerService implements IMatchPlayerService {
    private final Logger logger = LoggerFactory.getLogger(MatchPlayerService.class);

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private PlayerRepository playerRepository;

    /*@Override
    public MatchPlayer getMprById(int id) {
        logger.trace("GetMprById entered -- id = {}", id);

        var result = mprRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        logger.trace("GetMprById returned -- {}", result);
        return result;
    }*/

    /*@Override
    public Iterable<MatchPlayer> getMprs() {
        logger.trace("GetMprs entered ---");

        var result = mprRepository.findAll();

        logger.trace("GetMprs returned -- {}", result);
        return result;
    }*/

    @Override
    @Transactional
    public void addMatchPlayer(Integer matchId, Integer playerId, Integer goalsScored, Integer minutesPlayed) {
        logger.trace("AddMatchPlayer entered -- with matchId {} , playerId {}", matchId, playerId);

        logger.trace("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"+matchId+" "+playerId);
        Match match=this.matchRepository.findAll().stream()
                .filter(m -> m.getId().equals(matchId))
                .collect(Collectors.toList())
                .get(0);

        Player player=this.playerRepository.findAll().stream()
                .filter(p->p.getId().equals(playerId))
                .collect(Collectors.toList())
                .get(0);

        Optional.of(match.getMatchPlayers().stream()
                .anyMatch(mp -> mp.getPlayer().getId().equals(playerId)))
        .filter(bool -> bool.equals(true))
        .ifPresent((trueVal) -> {
            try {
                throw new ValidatorException("Match Player already exists!");
            } catch (ValidatorException e) {
                e.printStackTrace();
            }
        });

        if(matchRepository.findById(matchId).isPresent() && playerRepository.findById(playerId).isPresent()){
            MatchPlayer matchPlayer=new MatchPlayer(match, player, goalsScored, minutesPlayed);

            match.addPlayer(matchPlayer);
            player.addMatch(matchPlayer);

            matchRepository.save(match);
            playerRepository.save(player);

            logger.trace("AddMatchPlayer returned --- MatchPlayer added");
        }
        else {
            logger.trace("AddMatchPlayer returned --- failed because Match or Player does not exist");
        }
    }

    @Override
    @Transactional
    public void deleteMatchPlayer(Integer matchId, Integer playerId) {
        logger.trace("DeleteMatchPlayer entered -- with matchId {}, playerId {}", matchId, playerId);

        Match match=this.matchRepository.findAll().stream()
                .filter(m -> m.getId().equals(matchId))
                .collect(Collectors.toList())
                .get(0);

        Player player=this.playerRepository.findAll().stream()
                .filter(p->p.getId().equals(playerId))
                .collect(Collectors.toList())
                .get(0);

        match.deletePlayer(playerId);
        player.deleteMatch(matchId);

        this.matchRepository.save(match);
        this.playerRepository.save(player);

        logger.trace("DeleteMatchPlayer returned -- MatchPlayer deleted");
    }

    @Override
    @Transactional
    public void updateMatchPlayer(Integer matchId, Integer playerId, Integer goalsScored, Integer minutesPlayed) {
        logger.trace("UpdateMatchPlayer entered -- with matchId {}, playerId {}, goalsScored {}, minutesPlayed {}",
                matchId, playerId, goalsScored, minutesPlayed);

        matchRepository.findById(matchId).ifPresent(match->
                match.getMatchPlayers().stream()
                    .filter(matchPlayer -> matchPlayer.getPlayer().getId().equals(playerId))
                    .forEach(matchPlayer -> {matchPlayer.setGoalsScored(goalsScored); matchPlayer.setMinutesPlayed(minutesPlayed);})
        );

        logger.trace("UpdateMatchPlayer returned -- MatchPlayer updated");
    }

    @Override
    @Transactional
    public Set<MatchPlayer> getAllMatchPlayersForAMatch(Integer matchId) {
        logger.trace("getAllMatchPlayersForAMatch entered -- with matchId {}", matchId);
        Optional<Match> match = matchRepository.findById(matchId);
        Set<MatchPlayer> result = new HashSet<>();

        if(match.isPresent())
            result = match.get().getMatchPlayers();

        logger.trace("getAllMatchPlayersForAMatch returned -- {}", result);
        return result;
    }

    @Override
    @Transactional
    public Set<MatchPlayer> getAll() {
        logger.trace("getAll entered ---");
        Set<MatchPlayer> result = new HashSet<>();

        var matches=this.matchRepository.findAll();
        matches.forEach(m->result.addAll(m.getMatchPlayers()));

        logger.trace("getAll returned -- {}", result);
        return result;
    }

    @Override
    @Transactional
    public Set<MatchPlayer> getMostGoalsMatchPlayer(){
        logger.trace("getMostGoalsMatchPlayer entered ---");
        Set<MatchPlayer> result = new HashSet<>();

        Set<MatchPlayer> matches = getAll();
        int maxGoals=matches.stream()
                .map(MatchPlayer::getGoalsScored)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList()).get(0);

        result=matches.stream()
                .filter(m->m.getGoalsScored() == maxGoals)
                .collect(Collectors.toSet());

        logger.trace("getMostGoalsMatchPlayer returned -- {}", result);
        return result;
    }

    /*@Override
    public List<MatchPlayer> filterMprByGoals(int goals) {
        logger.trace("FilterMprByGoals entered -- with goals: {}", goals);
        var result = mprRepository.findAllByGoalsScored(goals);
        logger.trace("FilterMprByGoals returned -- result {}", result);
        return result;
    }*/

    /*@Override
    public List<MatchPlayer> getMprWithMostGoalsScored() {
        logger.trace("GetMprWithMostGoalsScored entered ---");
        var result = mprRepository.findFirstByOrderByGoalsScoredDesc();
        logger.trace("GetMprWithMostGoalsScored returned -- result {}", result);
        return result;
    }*/
}
