package ro.ubb.teameugen.client.UI;

import ro.ubb.teameugen.client.Service.MprClientService;
import ro.ubb.teameugen.common.Domain.Entities.MatchPlayerRelation;

import java.util.Scanner;

public class ClientMPRMenu extends ClientBaseTextMenu {

    private final Scanner scanner;
    private final MprClientService mprClientService;

    public ClientMPRMenu(Scanner scanner, MprClientService mprClientService) {
        this.scanner = scanner;
        this.mprClientService = mprClientService;
    }

    @Override
    public void start() {
        while (true) {
            try{
                int option;
                option = readInteger(scanner, getMenu());

                switch (option) {
                    case 1 -> addMPR();
                    case 2 -> deleteMPR();
                    case 3 -> updateMPR();
                    case 4 -> printAllMPR();
                    case 5 -> filterMPR();
                    case 6 -> getMPRMostGoalsScored();
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

    private void getMPRMostGoalsScored() {
        this.mprClientService.getMprWithMostGoalsScored().forEach(System.out::println);
    }

    private void filterMPR() {
        var goals = requestGoals();
        this.mprClientService.filterMprByGoals(goals).forEach(System.out::println);
    }

    private void updateMPR() {
        var MPR = requestMPRInfo();
        var id = requestID();
        MPR.setId(id);

        this.mprClientService.updateMpr(MPR);
        System.out.println("MatchPlayerRelation updated successfully!");

    }

    private void deleteMPR() {
        var id = requestID();

        this.mprClientService.deleteMpr(id);
        System.out.println("MatchPlayerRelation deleted successfully!");
    }

    private void printAllMPR() {
       this.mprClientService.getMprs().forEach(System.out::println);
    }

    private void addMPR() {
        var MPR = requestMPRInfo();

        this.mprClientService.addMpr(MPR);
        System.out.println("MatchPlayerRelation added successfully!");
    }

    @Override
    protected String getMenu() {
        String stringRepresentation = "";

        stringRepresentation += "1 - Add a Match - Player Relationship" + "\n";
        stringRepresentation += "2 - Delete a Match - Player Relationship" + "\n";
        stringRepresentation += "3 - Update a Match - Player Relationship" + "\n";
        stringRepresentation += "4 - Print all added Match - Player Relationships" + "\n";
        stringRepresentation += "5 - Filter Match - Player Relationships with a number of goals smaller than a given number" + "\n";
        stringRepresentation += "6 - Get the Match - Player Relationship(s) with the most goals scored"+ "\n";
        stringRepresentation += "-->";

        return stringRepresentation;
    }

    private int requestID() {
        int id;
        id =  readInteger(scanner, "Enter ID: ");
        return id;
    }

    private int requestGoals() {
        int goals;
        goals =  readInteger(scanner, "Enter maximum number of goals: ");
        return goals;
    }

    private MatchPlayerRelation requestMPRInfo() {
        int matchID;
        int playerID;
        int goals;
        int minutes;

        System.out.println("\nLet's add a Match - Player Relationship:");


        matchID = readInteger(scanner, "Match ID: ");

        playerID = readInteger(scanner, "Player ID: ");

        goals = readInteger(scanner, "Goals: ");

        minutes = readInteger(scanner, "Minutes played: ");

        var mpr = new MatchPlayerRelation(matchID, playerID, goals, minutes);
        mpr.setId(0);

        return mpr;
    }

}
