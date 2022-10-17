package ro.ubb.teameugen.server.Repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import ro.ubb.teameugen.common.Domain.Entities.Competition;

import java.util.List;

@Component("CompetitionRepositoryNativeSQL")
public interface CompetitionRepository extends IRepository<Competition, Integer>, CompetitionRepositoryCustom {

    @Query("select distinct c from Competition c")
    @EntityGraph(value = "competitions",
            type = EntityGraph.EntityGraphType.LOAD)
    List<Competition> findAllCompetitions();

    @Query("select distinct c from Competition c where c.firstYear = :firstYear")
    @EntityGraph(value = "competitions",
            type = EntityGraph.EntityGraphType.LOAD)
    List<Competition> findAllCompetitionsByFirstYear(@Param("firstYear") int firstYear);

    //List<Competition> findAllByFirstYear(int firstYear);
    List<Competition> findFirstByOrderByPrizePoolDesc();
    List<Competition> findAllByOrderByFirstYearAsc();
}
