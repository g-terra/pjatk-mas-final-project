package pjatk.mas.finalproject.devicemanufactureapi.domain.devicetype.devicetypeversion;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import pjatk.mas.finalproject.devicemanufactureapi.domain.devicetype.DeviceType;
import pjatk.mas.finalproject.devicemanufactureapi.domain.functionality.Functionality;
import pjatk.mas.finalproject.devicemanufactureapi.domain.types.PropertyValue;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

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

    @NotNull
    @Column(name = "version_unique_id", nullable = false, unique = true)
    private String versionUniqueId;

    @ElementCollection
    @NotNull
    private List<PropertyValue> propertyValues;


    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime createDateTime;


    @ManyToOne(optional = false)
    @JoinColumn(name = "device_type_id", nullable = false)
    private DeviceType deviceType;


    @Enumerated(EnumType.STRING)
    private DeviceTypeVersionStatus deviceTypeVersionStatus;

    @ManyToMany
    @JoinTable(name = "device_type_versions_functionalities",
            joinColumns = @JoinColumn(name = "device_type_version_id"),
            inverseJoinColumns = @JoinColumn(name = "functionality_id"))
    private List<Functionality> functionalities;

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
