package ro.ubb.teameugen.client.UI;
 
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class ClientBaseTextMenu {
    public abstract void start();
    protected abstract String getMenu();

    public int readInteger(Scanner scanner, String message){
        int number;
        System.out.print(message);
        try{
            number = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Invalid argument given, please try again.");
            scanner.nextLine();
            return readInteger(scanner, message);
        }

        return number;
    }

    public double readDouble(Scanner scanner, String message){
        double number;
        System.out.print(message);
        try{
            number = scanner.nextDouble();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Invalid argument given, please try again.");
            scanner.nextLine();
            return readDouble(scanner, message);
        }

        return number;
    }

    public LocalDate readDate(Scanner scanner, String message){
        LocalDate date;
        System.out.print(message);
        try{
            String string = scanner.nextLine();
            date = LocalDate.parse(string, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        } catch (DateTimeParseException e) {
            System.out.println("Invalid argument given, please try again.");
            return readDate(scanner, message);
        }

        return date;
    }

    public String readString(Scanner scanner, String message){
        String string;
        System.out.print(message);
        try{
            string=scanner.nextLine();
        } catch (DateTimeParseException e) {
            System.out.println("Invalid argument given, please try again.");
            return readString(scanner, message);
        }

        return string;
    }

    protected void prettyPrintThrowable(Throwable throwable) {
        System.out.println("Oops! An " + throwable.getClass().getSimpleName() + " has occurred.");
        System.out.println("Provided message: " + throwable.getMessage());
        throwable.printStackTrace();
    }
}
