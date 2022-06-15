package pjatk.mas.finalproject.devicemanufactureapi.domain.devicetype;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pjatk.mas.finalproject.devicemanufactureapi.domain.team.Team;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

    @OneToMany(mappedBy = "targetDeviceType")
    private List<Team> sellingTeams;


    @OneToMany(mappedBy = "deviceType", orphanRemoval = true)
    @MapKey(name = "id")
    private Map<Long,DeviceTypeVersion> deviceTypeVersions = new java.util.HashMap<>();

}
