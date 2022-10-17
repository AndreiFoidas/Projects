package ro.ubb.teameugen.common.Domain.Entities;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@NamedEntityGraphs({
        @NamedEntityGraph(name = "playerWithMatchPlayers",
            attributeNodes = @NamedAttributeNode(value = "matchPlayers")),

        @NamedEntityGraph(name = "playerWithMatchPlayersAndClubs",
                attributeNodes = {
                    @NamedAttributeNode(value = "matchPlayers"),
                    @NamedAttributeNode(value = "club"),
                })
})
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true, exclude = {"matchPlayers"})
@Builder
public class Player extends BaseEntity<Integer> {
    private String playerName;
    private LocalDate birthday;
    private double wage;
    private String mainPosition;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Club club;

    @Embedded
    private Address address;

    //@ManyToMany
    //@OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<MatchPlayer> matchPlayers = new HashSet<>();

    public Set<Match> getMatches(){
        matchPlayers = matchPlayers == null ? new HashSet<>() : matchPlayers;
        return Collections.unmodifiableSet(
                matchPlayers.stream()
                .map(MatchPlayer::getMatch)
                .collect(Collectors.toSet())
        );
    }

    public void addMatch(MatchPlayer matchPlayer){
        matchPlayers.add(matchPlayer);
    }

    public void deleteMatch(int matchId){
        MatchPlayer matchPlayer = this.matchPlayers.stream()
                .filter(m -> m.getMatch().getId().equals(matchId))
                .collect(Collectors.toList())
                .get(0);

        matchPlayers.remove(matchPlayer);
    }

    @Override
    public String toString() {
        String stringRepresentation = "";

        stringRepresentation += "Player:\n";
        stringRepresentation += "\tId = " + this.getId() + "\n";
        stringRepresentation += "\tName = " + this.playerName + "\n";
        stringRepresentation += "\tBirthday = " + this.birthday.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + "\n";
        stringRepresentation += "\tWage = " + this.wage + "\n";
        stringRepresentation += "\tClubID = " + this.club.getId() + "\n";
        stringRepresentation += "\tMainPosition = " + this.mainPosition + "\n";
        stringRepresentation += "\tAddress:" + "\n";
        stringRepresentation += "\tStreet:" + this.address.getStreet() + "\n";
        stringRepresentation += "\tCity:" + this.address.getCity() + "\n\n";


        return stringRepresentation;
    }
}
