package pjatk.mas.finalproject.devicemanufactureapi.domain.devicetype;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import pjatk.mas.finalproject.devicemanufactureapi.domain.devicetype.devicetypeversion.DeviceTypeVersion;
import pjatk.mas.finalproject.devicemanufactureapi.domain.team.Team;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "DeviceType")
@Table(name = "device_types")
public class DeviceType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "device_type_id")
    private Long id;

    @NotNull
    @Max(2000)
    @Positive
    @Column(name = "power_consumption", nullable = false)
    private Integer powerConsumption;

    @NotNull
    @Column(name = "name")
    private String name;

    @CreationTimestamp
    @Column(name="crated_at")
    private LocalDateTime createdAt;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "device_type_status")
    private DeviceTypeStatus deviceTypeStatus;


    @OneToMany(mappedBy = "deviceType", cascade = CascadeType.ALL, orphanRemoval = true )
    @MapKey(name="id")
    private Map<Long, DeviceTypeVersion> deviceTypeVersions = new HashMap<>();


    @OneToMany(mappedBy = "targetDeviceType", orphanRemoval = true)
    private List<Team> sellingTeams = new java.util.ArrayList<>();

}
