package ro.ubb.teameugen.server.Configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan({ "ro.ubb.teameugen.server", "ro.ubb.teameugen.common" })
@Import({ AppLocalConfiguration.class, ValidationConfiguration.class, WebConfiguration.class })
public class ServerConfiguration {

}
