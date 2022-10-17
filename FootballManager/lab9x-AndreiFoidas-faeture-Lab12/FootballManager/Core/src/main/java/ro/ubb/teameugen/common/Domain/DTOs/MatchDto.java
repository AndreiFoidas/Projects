package ro.ubb.teameugen.common.Domain.DTOs;

import lombok.*;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MatchDto extends BaseDto{
    private ClubDto club1;
    private ClubDto club2;
    private String date;
    private String stadiumName;
    private CompetitionDto competition;
    private int attendance;
}
