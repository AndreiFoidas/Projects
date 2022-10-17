package ro.ubb.teameugen.server.Repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import ro.ubb.teameugen.common.Domain.Entities.Match;

import java.util.List;

@Component("MatchRepositoryNativeSQL")
public interface MatchRepository extends IRepository<Match, Integer>, MatchRepositoryCustom {

    @Query("select distinct m from Match m")
    @EntityGraph(value = "matchWithMatchPlayers",
        type = EntityGraph.EntityGraphType.LOAD)
    List<Match> findAllWithMatchPlayers();

    @Query("select distinct m from Match m")
    @EntityGraph(value = "matchWithMatchPlayersAndClubsAndCompetitions",
            type = EntityGraph.EntityGraphType.LOAD)
    List<Match> findAllWithMatchPlayersAndClubsAndCompetitions();

    List<Match> findAllByAttendance(int attendance);
    List<Match> findFirstByOrderByAttendanceAsc();
    int countAllByStadiumName(String stadiumName);
}
