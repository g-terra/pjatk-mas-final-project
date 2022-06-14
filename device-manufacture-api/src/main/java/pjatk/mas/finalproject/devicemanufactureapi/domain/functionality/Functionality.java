package pjatk.mas.finalproject.devicemanufactureapi.domain.functionality;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pjatk.mas.finalproject.devicemanufactureapi.domain.devicetype.DeviceTypeVersion;
import pjatk.mas.finalproject.devicemanufactureapi.domain.types.Property;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "functionalities")
public class Functionality {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "functionality_id")
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "functionalities")
    private List<DeviceTypeVersion> deviceTypeVersions;

    @ElementCollection
    @CollectionTable(name = "functionality_properties")
    private List<Property> properties;

}
