package ro.ubb.teameugen.common.Domain.DTOs;

import lombok.*;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CompetitionDto extends BaseDto {
    private String name;
    private int prizePool;
    private int firstYear;
}
