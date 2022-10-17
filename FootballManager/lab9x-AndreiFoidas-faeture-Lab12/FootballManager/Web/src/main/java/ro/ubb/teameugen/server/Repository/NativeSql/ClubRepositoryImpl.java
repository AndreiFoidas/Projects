package ro.ubb.teameugen.server.Repository.NativeSql;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.ejb.HibernateEntityManager;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.teameugen.common.Domain.Entities.Club;
import ro.ubb.teameugen.server.Repository.ClubRepositoryCustom;
import ro.ubb.teameugen.server.Repository.CustomRepositorySupport;

import java.util.List;


@Component("ClubRepositoryNativeSQLImpl")
//@NoRepositoryBean
public class ClubRepositoryImpl extends CustomRepositorySupport implements ClubRepositoryCustom {
    @Override
    @Transactional
    public List<Club> findClubsByCountry(String country) {
        HibernateEntityManager hibernateEntityManager = getEntityManager().unwrap(HibernateEntityManager.class);
        Session session = hibernateEntityManager.getSession();

        org.hibernate.Query query = session.createSQLQuery("select distinct {c.*} "+
                "from club c "+
                "where c.country = ?")
                .addEntity("c", Club.class)
                .setParameter(1, country)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<Club> clubs = query.getResultList();
        return clubs;
    }

    @Override
    @Transactional
    public List<Club> sortClubsByName() {
        HibernateEntityManager hibernateEntityManager = getEntityManager().unwrap(HibernateEntityManager.class);
        Session session = hibernateEntityManager.getSession();

        org.hibernate.Query query = session.createSQLQuery("select distinct {c.*} "+
                "from club c "+
                "order by c.name ")
                .addEntity("c", Club.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<Club> clubs = query.getResultList();
        return clubs;
    }
}
