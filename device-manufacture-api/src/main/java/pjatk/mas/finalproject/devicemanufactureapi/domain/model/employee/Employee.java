package pjatk.mas.finalproject.devicemanufactureapi.domain.model.employee;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import pjatk.mas.finalproject.devicemanufactureapi.domain.model.OrderItem.OrderItem;
import pjatk.mas.finalproject.devicemanufactureapi.domain.model.factory.Factory;
import pjatk.mas.finalproject.devicemanufactureapi.domain.types.Paycheck;
import pjatk.mas.finalproject.devicemanufactureapi.domain.model.team.Team;
import pjatk.mas.finalproject.devicemanufactureapi.domain.model.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "Employee")
@Table(name = "employees")
@EntityListeners(EmployeeListener.class)
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotEmpty
    @NotNull
    @Column(nullable = false)
    private String phone;

    @CreationTimestamp
    @Column(name = "employement_date")
    private LocalDateTime employmentDate;


    @ElementCollection
    @CollectionTable(name = "employee_paychecks")
    private List<Paycheck> paychecks;


    @OneToMany(mappedBy = "advisor", orphanRemoval = true)
    private List<OrderItem> advised;


    @ManyToOne(optional = false)
    @JoinColumn(name = "factory_id", nullable = false)
    private Factory worksAt;

    @NotNull
    @OneToOne(optional = false, orphanRemoval = true)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @ManyToOne
    @JoinColumn(name = "team_id" )
    private Team team;

}
