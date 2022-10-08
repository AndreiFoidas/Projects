package ro.ubb.teameugen.common.Domain.DTOs;

import java.util.Objects;

public class CompetitionDto extends BaseDto {
    private String name;
    private int prizePool;
    private int firstYear;

    public CompetitionDto(int id, String name, int prize, int firstYear) {
        super(id);
        this.name = name;
        this.prizePool = prize;
        this.firstYear = firstYear;
    }

    public CompetitionDto() {}

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
        CompetitionDto that = (CompetitionDto) newObject;
        return this.prizePool == that.prizePool &&
                this.firstYear == that.firstYear &&
                Objects.equals(this.name, that.name) &&
                this.getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.prizePool, this.firstYear);
    }

    @Override
    public String toString() {
        return "CompetitionDto{" +
                "name='" + name + '\'' +
                ", prizePool=" + prizePool +
                ", firstYear=" + firstYear +
                "} " + super.toString();
    }
}
