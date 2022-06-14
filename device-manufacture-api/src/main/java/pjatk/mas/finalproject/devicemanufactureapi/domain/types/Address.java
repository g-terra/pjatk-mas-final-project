package pjatk.mas.finalproject.devicemanufactureapi.domain.types;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "building_number", nullable = false)
    private Integer buildingNumber;

    @Column(name = "flat_number", nullable = false)
    private Integer flatNumber;

    @Column(name = "zip", nullable = false)
    private String zip;

    @Column(name = "city", nullable = false)
    private String City;

}
