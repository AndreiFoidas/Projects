package ro.ubb.teameugen.server.Repository.NativeSql;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.ejb.HibernateEntityManager;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.teameugen.common.Domain.Entities.Club;
import ro.ubb.teameugen.common.Domain.Entities.MatchPlayer;
import ro.ubb.teameugen.common.Domain.Entities.Player;
import ro.ubb.teameugen.server.Repository.CustomRepositorySupport;
import ro.ubb.teameugen.server.Repository.PlayerRepositoryCustom;

import java.util.List;


@Component("PlayerRepositoryNativeSQLImpl")
//@NoRepositoryBean
public class PlayerRepositoryImpl extends CustomRepositorySupport implements PlayerRepositoryCustom {
    @Override
    @Transactional
    public List<Player> findPlayersByMainPosition(String mainPosition) {
        HibernateEntityManager hibernateEntityManager = getEntityManager().unwrap(HibernateEntityManager.class);
        Session session = hibernateEntityManager.getSession();

        org.hibernate.Query query = session.createSQLQuery("select distinct {p.*} "+
                "from player p "+
                "where p.mainPosition = ?")
                .addEntity("p", Player.class)
                .setParameter(1, mainPosition)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<Player> players = query.getResultList();
        return players;
    }

    @Override
    @Transactional
    public List<Player> findPlayersByMainPositionWithMatchPlayersAndClubs(String mainPosition) {
        HibernateEntityManager hibernateEntityManager = getEntityManager().unwrap(HibernateEntityManager.class);
        Session session = hibernateEntityManager.getSession();

        org.hibernate.Query query = session.createSQLQuery("select distinct {p.*}, {c.*}, {mp.*} "+
                "from player p "+
                "left join club c on p.club_id=c.id "+
                "left join match_player mp on p.id=mp.player_id  "+
                "where p.mainPosition = ?")
                .addEntity("p", Player.class)
                .addJoin("c", "p.club")
                .addJoin("mp", "p.matchPlayers")
                .addEntity("p", Player.class)
                .setParameter(1, mainPosition)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<Player> players = query.getResultList();
        return players;
    }
}


