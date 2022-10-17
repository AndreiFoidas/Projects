package ro.ubb.teameugen.common.Domain.DTOs;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PlayerDto extends BaseDto {
    private String playerName;
    private String birthday;
    private double wage;
    private ClubDto club;
    private String mainPosition;

    private String street;
    private String city;


}
