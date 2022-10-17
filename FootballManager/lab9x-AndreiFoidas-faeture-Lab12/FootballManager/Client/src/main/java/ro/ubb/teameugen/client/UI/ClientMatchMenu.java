package ro.ubb.teameugen.client.UI;

import ro.ubb.teameugen.client.Service.MatchClientService;
import ro.ubb.teameugen.common.Domain.Entities.Match;
import java.time.LocalDate;
import java.util.Scanner;

public class ClientMatchMenu extends ClientBaseTextMenu {
    private final Scanner scanner;
    private final MatchClientService matchClientService;

    public ClientMatchMenu(Scanner scanner, MatchClientService matchClientService) {
        this.scanner = scanner;
        this.matchClientService = matchClientService;
    }

    @Override
    public void start() {
        while (true) {
            try{
                int option;
                option = readInteger(scanner, getMenu());

                switch (option) {
                    case 1 -> addMatch();
                    case 2 -> deleteMatch();
                    case 3 -> updateMatch();
                    case 4 -> printAllMatches();
                    case 5 -> filterMatches();
                    case 6 -> calculateNumberOfMatches();
                    case 7 -> getMatchWithLowestAttendance();
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

    private void getMatchWithLowestAttendance() {
        this.matchClientService.getMatchWithLowestAttendance().forEach(System.out::println);
    }

    private void filterMatches() {
        var att = requestAttendance();
        this.matchClientService.filterMatchByAttendance(att).forEach(System.out::println);
    }

    private void addMatch() {
        var match = requestMatchInfo();
        this.matchClientService.addMatch(match);
        System.out.println("Match added successfully!");
    }

    private void updateMatch() {
        var match = requestMatchInfo();
        int id=requestID();
        match.setId(id);
        this.matchClientService.updateMatch(match);
        System.out.println("Match updated successfully!");
    }

    private void deleteMatch() {
        var id = requestID();
        this.matchClientService.deleteMatch(id);
        System.out.println("Match deleted successfully!");
    }

    private void printAllMatches() {
        this.matchClientService.getMatches().forEach(System.out::println);
    }

    private void calculateNumberOfMatches() {
        String stadiumName = requestStadiumName();
        System.out.println(this.matchClientService.collectNumberOfMatchesPlayedInStadium(stadiumName));
    }

    @Override
    protected String getMenu() {
        String stringRepresentation = "";

        stringRepresentation += "1 - Add a Match" + "\n";
        stringRepresentation += "2 - Delete a Match" + "\n";
        stringRepresentation += "3 - Update a Match" + "\n";
        stringRepresentation += "4 - Print all added Matches" + "\n";
        stringRepresentation += "5 - Filter matches by attendance" + "\n";
        stringRepresentation += "6 - Get the number of matches played at a specific stadium" + "\n";
        stringRepresentation += "7 - Get the Match(es) with the lowest attendance" + "\n";
        stringRepresentation += "0 - Exit submenu" + "\n";
        stringRepresentation += "-->";

        return stringRepresentation;
    }

    private int requestID() {
        int id;
        id = readInteger(scanner, "Enter ID: ");
        return id;
    }

    private int requestAttendance() {
        int att;
        att = readInteger(scanner, "Enter attendance: ");
        return att;
    }

    private String requestStadiumName() {
        String stadiumName;
        stadiumName = readString(scanner, "Enter stadium name: ");
        return stadiumName;
    }

    private Match requestMatchInfo() {
        int c1;
        int c2;
        LocalDate date;
        String stadium;
        int competitionID;
        int attendance;

        System.out.println("\nEnter Match information:");

        c1 = readInteger(scanner, "First Club ID: ");
        c2 = readInteger(scanner, "Second Club ID: ");
        date = readDate(scanner, "Date: ");
        stadium = readString(scanner, "Stadium name: ");
        competitionID = readInteger(scanner, "Competition ID: ");
        attendance = readInteger(scanner, "Attendance: ");

        //var match = new Match(c1, c2, date, stadium, competitionID, attendance);
        //match.setId(0);
        //return match;
        return null;
    }
}
