package pjatk.mas.finalproject.devicemanufactureapi.domain.employee;

import lombok.*;
import pjatk.mas.finalproject.devicemanufactureapi.domain.OrderItem.OrderItem;
import pjatk.mas.finalproject.devicemanufactureapi.domain.types.Paycheck;
import pjatk.mas.finalproject.devicemanufactureapi.domain.factory.Factory;
import pjatk.mas.finalproject.devicemanufactureapi.domain.team.Team;
import pjatk.mas.finalproject.devicemanufactureapi.domain.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long id;

    @Column(name = "phone")
    private String phone;

    @Column(name = "employment_date")
    private LocalDateTime employmentDate;

    @ElementCollection
    @CollectionTable(name = "employee_paychecks")
    private List<Paycheck> paychecks;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @OneToMany(mappedBy = "advisor")
    private List<OrderItem> advised;

    @ManyToOne
    @JoinColumn(name = "factory_id")
    private Factory worksAt;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team memberOf;






}
