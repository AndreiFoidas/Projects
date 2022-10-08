package ro.ubb.teameugen.common.Domain.DTOs;

import java.util.Objects;

public class MatchPlayerRelationDto extends BaseDto {
    private int matchID;
    private int playerID;
    private int goalsScored;
    private int minutesPlayed;

    public MatchPlayerRelationDto(int id, int matchID, int playerID, int goalsScored, int minutesPlayed) {
        super(id);

        this.matchID = matchID;
        this.playerID = playerID;
        this.goalsScored = goalsScored;
        this.minutesPlayed = minutesPlayed;
    }

    public MatchPlayerRelationDto() {}

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

        MatchPlayerRelationDto matchPlayerRelationDto = (MatchPlayerRelationDto) newObject;

        return this.matchID == matchPlayerRelationDto.matchID &&
                this.playerID == matchPlayerRelationDto.playerID &&
                this.goalsScored == matchPlayerRelationDto.goalsScored &&
                this.minutesPlayed == matchPlayerRelationDto.minutesPlayed;
    }

    @Override
    public int hashCode() {
        return Objects.hash(matchID, playerID, goalsScored, minutesPlayed);
    }

    /*@Override
    public String toString() {
        String stringRepresentation = "";

        stringRepresentation += "MatchPlayerRelationDto:\n";
        stringRepresentation += "\tId = " + this.getId() + "\n";
        stringRepresentation += "\tMatchID = " + this.matchID + "\n";
        stringRepresentation += "\tPlayerID = " + this.playerID + "\n";
        stringRepresentation += "\tGoalsScored = " + this.goalsScored + "\n";
        stringRepresentation += "\tMinutesPlayed = " + this.minutesPlayed + "\n\n";

        return stringRepresentation;
    }*/
    @Override
    public String toString() {
        return "MatchPlayerRelationDto{" +
                "matchID='" + matchID + '\'' +
                ", playerID='" + playerID + '\'' +
                ", goalsScored=" + goalsScored +
                ", minutesPlayed='" + minutesPlayed + '\'' +
                "} " + super.toString();
    }
}
