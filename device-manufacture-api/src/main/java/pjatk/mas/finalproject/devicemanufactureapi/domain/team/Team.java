package pjatk.mas.finalproject.devicemanufactureapi.domain.team;

import lombok.*;
import pjatk.mas.finalproject.devicemanufactureapi.domain.devicetype.DeviceType;
import pjatk.mas.finalproject.devicemanufactureapi.domain.employee.Employee;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "teams")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private Long id;

    @OneToMany(mappedBy = "memberOf")
    private List<Employee> members;

    @ManyToOne
    @JoinColumn(name = "device_type_id")
    private DeviceType targetDeviceType;
}
