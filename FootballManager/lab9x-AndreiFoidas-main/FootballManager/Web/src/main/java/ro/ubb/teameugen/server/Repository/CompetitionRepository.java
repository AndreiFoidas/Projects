package ro.ubb.teameugen.server.Repository;

import ro.ubb.teameugen.common.Domain.Entities.Competition;

import java.util.List;

public interface CompetitionRepository extends IRepository<Competition, Integer> {
    List<Competition> findAllByFirstYear(int firstYear);
    List<Competition> findFirstByOrderByPrizePoolDesc();
    List<Competition> findAllByOrderByFirstYearAsc();
}
