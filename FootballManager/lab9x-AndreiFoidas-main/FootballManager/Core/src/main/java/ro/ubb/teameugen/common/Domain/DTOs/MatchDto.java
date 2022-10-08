package ro.ubb.teameugen.common.Domain.DTOs;

import java.util.Objects;

public class MatchDto extends BaseDto{
    private int clubID1;
    private int clubID2;
    private String date;
    private String stadiumName;
    private int competitionID;
    private int attendance;

    public MatchDto(int id, int c1, int c2, String date, String stadium, int competition, int attendance) {
        super(id);
        this.clubID1 = c1;
        this.clubID2 = c2;
        this.date = date;
        this.stadiumName = stadium;
        this.competitionID = competition;
        this.attendance = attendance;
    }

    public MatchDto() {}

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

    public String getDate() {
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

    public void setDate(String date) {
        this.date = date;
    }

    public void setStadiumName(String stadiumName) {
        this.stadiumName = stadiumName;
    }

    @Override
    public boolean equals(Object newObject) {
        if (this == newObject) return true;
        if (newObject == null || getClass() != newObject.getClass()) return false;
        MatchDto matchDto = (MatchDto) newObject;
        return this.clubID1 == matchDto.clubID1 &&
                this.clubID2 == matchDto.clubID2 &&
                this.competitionID == matchDto.competitionID &&
                this.attendance == matchDto.attendance &&
                Objects.equals(this.date, matchDto.date) &&
                Objects.equals(this.stadiumName, matchDto.stadiumName) &&
                this.getId() == matchDto.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.clubID1, this.clubID2, this.date, this.date, this.stadiumName, this.competitionID, this.attendance);
    }

    @Override
    public String toString() {
        return "MatchDto{" +
                "clubID1=" + clubID1 +
                ", clubID2=" + clubID2 +
                ", date=" + date +
                ", stadiumName='" + stadiumName + '\'' +
                ", competitionID=" + competitionID +
                ", attendance=" + attendance +
                "} " + super.toString();
    }
}
