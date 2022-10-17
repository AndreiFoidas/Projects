package ro.ubb.teameugen.server.Repository.CriteraAPI;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Component;
import ro.ubb.teameugen.common.Domain.Entities.Club;
import ro.ubb.teameugen.common.Domain.Entities.Match;
import ro.ubb.teameugen.server.Repository.CustomRepositorySupport;
import ro.ubb.teameugen.server.Repository.MatchRepositoryCustom;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;


@Component("MatchRepositoryCriteriaAPIImpl")
@NoRepositoryBean
public class MatchRepositoryImpl extends CustomRepositorySupport implements MatchRepositoryCustom {
    @Override
    public List<Match> findMatchesByStadiumName(String stadiumName) {
        EntityManager entityManager = getEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Match> query = criteriaBuilder.createQuery(Match.class);
        query.distinct(Boolean.TRUE);
        Root<Match> root = query.from(Match.class);
        query.select(root);
        ParameterExpression<String> parameter = criteriaBuilder.parameter(String.class ,"stadiumName");
        query.where(criteriaBuilder.equal(root.get("stadiumName"), parameter));
        TypedQuery<Match> typedQuery = entityManager.createQuery(query);
        typedQuery.setParameter("stadiumName", stadiumName);
        List<Match> matches = typedQuery.getResultList();
        return matches;
    }

    @Override
    public List<Match> findMatchesByStadiumNameWithMatchPlayersAndClubsAndCompetitions(String stadiumName) {
        EntityManager entityManager = getEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Match> query = criteriaBuilder.createQuery(Match.class);
        query.distinct(Boolean.TRUE);
        Root<Match> root = query.from(Match.class);
        //query.select(root);
        root.fetch("club1", JoinType.LEFT);
        root.fetch("competition", JoinType.LEFT);
        root.fetch("matchPlayers", JoinType.LEFT);

        ParameterExpression<String> parameter = criteriaBuilder.parameter(String.class ,"stadiumName");
        query.where(criteriaBuilder.equal(root.get("stadiumName"), parameter));
        TypedQuery<Match> typedQuery = entityManager.createQuery(query);
        typedQuery.setParameter("stadiumName", stadiumName);
        List<Match> matches = typedQuery.getResultList();
        return matches;
    }
}