package pjatk.mas.finalproject.devicemanufactureapi.domain.model.factory;

import lombok.*;
import pjatk.mas.finalproject.devicemanufactureapi.domain.types.Address;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "Factory")
@Table(name = "factories")
@EntityListeners(FactoryListener.class)
public class Factory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotNull
    @Embedded
    private Address address;
}
