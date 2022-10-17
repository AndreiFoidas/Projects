package ro.ubb.teameugen.server.Repository.CriteraAPI;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Component;
import ro.ubb.teameugen.common.Domain.Entities.Match;
import ro.ubb.teameugen.common.Domain.Entities.Player;
import ro.ubb.teameugen.server.Repository.CustomRepositorySupport;
import ro.ubb.teameugen.server.Repository.PlayerRepositoryCustom;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;


@Component("PlayerRepositoryCriteriaAPIImpl")
@NoRepositoryBean
public class PlayerRepositoryImpl extends CustomRepositorySupport implements PlayerRepositoryCustom {
    @Override
    public List<Player> findPlayersByMainPosition(String mainPosition) {
        EntityManager entityManager = getEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Player> query = criteriaBuilder.createQuery(Player.class);
        query.distinct(Boolean.TRUE);
        Root<Player> root = query.from(Player.class);
        query.select(root);
        ParameterExpression<String> parameter = criteriaBuilder.parameter(String.class ,"mainPosition");
        query.where(criteriaBuilder.equal(root.get("mainPosition"), mainPosition));
        TypedQuery<Player> typedQuery = entityManager.createQuery(query);
        typedQuery.setParameter("mainPosition", mainPosition);
        List<Player> players = typedQuery.getResultList();
        return players;
    }

    @Override
    public List<Player> findPlayersByMainPositionWithMatchPlayersAndClubs(String mainPosition) {
        EntityManager entityManager = getEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Player> query = criteriaBuilder.createQuery(Player.class);
        query.distinct(Boolean.TRUE);
        Root<Player> root = query.from(Player.class);
        //query.select(root);
        root.fetch("club", JoinType.LEFT);
        root.fetch("matchPlayers", JoinType.LEFT);
        ParameterExpression<String> parameter = criteriaBuilder.parameter(String.class ,"mainPosition");
        query.where(criteriaBuilder.equal(root.get("mainPosition"), parameter));
        TypedQuery<Player> typedQuery = entityManager.createQuery(query);
        typedQuery.setParameter("mainPosition", mainPosition);
        List<Player> players = typedQuery.getResultList();
        return players;
    }
}
