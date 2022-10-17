package ro.ubb.teameugen.server.Repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Component;
import ro.ubb.teameugen.common.Domain.Entities.Club;

import java.util.List;

@NoRepositoryBean
public interface ClubRepositoryCustom {
    List<Club> findClubsByCountry(String country);
    List<Club> sortClubsByName();
}
