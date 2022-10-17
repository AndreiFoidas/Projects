package ro.ubb.teameugen.server.Repository.JPQL;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Component;
import ro.ubb.teameugen.common.Domain.Entities.Club;
import ro.ubb.teameugen.server.Repository.ClubRepositoryCustom;
import ro.ubb.teameugen.server.Repository.CustomRepositorySupport;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Component("ClubRepositoryJPQLImpl")
@NoRepositoryBean
public class ClubRepositoryImpl extends CustomRepositorySupport implements ClubRepositoryCustom {
    @Override
    public List<Club> findClubsByCountry(String country) {
        EntityManager entityManager = getEntityManager();
        Query query = entityManager.createQuery("select distinct c from Club c "+
                "where c.country = :country");
        query.setParameter("country", country);
        List<Club> clubs = query.getResultList();
        return clubs;
    }

    @Override
    public List<Club> sortClubsByName() {
        EntityManager entityManager = getEntityManager();
        Query query = entityManager.createQuery("select distinct c from Club c "+
                "order by c.name ");
        List<Club> clubs = query.getResultList();
        return clubs;
    }
}