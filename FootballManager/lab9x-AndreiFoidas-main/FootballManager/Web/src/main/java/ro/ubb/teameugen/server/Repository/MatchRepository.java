package ro.ubb.teameugen.server.Repository;

import ro.ubb.teameugen.common.Domain.Entities.Match;

import java.util.List;

public interface MatchRepository extends IRepository<Match, Integer> {
    List<Match> findAllByAttendance(int attendance);
    List<Match> findFirstByOrderByAttendanceAsc();
    int countAllByStadiumName(String stadiumName);
}
