package pjatk.mas.finalproject.devicemanufactureapi.domain.functionality;

import lombok.*;
import pjatk.mas.finalproject.devicemanufactureapi.domain.devicetype.DeviceTypeVersion;
import pjatk.mas.finalproject.devicemanufactureapi.domain.types.Property;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "Functionality")
@Table(name = "functionalities")

public class Functionality {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "functionality_id", nullable = false)
    private Long id;

    @NotBlank
    @NotEmpty
    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @ElementCollection
    @CollectionTable(name = "functionality_properties")
    private List<Property> properties = new ArrayList<>();

    @ManyToMany(mappedBy = "functionalities")
    private Set<DeviceTypeVersion> deviceTypeVersions = new LinkedHashSet<>();

}
