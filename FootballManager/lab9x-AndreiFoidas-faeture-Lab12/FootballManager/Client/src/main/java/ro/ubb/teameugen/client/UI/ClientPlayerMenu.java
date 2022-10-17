package ro.ubb.teameugen.client.UI;

import ro.ubb.teameugen.client.Service.PlayerClientService;
import ro.ubb.teameugen.common.Domain.Entities.Player;

import java.time.LocalDate;
import java.util.Scanner;

public class ClientPlayerMenu extends ClientBaseTextMenu {
    private final Scanner scanner;
    private final PlayerClientService playerClientService;

    public ClientPlayerMenu(Scanner scanner, PlayerClientService playerClientService) {
        this.scanner = scanner;
        this.playerClientService = playerClientService;
    }
    
    @Override
    public void start() {
        while (true) {
            try{
                int option;
                option = readInteger(scanner, getMenu());

                switch (option) {
                    case 1 -> addPlayer();
                    case 2 -> deletePlayer();
                    case 3 -> updatePlayer();
                    case 4 -> printAllPlayers();
                    case 5 -> filterPlayers();
                    case 6 -> getYoungestPlayer();
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

    private void getYoungestPlayer() {
        playerClientService.getYoungestPlayer().forEach(System.out::println);
    }

    private void filterPlayers() {
        var position = requestPosition();

        playerClientService.filterPlayersByPosition(position).forEach(System.out::println);
    }

    private void updatePlayer() {
        var Player = requestPlayerInfo();

        playerClientService.updatePlayer(Player);
        System.out.println("Player updated successfully!");
    }

    private void deletePlayer() {
        var id = requestID();

        playerClientService.deletePlayer(id);
        System.out.println("Player deleted successfully!");
    }

    private void printAllPlayers() {
        playerClientService.getPlayers().forEach(System.out::println);
    }

    private void addPlayer() {
        var Player = requestPlayerInfo();

        playerClientService.addPlayer(Player);
        System.out.println("Player added successfully.");
    }

    @Override
    protected String getMenu() {
        String stringRepresentation = "";

        stringRepresentation += "1 - Add a Player" + "\n";
        stringRepresentation += "2 - Delete a Player" + "\n";
        stringRepresentation += "3 - Update a Player" + "\n";
        stringRepresentation += "4 - Print all added Players" + "\n";
        stringRepresentation += "5 - Filter Players by position" + "\n";
        stringRepresentation += "6 - Get youngest Player(s)" + "\n";
        stringRepresentation += "0 - Exit submenu" + "\n";
        stringRepresentation += "-->";

        return stringRepresentation;
    }

    private int requestID() {
        int id;
        id =  readInteger(scanner, "Enter ID: ");
        return id;
    }

    private String requestPosition() {
        String position;
        position = readString(scanner, "Enter position: ");
        return position;
    }

    private Player requestPlayerInfo() {
        String playerName;
        LocalDate birthday;
        double wage;
        int clubId;
        String mainPosition;

        System.out.println("\nLet's add a Player:");

        playerName = readString(scanner, "Player name: ");

        birthday=readDate(scanner, "Birthday: ");

        wage = readDouble(scanner, "Wage: ");

        clubId = readInteger(scanner, "Club ID: ");

        mainPosition = readString(scanner, "Main position: ");

        //var player = new Player(playerName, birthday, wage, clubId, mainPosition);
        //player.setId(0);

        //return player;
        return null;
    }

}
