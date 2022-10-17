package ro.ubb.teameugen.server.Configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.data.repository.support.Repositories;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "ro.ubb.teameugen.server", "ro.ubb.teameugen.common" })
                //excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX, pattern="ro.ubb.teameugen.server.Repository.*"))
@Import({ AppLocalConfiguration.class, ValidationConfiguration.class, WebConfiguration.class })
public class ServerConfiguration {

}
