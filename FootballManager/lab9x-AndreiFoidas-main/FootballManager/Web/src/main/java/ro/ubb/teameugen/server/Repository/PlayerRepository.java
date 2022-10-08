package ro.ubb.teameugen.server.Repository;

import ro.ubb.teameugen.common.Domain.Entities.Player;

import java.util.List;

public interface PlayerRepository extends IRepository<Player, Integer> {
    List<Player> findAllByMainPosition(String mainPosition);
    List<Player> findFirstByOrderByBirthdayDesc();
}
