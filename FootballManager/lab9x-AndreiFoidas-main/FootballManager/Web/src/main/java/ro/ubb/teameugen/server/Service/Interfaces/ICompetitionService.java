package ro.ubb.teameugen.server.Service.Interfaces;

import ro.ubb.teameugen.common.Domain.Entities.Competition;

import java.util.List;

public interface ICompetitionService {

    Competition getCompetitionById(int id);
    Iterable<Competition> getCompetitions();
    void addCompetition(Competition competition);
    void deleteCompetition(int id);
    void updateCompetition(Competition competition);
    List<Competition> getCompetitionWithBiggestPrizePool();
    List<Competition> filterCompetitionByYear(Integer year);
    List<Competition> sortCompetitionsByYear();

}
