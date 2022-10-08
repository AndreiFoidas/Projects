package ro.ubb.teameugen.common.Domain.Entities;

import javax.persistence.Entity;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Entity
public class Competition extends BaseEntity<Integer> {
    private String name;
    private int prizePool;
    private int firstYear;

    public Competition(String name, int prize, int firstYear) {

        this.name = name;
        this.prizePool = prize;
        this.firstYear = firstYear;
    }

    protected Competition() {}

    public String getName() {
        return name;
    }

    public int getPrizePool() {
        return prizePool;
    }

    public int getFirstYear() {
        return firstYear;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrizePool(int prizePool) {
        this.prizePool = prizePool;
    }

    public void setFirstYear(int firstYear) {
        this.firstYear = firstYear;
    }

    @Override
    public boolean equals(Object newObject) {
        if (this == newObject) return true;
        if (newObject == null || getClass() != newObject.getClass()) return false;
        Competition that = (Competition) newObject;
        return this.prizePool == that.prizePool &&
                this.firstYear == that.firstYear &&
                Objects.equals(this.name, that.name) &&
                this.getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        int result = name.hashCode() % 600007;
        result = 31 * (result + prizePool) % 600007;
        result = 31 * (result + firstYear) % 600007;
        return result;
    }

    @Override
    public String toString() {
        String stringRepresentation = "";

        stringRepresentation += "Competition:\n";
        stringRepresentation += "\tId = " + this.getId() + "\n";
        stringRepresentation += "\tName = " + this.name + "\n";
        stringRepresentation += "\tPrizePool = " + this.prizePool + "\n";
        stringRepresentation += "\tFirstYear = " + this.firstYear + "\n\n";

        return stringRepresentation;
    }
}
