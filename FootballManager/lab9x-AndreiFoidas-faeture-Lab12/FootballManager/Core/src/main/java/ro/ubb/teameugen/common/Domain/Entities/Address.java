package ro.ubb.teameugen.common.Domain.Entities;

import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
@Builder
public class Address {
    private String street;
    private String city;
}

