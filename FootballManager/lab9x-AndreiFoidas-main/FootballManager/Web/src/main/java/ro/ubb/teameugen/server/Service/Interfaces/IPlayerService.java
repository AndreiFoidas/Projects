package ro.ubb.teameugen.server.Service.Interfaces;

import ro.ubb.teameugen.common.Domain.Entities.Player;

import java.util.List;

public interface IPlayerService {

    Player getPlayerById(int id);
    Iterable<Player> getPlayers();
    void addPlayer(Player player);
    void deletePlayer(int id);
    void updatePlayer(Player player);
    List<Player> getYoungestPlayer();
    List<Player> filterPlayersByPosition(String position);

}
