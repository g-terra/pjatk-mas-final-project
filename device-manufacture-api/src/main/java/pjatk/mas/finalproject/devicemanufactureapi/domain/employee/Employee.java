package pjatk.mas.finalproject.devicemanufactureapi.domain.employee;

import lombok.*;
import pjatk.mas.finalproject.devicemanufactureapi.domain.OrderItem.OrderItem;
import pjatk.mas.finalproject.devicemanufactureapi.domain.types.Paycheck;
import pjatk.mas.finalproject.devicemanufactureapi.domain.factory.Factory;
import pjatk.mas.finalproject.devicemanufactureapi.domain.team.Team;

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

    @OneToMany(mappedBy = "advisor")
    private List<OrderItem> advised;

    @ManyToOne
    @JoinColumn(name = "factory_id")
    private Factory worksAt;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team memberOf;

    @ElementCollection
    @CollectionTable(name = "paychecks")
    private List<Paycheck> paychecks;



}
