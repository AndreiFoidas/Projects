package ro.ubb.teameugen.server.Service.Interfaces;

import ro.ubb.teameugen.common.Domain.Entities.Match;

import java.util.List;

public interface IMatchService {

    Match getMatchById(int id);
    Iterable<Match> getMatches();
    void addMatch(Match match);
    void deleteMatch(int id);
    void updateMatch(Match match);
    List<Match> filterMatchByAttendance(int attendance);
    List<Match> getMatchWithLowestAttendance();
    List<Match> filterMatchByStadiumName(String stadiumName);
    Integer collectNumberOfMatchesPlayedInStadium(String stadiumName);

}
