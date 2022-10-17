package ro.ubb.teameugen.server.Repository.CriteraAPI;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Component;
import ro.ubb.teameugen.common.Domain.Entities.Club;
import ro.ubb.teameugen.common.Domain.Entities.Competition;
import ro.ubb.teameugen.server.Repository.CompetitionRepositoryCustom;
import ro.ubb.teameugen.server.Repository.CustomRepositorySupport;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.List;


@Component("CompetitionRepositoryCriteriaAPIImpl")
@NoRepositoryBean
public class CompetitionRepositoryImpl extends CustomRepositorySupport implements CompetitionRepositoryCustom {
    @Override
    public List<Competition> findCompetitionsByName(String name) {
        EntityManager entityManager = getEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Competition> query = criteriaBuilder.createQuery(Competition.class);
        query.distinct(Boolean.TRUE);
        Root<Competition> root = query.from(Competition.class);
        query.select(root);
        ParameterExpression<String> parameter = criteriaBuilder.parameter(String.class ,"name");
        query.where(criteriaBuilder.equal(root.get("name"), parameter));
        TypedQuery<Competition> typedQuery = entityManager.createQuery(query);
        typedQuery.setParameter("name", name);
        List<Competition> competitions = typedQuery.getResultList();
        return competitions;
    }

    @Override
    public Competition findCompetitionWithBiggestPrizePool() {
        EntityManager entityManager = getEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Competition> query = criteriaBuilder.createQuery(Competition.class);
        query.distinct(Boolean.TRUE);
        Root<Competition> root = query.from(Competition.class);
        query.select(root);
        query.orderBy(criteriaBuilder.desc(root.get("prizePool")));
        List<Competition> competitions = entityManager.createQuery(query).getResultList();
        return competitions.get(0);
    }
}
