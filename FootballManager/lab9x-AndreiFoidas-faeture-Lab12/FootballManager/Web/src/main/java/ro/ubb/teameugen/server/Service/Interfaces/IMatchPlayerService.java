package ro.ubb.teameugen.server.Service.Interfaces;

import ro.ubb.teameugen.common.Domain.Entities.MatchPlayer;

import java.util.List;
import java.util.Set;

public interface IMatchPlayerService {

    //MatchPlayer getMprById(int id);
    //Iterable<MatchPlayer> getMprs();
    void addMatchPlayer(Integer matchId, Integer playerId, Integer goalsScored, Integer minutesPlayed);
    void deleteMatchPlayer(Integer matchId, Integer playerId);
    void updateMatchPlayer(Integer matchId, Integer playerId, Integer goalsScored, Integer minutesPlayed);
    Set<MatchPlayer> getAllMatchPlayersForAMatch(Integer matchId);
    Set<MatchPlayer> getAll();
    Set<MatchPlayer> getMostGoalsMatchPlayer();
    //void deleteMpr(int id);
    //void updateMpr(MatchPlayer matchPlayer);
    //List<MatchPlayer> filterMprByGoals(int goals);
    //List<MatchPlayer> getMprWithMostGoalsScored();

}
