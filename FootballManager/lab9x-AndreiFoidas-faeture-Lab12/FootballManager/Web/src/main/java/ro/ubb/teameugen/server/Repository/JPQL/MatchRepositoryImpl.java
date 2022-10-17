package ro.ubb.teameugen.server.Repository.JPQL;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import ro.ubb.teameugen.common.Domain.Entities.Match;
import ro.ubb.teameugen.server.Repository.CustomRepositorySupport;
import ro.ubb.teameugen.server.Repository.MatchRepositoryCustom;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.*;

@Component("MatchRepositoryJPQLImpl")
@NoRepositoryBean
public class MatchRepositoryImpl extends CustomRepositorySupport implements MatchRepositoryCustom {
    @Override
    public List<Match> findMatchesByStadiumName(String stadiumName) {
        EntityManager entityManager = getEntityManager();
        Query query = entityManager.createQuery("select distinct m from Match m "+
                "where m.stadiumName = :stadiumName");
        query.setParameter("stadiumName", stadiumName);
        List<Match> matches = query.getResultList();
        return matches;
    }

    @Override
    public List<Match> findMatchesByStadiumNameWithMatchPlayersAndClubsAndCompetitions(String stadiumName) {
        EntityManager entityManager = getEntityManager();
        Query query = entityManager.createQuery("select distinct m from Match m "+
                "left join fetch m.club1 "+
                "left join fetch m.club2 "+
                "left join fetch m.competition "+
                "left join fetch m.matchPlayers "+
                "where m.stadiumName = :stadiumName");
        query.setParameter("stadiumName", stadiumName);
        List<Match> matches = query.getResultList();
        return matches;
    }
}