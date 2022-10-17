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
        @NamedEntityGraph(name = "matchWithMatchPlayers",
                attributeNodes = @NamedAttributeNode(value = "matchPlayers")),

        @NamedEntityGraph(name = "matchWithMatchPlayersAndClubsAndCompetitions",
            attributeNodes = {
                @NamedAttributeNode(value = "matchPlayers"),
                @NamedAttributeNode(value = "club1"),
                @NamedAttributeNode(value = "club2"),
                @NamedAttributeNode(value = "competition")
            })
})
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true, exclude = {"matchPlayers"})
@Builder
public class Match extends BaseEntity<Integer> {
    private LocalDate date;
    private String stadiumName;
    private int attendance;

    @ManyToOne(fetch = FetchType.LAZY)
    private Club club1;
    @ManyToOne(fetch = FetchType.LAZY)
    private Club club2;
    @ManyToOne(fetch = FetchType.LAZY)
    private Competition competition;

    //@ManyToMany
    //@OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "match", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<MatchPlayer> matchPlayers = new HashSet<>();

    public Set<Player> getPlayers(){
        matchPlayers = matchPlayers == null ? new HashSet<>() : matchPlayers;
        return Collections.unmodifiableSet(
                matchPlayers.stream()
                .map(MatchPlayer::getPlayer)
                .collect(Collectors.toSet())
        );
    }

    public void addPlayer(MatchPlayer matchPlayer){
        matchPlayers.add(matchPlayer);
    }

    public void deletePlayer(int playerId){
        MatchPlayer matchPlayer = this.matchPlayers.stream()
                .filter(p -> p.getPlayer().getId().equals(playerId))
                .collect(Collectors.toList())
                .get(0);

        matchPlayers.remove(matchPlayer);
    }

    @Override
    public String toString() {
        String stringRepresentation = "";

        stringRepresentation += "Match:\n";
        stringRepresentation += "\tId = " + this.getId() + "\n";
        stringRepresentation += "\tClubID1 = " + this.club1.getId() + "\n";
        stringRepresentation += "\tClubID2 = " + this.club2.getId() + "\n";
        stringRepresentation += "\tDate = " + this.date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + "\n";
        stringRepresentation += "\tStadiumName = " + this.stadiumName + "\n";
        stringRepresentation += "\tCompetitionID = " + this.competition.getId() + "\n";
        stringRepresentation += "\tAttendance = " + this.attendance + "\n\n";

        return stringRepresentation;
    }
}