package ro.ubb.teameugen.common.Domain.DTOs;

import java.util.Objects;

public class ClubDto extends BaseDto {

    private String name;
    private String country;
    private int budget;
    private String ownerName;
    private String managerName;
    private String stadiumName;

    public ClubDto() {}

    public ClubDto(int id, String name, String country, int budget, String ownerName, String managerName, String stadiumName) {
        super(id);

        this.name = name;
        this.country = country;
        this.budget = budget;
        this.ownerName = ownerName;
        this.managerName = managerName;
        this.stadiumName = stadiumName;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getStadiumName() {
        return stadiumName;
    }

    public void setStadiumName(String stadiumName) {
        this.stadiumName = stadiumName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClubDto clubDTO = (ClubDto) o;
        return budget == clubDTO.budget && Objects.equals(name, clubDTO.name) && Objects.equals(country, clubDTO.country) && Objects.equals(ownerName, clubDTO.ownerName) && Objects.equals(managerName, clubDTO.managerName) && Objects.equals(stadiumName, clubDTO.stadiumName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, country, budget, ownerName, managerName, stadiumName);
    }

    @Override
    public String toString() {
        return "ClubDto{" +
                "name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", budget=" + budget +
                ", ownerName='" + ownerName + '\'' +
                ", managerName='" + managerName + '\'' +
                ", stadiumName='" + stadiumName + '\'' +
                "} " + super.toString();
    }

}
