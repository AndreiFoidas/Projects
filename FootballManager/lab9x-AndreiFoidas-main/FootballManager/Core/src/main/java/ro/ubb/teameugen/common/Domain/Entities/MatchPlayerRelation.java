package ro.ubb.teameugen.common.Domain.Entities;

import javax.persistence.Entity;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Entity
public class MatchPlayerRelation extends BaseEntity<Integer> {
    private int matchID;
    private int playerID;
    private int goalsScored;
    private int minutesPlayed;

    public MatchPlayerRelation(int matchID, int playerID, int goalsScored, int minutesPlayed) {

        this.matchID = matchID;
        this.playerID = playerID;
        this.goalsScored = goalsScored;
        this.minutesPlayed = minutesPlayed;
    }

    protected MatchPlayerRelation() {}

    public int getMatchID() {
        return matchID;
    }

    public int getPlayerID() {
        return playerID;
    }

    public int getGoalsScored() {
        return goalsScored;
    }

    public int getMinutesPlayed() {
        return minutesPlayed;
    }

    public void setMatchID(int matchID) {
        this.matchID = matchID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public void setGoalsScored(int goalsScored) {
        this.goalsScored = goalsScored;
    }

    public void setMinutesPlayed(int minutesPlayed) {
        this.minutesPlayed = minutesPlayed;
    }

    @Override
    public boolean equals(Object newObject) {
        if (this == newObject) return true;
        if (newObject == null || getClass() != newObject.getClass()) return false;

        MatchPlayerRelation matchPlayerRelation = (MatchPlayerRelation) newObject;

        return this.matchID == matchPlayerRelation.matchID &&
                this.playerID == matchPlayerRelation.playerID &&
                this.goalsScored == matchPlayerRelation.goalsScored &&
                this.minutesPlayed == matchPlayerRelation.minutesPlayed&&
                this.getId().equals(matchPlayerRelation.getId());
    }

    @Override
    public int hashCode() {
        int result = this.matchID % 600007;
        result = 31 * (result + this.playerID) % 600007;
        result = 31 * (result + this.goalsScored) % 600007;
        result = 31 * (result + this.minutesPlayed) % 600007;
        return result;
    }

    @Override
    public String toString() {
        String stringRepresentation = "";

        stringRepresentation += "MatchPlayerRelation:\n";
        stringRepresentation += "\tId = " + this.getId() + "\n";
        stringRepresentation += "\tMatchID = " + this.matchID + "\n";
        stringRepresentation += "\tPlayerID = " + this.playerID + "\n";
        stringRepresentation += "\tGoalsScored = " + this.goalsScored + "\n";
        stringRepresentation += "\tMinutesPlayed = " + this.minutesPlayed + "\n\n";

        return stringRepresentation;
    }
}
