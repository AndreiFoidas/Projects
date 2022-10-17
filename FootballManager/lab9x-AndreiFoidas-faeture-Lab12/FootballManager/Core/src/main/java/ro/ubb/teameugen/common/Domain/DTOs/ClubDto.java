package ro.ubb.teameugen.common.Domain.DTOs;

import lombok.*;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ClubDto extends BaseDto {
    private String name;
    private String country;
    private int budget;
    private String ownerName;
    private String managerName;
    private String stadiumName;

}
