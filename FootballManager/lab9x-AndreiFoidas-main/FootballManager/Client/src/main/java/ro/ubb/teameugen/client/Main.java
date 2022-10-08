package ro.ubb.teameugen.client;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ro.ubb.teameugen.client.Configuration.ClientConfiguration;
import ro.ubb.teameugen.client.UI.ClientCommandLineInterface;

public class Main {

    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ClientConfiguration.class);

        var clientConsole = context.getBean(ClientCommandLineInterface.class);
        clientConsole.start();
    }
}
