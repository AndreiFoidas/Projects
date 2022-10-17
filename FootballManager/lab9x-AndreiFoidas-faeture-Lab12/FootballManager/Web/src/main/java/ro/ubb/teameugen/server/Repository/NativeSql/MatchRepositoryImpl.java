package ro.ubb.teameugen.server.Repository.NativeSql;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.ejb.HibernateEntityManager;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.teameugen.common.Domain.Entities.*;
import ro.ubb.teameugen.server.Repository.CustomRepositorySupport;
import ro.ubb.teameugen.server.Repository.MatchRepositoryCustom;

import java.util.List;


@Component("MatchRepositoryNativeSQLImpl")
//@NoRepositoryBean
public class MatchRepositoryImpl extends CustomRepositorySupport implements MatchRepositoryCustom {
    @Override
    @Transactional
    public List<Match> findMatchesByStadiumName(String stadiumName) {
        HibernateEntityManager hibernateEntityManager = getEntityManager().unwrap(HibernateEntityManager.class);
        Session session = hibernateEntityManager.getSession();

        org.hibernate.Query query = session.createSQLQuery("select distinct {m.*}, {cl.*}, {co.*}, {mp.*} "+
                "from match m "+
                "where m.stadiumName = ?")
                .addEntity("m", Match.class)
                .setParameter(1, stadiumName)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<Match> matches = query.getResultList();
        return matches;
    }

    @Override
    @Transactional
    public List<Match> findMatchesByStadiumNameWithMatchPlayersAndClubsAndCompetitions(String stadiumName) {
        HibernateEntityManager hibernateEntityManager = getEntityManager().unwrap(HibernateEntityManager.class);
        Session session = hibernateEntityManager.getSession();

        org.hibernate.Query query = session.createSQLQuery("select distinct {m.*}, {cl1.*}, {cl2.*}, {co.*}, {mp.*} "+
                "from match m "+
                "left join club cl1 on m.club1_id=cl1.id "+
                "left join club cl2 on m.club2_id=cl2.id "+
                "left join competition co on m.competition_id=co.id "+
                "left join match_player mp on m.id=mp.match_id "+
                "where m.stadiumName = ?")
                .addEntity("m", Match.class)
                .addJoin("cl1" ,"m.club1")
                .addJoin("cl2" ,"m.club2")
                .addJoin("co" ,"m.competition")
                .addJoin("mp" ,"m.matchPlayers")
                .addEntity("m", Match.class)
                .setParameter(1, stadiumName)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<Match> matches = query.getResultList();
        return matches;
    }
}
