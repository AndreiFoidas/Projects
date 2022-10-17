package ro.ubb.teameugen.common.Domain.Entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@NamedEntityGraphs({
        @NamedEntityGraph(name = "clubs")
})
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Builder
public class Club extends BaseEntity<Integer> {
    private String name;
    private String country;
    private int budget; // in M euro
    private String ownerName;
    private String managerName;
    private String stadiumName;

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