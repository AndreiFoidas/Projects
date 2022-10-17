package ro.ubb.teameugen.server.Repository.JPQL;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Component;
import ro.ubb.teameugen.common.Domain.Entities.Competition;
import ro.ubb.teameugen.server.Repository.CompetitionRepositoryCustom;
import ro.ubb.teameugen.server.Repository.CustomRepositorySupport;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Component("CompetitionRepositoryJPQLImpl")
@NoRepositoryBean
public class CompetitionRepositoryImpl extends CustomRepositorySupport implements CompetitionRepositoryCustom {
    @Override
    public List<Competition> findCompetitionsByName(String name) {
        EntityManager entityManager = getEntityManager();
        Query query = entityManager.createQuery("select distinct c from Competition c "+
                "where c.name = :name");
        query.setParameter("name", name);
        List<Competition> competitions = query.getResultList();
        return competitions;
    }

    @Override
    public Competition findCompetitionWithBiggestPrizePool() {
        EntityManager entityManager = getEntityManager();
        Query query = entityManager.createQuery("select distinct c from Competition c "+
                "order by c.prizePool desc");
        List<Competition> competitions = query.getResultList();
        return competitions.get(0);
    }
}
