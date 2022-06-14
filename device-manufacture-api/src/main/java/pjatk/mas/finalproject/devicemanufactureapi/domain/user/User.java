package pjatk.mas.finalproject.devicemanufactureapi.domain.user;

import lombok.*;
import pjatk.mas.finalproject.devicemanufactureapi.domain.Order.Order;
import pjatk.mas.finalproject.devicemanufactureapi.domain.client.Client;
import pjatk.mas.finalproject.devicemanufactureapi.domain.employee.Employee;
import pjatk.mas.finalproject.devicemanufactureapi.domain.role.Role;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "name" , nullable = false)
    private String name;

    @Column(name = "surname" , nullable = false)
    private String surname;

    @Column(name = "email" , nullable = false)
    private String email;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "role_id") }
    )
    private Set<Role> roles;

    @OneToOne(cascade = CascadeType.ALL)
    private Client client;

    @OneToOne(cascade = CascadeType.ALL)
    private Employee employee;

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    private List<Order> orders;

    public Optional<Employee> getEmployee(){
        return Optional.ofNullable(this.employee);
    }

    public Optional<Client> getClient(){
        return Optional.ofNullable(this.client);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
