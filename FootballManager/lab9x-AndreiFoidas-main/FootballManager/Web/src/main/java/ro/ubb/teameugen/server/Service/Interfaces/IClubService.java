package ro.ubb.teameugen.server.Service.Interfaces;

import ro.ubb.teameugen.common.Domain.Entities.Club;

import java.util.List;

public interface IClubService {

    Club getClubById(int id);
    Iterable<Club> getClubs();
    void addClub(Club club);
    void deleteClub(int id);
    void updateClub(Club club);
    List<Club> filterClubsByOwner(String ownerName);
    List<Club> filterClubsByCountry(String country);
    List<Club> getClubWithBiggestBudget();
    List<Club> sortClubsByName();
    void deleteClubByCountry(String country);

}
