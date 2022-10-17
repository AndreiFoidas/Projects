package ro.ubb.teameugen.server.Repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Component;
import ro.ubb.teameugen.common.Domain.Entities.Match;

import java.util.List;

@NoRepositoryBean
public interface MatchRepositoryCustom {
    List<Match> findMatchesByStadiumName(String stadiumName);
    List<Match> findMatchesByStadiumNameWithMatchPlayersAndClubsAndCompetitions(String stadiumName);
}
