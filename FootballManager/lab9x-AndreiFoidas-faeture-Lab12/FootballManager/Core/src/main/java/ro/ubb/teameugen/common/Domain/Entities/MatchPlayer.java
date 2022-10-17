package ro.ubb.teameugen.common.Domain.Entities;

import lombok.*;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "match_player")
@IdClass(MatchPlayerPK.class)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = {"match", "player"})
public class MatchPlayer implements Serializable {

    @Id
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "match_id")
    private Match match;

    @Id
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id")
    private Player player;

    @Column(name = "goalsScored")
    private int goalsScored;
    @Column(name = "minutesPlayed")
    private int minutesPlayed;

}
