package ro.ubb.teameugen.common.Domain.Entities;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Entity
public class Player extends BaseEntity<Integer> {
    private String playerName;
    private LocalDate birthday;
    private double wage;
    private int clubID;
    private String mainPosition;

    public Player(String playerName, LocalDate birthday, double wage, int clubID, String mainPosition) {

        this.playerName = playerName;
        this.birthday = birthday;
        this.wage = wage;
        this.clubID = clubID;
        this.mainPosition = mainPosition;
    }

    protected Player() {}

    public String getPlayerName() {
        return this.playerName;
    }

    public LocalDate getBirthday() {
        return this.birthday;
    }

    public double getWage() {
        return this.wage;
    }

    public String getMainPosition() {
        return this.mainPosition;
    }

    public int getClubID() {
        return this.clubID;
    }

    public void setPlayerName(String newPlayerName) {
        this.playerName = newPlayerName;
    }

    public void setBirthday(LocalDate newBirthday) {
        this.birthday = newBirthday;
    }

    public void setWage(double newWage) {
        this.wage = newWage;
    }

    public void setClubID(int newClubID) {
        this.clubID = newClubID;
    }

    public void setMainPosition(String newMainPosition) {
        this.mainPosition = newMainPosition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        return this.playerName.equals(player.playerName) &&
                this.birthday.equals(player.birthday) &&
                this.wage == player.wage &&
                this.clubID == player.clubID &&
                this.mainPosition.equals(player.mainPosition) &&
                this.getId().equals(player.getId());
    }

    @Override
    public int hashCode() {
        int result = this.playerName.hashCode() % 600007;
        result = 31 * (result + this.birthday.hashCode()) % 600007;
        result = 31 * (result + String.valueOf(this.wage).hashCode()) % 600007;
        result = 31 * (result + this.clubID) % 600007;
        result = 31 * (result + this.mainPosition.hashCode()) % 600007;
        return result;
    }

    @Override
    public String toString() {
        String stringRepresentation = "";

        stringRepresentation += "Player:\n";
        stringRepresentation += "\tId = " + this.getId() + "\n";
        stringRepresentation += "\tName = " + this.playerName + "\n";
        stringRepresentation += "\tBirthday = " + this.birthday.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + "\n";
        stringRepresentation += "\tWage = " + this.wage + "\n";
        stringRepresentation += "\tClubID = " + this.clubID + "\n";
        stringRepresentation += "\tMainPosition = " + this.mainPosition + "\n\n";

        return stringRepresentation;
    }
}
