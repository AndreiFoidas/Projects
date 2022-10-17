package ro.ubb.teameugen.client.UI;

import ro.ubb.teameugen.client.Service.*;

import java.util.Scanner;

public class ClientCommandLineInterface extends ClientBaseTextMenu {

    private final Scanner scanner;
    private final ClientClubMenu clubMenu;
    private final ClientCompetitionMenu competitionMenu;
    private final ClientMatchMenu matchMenu;
    private final ClientPlayerMenu playerMenu;
    private final ClientMPRMenu mprMenu;

    public ClientCommandLineInterface(ClubClientService clubService, CompetitionClientService competitionService, MatchClientService matchService, MprClientService mprService, PlayerClientService playerService) {
        this.scanner = new Scanner(System.in);
        this.clubMenu = new ClientClubMenu(this.scanner, clubService);
        this.competitionMenu = new ClientCompetitionMenu(this.scanner, competitionService);
        this.matchMenu = new ClientMatchMenu(this.scanner, matchService);
        this.playerMenu = new ClientPlayerMenu(this.scanner, playerService);
        this.mprMenu = new ClientMPRMenu(this.scanner, mprService);
    }

    @Override
    public void start() {
        printHeader();

        while (true) {
            int option = readInteger(scanner, getMenu());

            switch (option) {
                case 0 -> System.exit(0);
                case 1 -> this.clubMenu.start();
                case 2 -> this.competitionMenu.start();
                case 3 -> this.matchMenu.start();
                case 4 -> this.playerMenu.start();
                case 5 -> this.mprMenu.start();
                default -> System.out.println("Invalid option selected.");
            }
        }
    }


    private void printHeader() {
        System.out.println("--== Football Teams Manager / Group 923/2 / Team Eugen ==--\n");
    }

    @Override
    protected String getMenu() {
        String stringRepresentation = "";

        stringRepresentation += "Please choose an option from the menu:" + "\n";
        stringRepresentation += "1 - Club menu" + "\n";
        stringRepresentation += "2 - Competition menu" + "\n";
        stringRepresentation += "3 - Match menu" + "\n";
        stringRepresentation += "4 - Player menu" + "\n";
        stringRepresentation += "5 - Match - Player Relationship menu" + "\n";
        stringRepresentation += "0 - Exit the program\n";
        stringRepresentation += "-->";

        return stringRepresentation;
    }


}
