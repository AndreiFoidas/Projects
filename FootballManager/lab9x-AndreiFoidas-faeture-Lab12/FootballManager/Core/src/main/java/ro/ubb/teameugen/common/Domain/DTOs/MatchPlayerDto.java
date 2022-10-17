package ro.ubb.teameugen.common.Domain.DTOs;

import lombok.*;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MatchPlayerDto extends BaseDto {
    private MatchDto match;
    private PlayerDto player;
    private int goalsScored;
    private int minutesPlayed;

}
