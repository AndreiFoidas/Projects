package ro.ubb.teameugen.common.Domain.Entities;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Entity
public class Match extends BaseEntity<Integer> {
    private int clubID1;
    private int clubID2;
    private LocalDate date;
    private String stadiumName;
    private int competitionID;
    private int attendance;

    public Match(int c1, int c2, LocalDate date, String stadium, int competition, int attendance) {

        this.clubID1 = c1;
        this.clubID2 = c2;
        this.date = date;
        this.stadiumName = stadium;
        this.competitionID = competition;
        this.attendance = attendance;
    }

    protected Match() {}

    public int getAttendance() {
        return attendance;
    }

    public int getClubID1() {
        return clubID1;
    }

    public int getClubID2() {
        return clubID2;
    }

    public int getCompetitionID() {
        return competitionID;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getStadiumName() {
        return stadiumName;
    }

    public void setAttendance(int attendance) {
        this.attendance = attendance;
    }

    public void setClubID1(int clubID1) {
        this.clubID1 = clubID1;
    }

    public void setClubID2(int clubID2) {
        this.clubID2 = clubID2;
    }

    public void setCompetitionID(int competitionID) {
        this.competitionID = competitionID;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setStadiumName(String stadiumName) {
        this.stadiumName = stadiumName;
    }

    @Override
    public boolean equals(Object newObject) {
        if (this == newObject) return true;
        if (newObject == null || getClass() != newObject.getClass()) return false;
        Match match = (Match) newObject;
        return this.clubID1 == match.clubID1 &&
                this.clubID2 == match.clubID2 &&
                this.competitionID == match.competitionID &&
                this.attendance == match.attendance &&
                Objects.equals(this.date, match.date) &&
                Objects.equals(this.stadiumName, match.stadiumName)&&
                this.getId().equals(match.getId());
    }

    @Override
    public int hashCode() {
        int result = date.hashCode() % 600007;
        result = (31 * result + stadiumName.hashCode()) % 600007;
        result = (31 * result + clubID1) % 600007;
        result = (31 * result + clubID2) % 600007;
        result = (31 * result + competitionID) % 600007;
        result = (31 * result + attendance) % 600007;
        return result;
    }

    @Override
    public String toString() {
        String stringRepresentation = "";

        stringRepresentation += "Match:\n";
        stringRepresentation += "\tId = " + this.getId() + "\n";
        stringRepresentation += "\tClubID1 = " + this.clubID1 + "\n";
        stringRepresentation += "\tClubID2 = " + this.clubID2 + "\n";
        stringRepresentation += "\tDate = " + this.date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + "\n";
        stringRepresentation += "\tStadiumName = " + this.stadiumName + "\n";
        stringRepresentation += "\tCompetitionID = " + this.competitionID + "\n";
        stringRepresentation += "\tAttendance = " + this.attendance + "\n\n";

        return stringRepresentation;
    }
}