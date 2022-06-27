package pjatk.mas.finalproject.devicemanufactureapi.domain.model.team;

import lombok.*;
import pjatk.mas.finalproject.devicemanufactureapi.domain.model.devicetype.DeviceType;
import pjatk.mas.finalproject.devicemanufactureapi.domain.model.employee.Employee;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "Team")
@Table(name = "teams")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @Size(min = 3, max = 5)
    @OneToMany(mappedBy = "team", orphanRemoval = true)
    private List<Employee> employees = new java.util.ArrayList<>();

    @ManyToOne(optional = false)
    @JoinColumn(name = "target_device_type_id", nullable = false)
    private DeviceType targetDeviceType;

}
