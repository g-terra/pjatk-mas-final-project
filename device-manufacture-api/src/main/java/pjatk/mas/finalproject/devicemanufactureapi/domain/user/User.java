package pjatk.mas.finalproject.devicemanufactureapi.domain.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pjatk.mas.finalproject.devicemanufactureapi.domain.client.Client;
import pjatk.mas.finalproject.devicemanufactureapi.domain.employee.Employee;

import javax.persistence.*;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String email;
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<UserRole> roles;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "phone", column = @Column(name = "client_phone")),
    })
    private Client client;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "phone", column = @Column(name = "employee_phone")),
    })
    private Employee employee;

    public Optional<Employee> getEmployee(){
        return Optional.ofNullable(this.employee);
    }

    public Optional<Client> getClient(){
        return Optional.ofNullable(this.client);
    }

    public boolean isClient(){
        return this.roles.contains(UserRole.CLIENT);
    }

    public boolean isEmployee(){
        return this.roles.contains(UserRole.EMPLOYEE);
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
