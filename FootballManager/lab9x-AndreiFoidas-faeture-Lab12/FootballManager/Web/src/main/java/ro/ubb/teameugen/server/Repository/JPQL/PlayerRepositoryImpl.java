package ro.ubb.teameugen.server.Repository.JPQL;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Component;
import ro.ubb.teameugen.common.Domain.Entities.Player;
import ro.ubb.teameugen.server.Repository.CustomRepositorySupport;
import ro.ubb.teameugen.server.Repository.PlayerRepositoryCustom;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Component("PlayerRepositoryJPQLImpl")
@NoRepositoryBean
public class PlayerRepositoryImpl extends CustomRepositorySupport implements PlayerRepositoryCustom {
    @Override
    public List<Player> findPlayersByMainPosition(String mainPosition) {
        EntityManager entityManager = getEntityManager();
        Query query = entityManager.createQuery("select distinct p from Player p "+
                "where p.mainPosition = :mainPosition");
        query.setParameter("mainPosition", mainPosition);
        List<Player> players = query.getResultList();
        return players;
    }

    @Override
    public List<Player> findPlayersByMainPositionWithMatchPlayersAndClubs(String mainPosition) {
        EntityManager entityManager = getEntityManager();
        Query query = entityManager.createQuery("select distinct p from Player p "+
                "left join fetch p.club "+
                "left join fetch p.matchPlayers "+
                "where p.mainPosition = :mainPosition");
        query.setParameter("mainPosition", mainPosition);
        List<Player> players = query.getResultList();
        return players;
    }
}

