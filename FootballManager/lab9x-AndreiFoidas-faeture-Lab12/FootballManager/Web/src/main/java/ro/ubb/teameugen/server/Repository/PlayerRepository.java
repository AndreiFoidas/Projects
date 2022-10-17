package ro.ubb.teameugen.server.Repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import ro.ubb.teameugen.common.Domain.Entities.Player;

import java.util.List;

@Component("PlayerRepositoryNativeSQL")
public interface PlayerRepository extends IRepository<Player, Integer>, PlayerRepositoryCustom {

    @Query("select distinct p from Player p")
    @EntityGraph(value = "playerWithMatchPlayers",
            type = EntityGraph.EntityGraphType.LOAD)
    List<Player> findAllWithMatchPlayers();

    @Query("select distinct p from Player p")
    @EntityGraph(value = "playerWithMatchPlayersAndClubs",
            type = EntityGraph.EntityGraphType.LOAD)
    List<Player> findAllWithMatchPlayersAndClubs();

    List<Player> findAllByMainPosition(String mainPosition);
    List<Player> findFirstByOrderByBirthdayDesc();
}
