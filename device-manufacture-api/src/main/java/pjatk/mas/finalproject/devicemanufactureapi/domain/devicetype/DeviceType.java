package pjatk.mas.finalproject.devicemanufactureapi.domain.devicetype;

import lombok.*;
import pjatk.mas.finalproject.devicemanufactureapi.domain.team.Team;

import javax.persistence.*;
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

    @Column(name = "power_consumption")
    private int powerConsumption;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "device_type_status")
    private DeviceTypeStatus deviceTypeStatus;


    @OneToMany(mappedBy = "deviceType", cascade = CascadeType.ALL, orphanRemoval = true)
    @MapKey(name="id")
    private Map<Long,DeviceTypeVersion> deviceTypeVersions = new java.util.HashMap<>();


    @OneToMany(mappedBy = "targetDeviceType", orphanRemoval = true)
    private List<Team> sellingTeams = new java.util.ArrayList<>();

}
