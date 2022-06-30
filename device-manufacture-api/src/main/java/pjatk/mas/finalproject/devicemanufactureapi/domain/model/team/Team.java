package pjatk.mas.finalproject.devicemanufactureapi.domain.model.team;

import lombok.*;
import pjatk.mas.finalproject.devicemanufactureapi.domain.model.devicetype.DeviceType;
import pjatk.mas.finalproject.devicemanufactureapi.domain.model.employee.Employee;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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

    @NotEmpty
    @NotNull
    @NotBlank
    @Column(name = "team_name", nullable = false, unique = true)
    private String name;


    @Size(min = 3, max = 5)
    @OneToMany(mappedBy = "team", orphanRemoval = true , cascade = CascadeType.MERGE)
    private List<Employee> employees ;

    @ManyToOne(optional = false)
    @JoinColumn(name = "target_device_type_id", nullable = false)
    private DeviceType targetDeviceType;



}
