package ro.ubb.teameugen.common.Domain.Entities;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class MatchPlayerPK implements Serializable {
    private Match match;
    private Player player;
}
