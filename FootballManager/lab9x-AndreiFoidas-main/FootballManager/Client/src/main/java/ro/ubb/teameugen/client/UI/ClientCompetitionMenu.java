package ro.ubb.teameugen.client.UI;

import ro.ubb.teameugen.client.Service.CompetitionClientService;
import ro.ubb.teameugen.common.Domain.Entities.Competition;
import java.util.Scanner;

public class ClientCompetitionMenu extends ClientBaseTextMenu {
    private final Scanner scanner;
    private final CompetitionClientService competitionClientService;

    public ClientCompetitionMenu(Scanner scanner, CompetitionClientService competitionClientService) {
        this.scanner = scanner;
        this.competitionClientService = competitionClientService;
    }

    @Override
    public void start() {
        while (true) {
            try {
                int option;
                option = readInteger(scanner, getMenu());

                switch (option) {
                    case 1 -> addCompetition();
                    case 2 -> deleteCompetition();
                    case 3 -> updateCompetition();
                    case 4 -> printAllCompetitions();
                    case 5 -> filterCompetitions();
                    case 6 -> sortCompetitions();
                    case 7 -> getCompetitionBiggestPrizePool();
                    case 0 -> {
                        return;
                    }
                    default -> System.out.println("Invalid option.");
                }
            } catch (Exception e) {
                prettyPrintThrowable(e);
            }
        }
    }

    private void getCompetitionBiggestPrizePool() {
        this.competitionClientService.getCompetitionWithBiggestPrizePool().forEach(System.out::println);
    }

    private void filterCompetitions() {
        var year = requestYear();
        this.competitionClientService.filterCompetitionByYear(year).forEach(System.out::println);
    }

    private void sortCompetitions() {
        this.competitionClientService.sortCompetitionsByYear().forEach(System.out::println);
    }

    private void addCompetition() {
        var competition = requestCompetitionInfo();
        this.competitionClientService.addCompetition(competition);
        System.out.println("Competition added successfully!");
    }

    private void updateCompetition() {
        var competition = requestCompetitionInfo();
        int id=requestID();
        competition.setId(id);
        this.competitionClientService.updateCompetition(competition);
        System.out.println("Competition updated successfully!");
    }

    private void deleteCompetition() {
        var id = requestID();
        this.competitionClientService.deleteCompetition(id);
        System.out.println("Competition deleted successfully!");
    }

    private void printAllCompetitions() {
        this.competitionClientService.getCompetitions().forEach(System.out::println);
    }

    @Override
    protected String getMenu() {
        String stringRepresentation = "";

        stringRepresentation += "1 - Add a Competition" + "\n";
        stringRepresentation += "2 - Delete a Competition" + "\n";
        stringRepresentation += "3 - Update a Competition" + "\n";
        stringRepresentation += "4 - Print all added Competitions" + "\n";
        stringRepresentation += "5 - Filter competitions by year" + "\n";
        stringRepresentation += "6 - Sort all Competitions by the first year" + "\n";
        stringRepresentation += "7 - Get the Competition(s) with the biggest prize pool" + "\n";
        stringRepresentation += "0 - Exit submenu" + "\n";
        stringRepresentation += "-->";

        return stringRepresentation;
    }

    private int requestID() {
        int id;
        id = readInteger(scanner, "Enter ID: ");
        return id;
    }

    private int requestYear() {
        int year;
        year = readInteger(scanner, "Enter year: ");
        return year;
    }

    private Competition requestCompetitionInfo() {
        String competitionName;
        int prizePool;
        int firstYear;

        System.out.println("\nEnter Competition information:");

        competitionName = readString(scanner, "Competition name: ");
        prizePool = readInteger(scanner, "Prize pool: ");
        firstYear = readInteger(scanner, "First year: ");

        var competition = new Competition(competitionName, prizePool, firstYear);
        competition.setId(0);
        return competition;
    }
}

