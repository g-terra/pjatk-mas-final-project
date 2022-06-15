package pjatk.mas.finalproject.devicemanufactureapi.domain.devicetype;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import pjatk.mas.finalproject.devicemanufactureapi.domain.functionality.Functionality;
import pjatk.mas.finalproject.devicemanufactureapi.domain.types.PropertyValue;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "device_type_version")
public class DeviceTypeVersion {

    @Id
    @Column(name = "device_type_version_id")
    private Long id;


    @ElementCollection
    private List<PropertyValue> propertyValues;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createDateTime;


    @ManyToMany
    @JoinTable(name = "device_type_version_functionalities",
            joinColumns = @JoinColumn(name = "device_type_version_id"),
            inverseJoinColumns = @JoinColumn(name = "functionality_id"))
    private List<Functionality> functionalities = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "device_type_id", nullable = false)
    private DeviceType deviceType;

}
