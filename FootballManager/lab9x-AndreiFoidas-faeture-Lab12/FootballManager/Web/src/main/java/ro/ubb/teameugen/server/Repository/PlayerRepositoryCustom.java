package ro.ubb.teameugen.server.Repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Component;
import ro.ubb.teameugen.common.Domain.Entities.Player;

import java.util.List;

@NoRepositoryBean
public interface PlayerRepositoryCustom {
    List<Player> findPlayersByMainPosition(String mainPosition);
    List<Player> findPlayersByMainPositionWithMatchPlayersAndClubs(String mainPosition);
}
