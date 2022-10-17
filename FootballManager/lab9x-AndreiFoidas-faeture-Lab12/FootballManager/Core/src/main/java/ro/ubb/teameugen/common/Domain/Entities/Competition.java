package ro.ubb.teameugen.common.Domain.Entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@NamedEntityGraphs({
        @NamedEntityGraph(name = "competitions")
})
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Builder
public class Competition extends BaseEntity<Integer> {
    private String name;
    private int prizePool;
    private int firstYear;

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
