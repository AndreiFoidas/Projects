package ro.ubb.teameugen.common.Domain.Entities;

import javax.persistence.Entity;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Entity
public class Club extends BaseEntity<Integer> {
    private String name;
    private String country;
    private int budget; // in M euro
    private String ownerName;
    private String managerName;
    private String stadiumName;

    public Club(String name, String country, int budget, String ownerName, String managerName, String stadiumName){

        this.name = name;
        this.country = country;
        this.budget = budget;
        this.ownerName = ownerName;
        this.managerName = managerName;
        this.stadiumName = stadiumName;
    }

    protected Club() {}

    public String getName() {
        return this.name;
    }

    public String getCountry() {
        return this.country;
    }

    public int getBudget() {
        return this.budget;
    }

    public String getOwnerName() {
        return this.ownerName;
    }

    public String getManagerName() {
        return this.managerName;
    }

    public String getStadiumName() {
        return this.stadiumName;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public void setCountry(String newCountry) {
        this.country = newCountry;
    }

    public void setBudget(int newBudget) {
        this.budget = newBudget;
    }

    public void setOwnerName(String newOwnerName) {
        this.ownerName = newOwnerName;
    }

    public void setManagerName(String newManagerName) {
        this.managerName = newManagerName;
    }

    public void setStadiumName(String newStadiumName) {
        this.stadiumName = newStadiumName;
    }

    @Override
    public boolean equals(Object newObject) {
        if (this == newObject) {
            return true;
        }

        if (newObject == null || getClass() != newObject.getClass()) {
            return false;
        }

        Club newClub = (Club) newObject; // at this point, we know it's an object of type Club
        return newClub.name.equals(this.name) &&
                newClub.country.equals(this.country) &&
                newClub.budget == this.budget &&
                newClub.ownerName.equals(this.ownerName) &&
                newClub.managerName.equals(this.managerName) &&
                newClub.stadiumName.equals(this.stadiumName) &&
                newClub.getId().equals(this.getId());
    }

    @Override
    public int hashCode() {
        int result = this.name.hashCode() % 600007;
        result = (31 * result + this.country.hashCode()) % 600007;
        result = (31 * result + this.budget) % 600007;
        result = (31 * result + this.ownerName.hashCode()) % 600007;
        result = (31 * result + this.managerName.hashCode()) % 600007;
        result = (31 * result + this.stadiumName.hashCode()) % 600007;
        return result;
    }

    @Override
    public String toString() {
        String stringRepresentation = "";

        stringRepresentation += "Club:\n";
        stringRepresentation += "\tId = " + this.getId() + "\n";
        stringRepresentation += "\tName = " + this.name + "\n";
        stringRepresentation += "\tCountry = " + this.country + "\n";
        stringRepresentation += "\tBudget = " + this.budget + "\n";
        stringRepresentation += "\tOwnerName = " + this.ownerName + "\n";
        stringRepresentation += "\tManagerName = " + this.managerName + "\n";
        stringRepresentation += "\tStadiumName = " + this.stadiumName + "\n\n";

        return stringRepresentation;
    }
}