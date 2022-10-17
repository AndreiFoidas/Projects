package ro.ubb.teameugen.server.Repository.NativeSql;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.ejb.HibernateEntityManager;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.teameugen.common.Domain.Entities.Competition;
import ro.ubb.teameugen.server.Repository.CompetitionRepositoryCustom;
import ro.ubb.teameugen.server.Repository.CustomRepositorySupport;

import java.util.List;


@Component("CompetitionRepositoryNativeSQLImpl")
//@NoRepositoryBean
public class CompetitionRepositoryImpl extends CustomRepositorySupport implements CompetitionRepositoryCustom {
    @Override
    @Transactional
    public List<Competition> findCompetitionsByName(String name) {
        HibernateEntityManager hibernateEntityManager = getEntityManager().unwrap(HibernateEntityManager.class);
        Session session = hibernateEntityManager.getSession();

        org.hibernate.Query query = session.createSQLQuery("select distinct {c.*} "+
                "from competition c "+
                "where c.name = ?")
                .addEntity("c", Competition.class)
                .setParameter(1, name)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<Competition> competitions = query.getResultList();
        return competitions;
    }

    @Override
    @Transactional
    public Competition findCompetitionWithBiggestPrizePool() {
        HibernateEntityManager hibernateEntityManager = getEntityManager().unwrap(HibernateEntityManager.class);
        Session session = hibernateEntityManager.getSession();

        org.hibernate.Query query = session.createSQLQuery("select distinct {c.*} "+
                "from competition c "+
                "order by c.prizePool desc")
                .addEntity("c", Competition.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<Competition> competitions = query.getResultList();
        return competitions.get(0);
    }
}