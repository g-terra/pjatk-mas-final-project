package pjatk.mas.finalproject.devicemanufactureapi.domain.functionality;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pjatk.mas.finalproject.devicemanufactureapi.domain.devicetype.DeviceTypeVersion;
import pjatk.mas.finalproject.devicemanufactureapi.domain.types.Property;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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


    @ElementCollection
    @CollectionTable(name = "functionality_properties")
    private List<Property> properties;


    @ManyToMany(mappedBy = "functionalities")
    private Set<DeviceTypeVersion> deviceTypeVersions = new java.util.LinkedHashSet<>();

}
