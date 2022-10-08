package ro.ubb.teameugen.server;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import ro.ubb.teameugen.server.Configuration.ServerConfiguration;

import javax.servlet.ServletContext;

public class Initializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        var context = new AnnotationConfigWebApplicationContext();
        context.register(ServerConfiguration.class);
        context.setServletContext(servletContext);

        servletContext.addListener(new ContextLoaderListener(context));

        var dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(context));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/*");
    }
}
