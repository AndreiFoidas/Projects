package ro.ubb.teameugen.server.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.ubb.teameugen.common.Domain.Entities.*;
import ro.ubb.teameugen.server.Domain.Validation.*;
import ro.ubb.teameugen.server.Domain.Validation.Interfaces.BaseValidator;

@Configuration
public class ValidationConfiguration {

    @Bean
    BaseValidator<Player> playerBaseValidator() {
        return new PlayerValidator();
    }

    @Bean
    BaseValidator<MatchPlayerRelation> matchPlayerRelationBaseValidator() {
        return new MatchPlayerRelationValidator();
    }

    @Bean
    BaseValidator<Match> matchBaseValidator() {
        return new MatchValidator();
    }

    @Bean
    BaseValidator<Competition> competitionBaseValidator() {
        return new CompetitionValidator();
    }

    @Bean
    BaseValidator<Club> clubBaseValidator() {
        return new ClubValidator();
    }

}
