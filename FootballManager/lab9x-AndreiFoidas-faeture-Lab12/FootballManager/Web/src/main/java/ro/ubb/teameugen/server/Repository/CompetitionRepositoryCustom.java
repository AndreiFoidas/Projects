package ro.ubb.teameugen.server.Repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Component;
import ro.ubb.teameugen.common.Domain.Entities.Competition;

import java.util.List;

@NoRepositoryBean
public interface CompetitionRepositoryCustom {
    List<Competition> findCompetitionsByName(String name);
    Competition findCompetitionWithBiggestPrizePool();
}
