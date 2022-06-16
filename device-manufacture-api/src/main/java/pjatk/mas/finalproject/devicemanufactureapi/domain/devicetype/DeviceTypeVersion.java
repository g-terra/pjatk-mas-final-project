package pjatk.mas.finalproject.devicemanufactureapi.domain.devicetype;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import pjatk.mas.finalproject.devicemanufactureapi.domain.functionality.Functionality;
import pjatk.mas.finalproject.devicemanufactureapi.domain.types.PropertyValue;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "DeviceTypeVersion")
@Table(name = "device_type_versions")
public class DeviceTypeVersion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "device_type_version_id", nullable = false)
    private Long id;


    @ElementCollection
    @NotNull
    private List<PropertyValue> propertyValues = new ArrayList<>();


    @NotNull
    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime createDateTime;


    @ManyToOne(optional = false)
    @JoinColumn(name = "device_type_id", nullable = false)
    private DeviceType deviceType;

    @ManyToMany
    @JoinTable(name = "device_type_versions_functionalities",
            joinColumns = @JoinColumn(name = "device_type_version_id"),
            inverseJoinColumns = @JoinColumn(name = "functionality_id"))
    private Set<Functionality> functionalities = new LinkedHashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        DeviceTypeVersion that = (DeviceTypeVersion) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
