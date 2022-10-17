package ro.ubb.teameugen.server.Repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import ro.ubb.teameugen.common.Domain.Entities.Club;
import ro.ubb.teameugen.common.Domain.Entities.Competition;

import java.util.List;

@Component("ClubRepositoryNativeSQL")
public interface ClubRepository extends IRepository<Club, Integer>, ClubRepositoryCustom {
    @Query("select distinct c from Club c")
    @EntityGraph(value = "clubs",
            type = EntityGraph.EntityGraphType.LOAD)
    List<Club> findAllClubs();

    @Query("select distinct c from Club c where c.ownerName = :ownerName")
    @EntityGraph(value = "clubs",
            type = EntityGraph.EntityGraphType.LOAD)
    List<Club> findAllClubsByOwnerName(@Param("ownerName") String ownerName);

    List<Club> findAllByOwnerName(String ownerName);
    List<Club> findAllByCountry(String country);
    List<Club> findFirstByOrderByBudgetDesc();
    List<Club> findAllByOrderByNameAsc();
    int deleteAllByCountry(String country);
}
