package ro.ubb.teameugen.server.Repository.CriteraAPI;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Component;
import ro.ubb.teameugen.common.Domain.Entities.Club;
import ro.ubb.teameugen.server.Repository.ClubRepositoryCustom;
import ro.ubb.teameugen.server.Repository.CustomRepositorySupport;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.List;


@Component("ClubRepositoryCriteriaAPIImpl")
@NoRepositoryBean
public class ClubRepositoryImpl extends CustomRepositorySupport implements ClubRepositoryCustom {
    @Override
    public List<Club> findClubsByCountry(String country) {
        EntityManager entityManager = getEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Club> query = criteriaBuilder.createQuery(Club.class);
        query.distinct(Boolean.TRUE);
        Root<Club> root = query.from(Club.class);
        query.select(root);
        ParameterExpression<String> parameter = criteriaBuilder.parameter(String.class ,"country");
        query.where(criteriaBuilder.equal(root.get("country"), parameter));
        TypedQuery<Club> typedQuery = entityManager.createQuery(query);
        typedQuery.setParameter("country", country);
        List<Club> clubs = typedQuery.getResultList();
        return clubs;
    }

    @Override
    public List<Club> sortClubsByName() {
        EntityManager entityManager = getEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Club> query = criteriaBuilder.createQuery(Club.class);
        query.distinct(Boolean.TRUE);
        Root<Club> root = query.from(Club.class);
        query.select(root);
        query.orderBy(criteriaBuilder.asc(root.get("name")));
        List<Club> clubs = entityManager.createQuery(query).getResultList();
        return clubs;
    }
}