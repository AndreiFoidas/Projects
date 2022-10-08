package ro.ubb.teameugen.client.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import ro.ubb.teameugen.client.Service.*;
import ro.ubb.teameugen.client.UI.ClientCommandLineInterface;

@Configuration
@ComponentScan({ "ro.ubb.teameugen.client", "ro.ubb.teameugen.common" })
public class ClientConfiguration {

    @Autowired
    private ClubClientService clubClientService;

    @Autowired
    private CompetitionClientService competitionClientService;

    @Autowired
    private MatchClientService matchClientService;

    @Autowired
    private MprClientService mprClientService;

    @Autowired
    private PlayerClientService playerClientService;

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    ClientCommandLineInterface clientConsole() {
        return new ClientCommandLineInterface(clubClientService, competitionClientService, matchClientService, mprClientService, playerClientService);
    }

}
