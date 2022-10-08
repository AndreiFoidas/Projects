package ro.ubb.teameugen.server.Repository;

import ro.ubb.teameugen.common.Domain.Entities.Club;

import java.util.List;

public interface ClubRepository extends IRepository<Club, Integer> {
    List<Club> findAllByOwnerName(String ownerName);
    List<Club> findAllByCountry(String country);
    List<Club> findFirstByOrderByBudgetDesc();
    List<Club> findAllByOrderByNameAsc();
    int deleteAllByCountry(String country);
}
