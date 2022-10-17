package ro.ubb.teameugen.client.UI;

import ro.ubb.teameugen.client.Service.ClubClientService;
import ro.ubb.teameugen.common.Domain.Entities.Club;

import java.util.Scanner;

public class ClientClubMenu extends ClientBaseTextMenu {

    private final Scanner scanner;
    private final ClubClientService clubClientService;

    public ClientClubMenu(Scanner scanner, ClubClientService clubClientService) {
        this.scanner = scanner;
        this.clubClientService = clubClientService;
    }

    @Override
    public void start() {
        while (true) {
            try{
                int option;
                option = readInteger(this.scanner, getMenu());

                switch (option) {
                    case 1 -> addClub();
                    case 2 -> deleteClub();
                    case 3 -> updateClub();
                    case 4 -> printAllClubs();
                    case 5 -> filterClubs();
                    case 6 -> deleteByCountry();
                    case 7 -> getClubBiggestBudget();
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

    private void getClubBiggestBudget() {
        clubClientService.getClubWithBiggestBudget().forEach(System.out::println);
    }

    private void filterClubs() {
        var country = requestCountry();

        clubClientService.filterClubsByCountry(country).forEach(System.out::println);
    }

    private void updateClub() {
        var club = requestClubInfo();

        clubClientService.updateClub(club);
        System.out.println("Club updated successfully!");
    }

    private void deleteByCountry(){
        var country = requestCountry();

        clubClientService.deleteClubByCountry(country);
        System.out.println("Clubs deleted successfully!");
    }

    private void deleteClub() {
        var id = requestID();

        clubClientService.deleteClub(id);
        System.out.println("Club deleted successfully!");
    }

    private void printAllClubs() {
        clubClientService.getClubs().forEach(System.out::println);
    }

    private void addClub() {
        var club = requestClubInfo();

        clubClientService.addClub(club);
        System.out.println("Club added successfully.");
    }

    @Override
    protected String getMenu() {
        String stringRepresentation = "";

        stringRepresentation += "1 - Add a Club" + "\n";
        stringRepresentation += "2 - Delete a Club" + "\n";
        stringRepresentation += "3 - Update a Club" + "\n";
        stringRepresentation += "4 - Print all added Clubs" + "\n";
        stringRepresentation += "5 - Filter Clubs by a given country" + "\n";
        stringRepresentation += "6 - Delete Clubs from a given country" + "\n";
        stringRepresentation += "7 - Get Club(s) with biggest budget" + "\n";
        stringRepresentation += "0 - Exit submenu"+ "\n";
        stringRepresentation += "-->";

        return stringRepresentation;
    }

    private int requestID() {
        int id;
        id = readInteger(scanner, "Enter ID: ");
        return id;
    }

    private String requestCountry() {
        String country;
        country = readString(scanner, "Enter country: ");
        return country;
    }

    private Club requestClubInfo() {
        String clubName;
        String country;
        int budget;
        String ownerName;
        String managerName;
        String stadiumName;

        System.out.println("\nEnter Club information:");

        clubName = readString(scanner, "Club name: ");
        country = readString(scanner, "Country: ");
        budget = readInteger(scanner, "Budget: ");
        ownerName = readString(scanner, "Owner name: ");
        managerName = readString(scanner, "Manager name: ");
        stadiumName = readString(scanner, "Stadium name: ");

        var club = new Club(clubName, country, budget, ownerName, managerName, stadiumName);
        club.setId(0);

        return club;
    }

}

