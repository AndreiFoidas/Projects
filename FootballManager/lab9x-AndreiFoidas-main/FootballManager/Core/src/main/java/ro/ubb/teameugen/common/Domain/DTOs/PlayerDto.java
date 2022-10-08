package ro.ubb.teameugen.common.Domain.DTOs;

import java.time.LocalDate;

public class PlayerDto extends BaseDto {
    private String playerName;
    private String birthday;
    private double wage;
    private int clubID;
    private String mainPosition;

    public PlayerDto() {}

    public PlayerDto(int id, String playerName, String birthday, double wage, int clubID, String mainPosition) {
        super(id);

        this.playerName = playerName;
        this.birthday = birthday;
        this.wage = wage;
        this.clubID = clubID;
        this.mainPosition = mainPosition;
    }

    public String getPlayerName() {
        return this.playerName;
    }

    public String getBirthday() {
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

    public void setBirthday(String newBirthday) {
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

        PlayerDto player = (PlayerDto) o;

        return this.playerName.equals(player.playerName) &&
                this.birthday.equals(player.birthday) &&
                this.wage == player.wage &&
                this.clubID == player.clubID &&
                this.mainPosition.equals(player.mainPosition);
    }

}
