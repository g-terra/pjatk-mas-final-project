package pjatk.mas.finalproject.devicemanufactureapi.domain.model.user;

import lombok.*;
import pjatk.mas.finalproject.devicemanufactureapi.domain.model.client.Client;
import pjatk.mas.finalproject.devicemanufactureapi.domain.model.employee.Employee;
import pjatk.mas.finalproject.devicemanufactureapi.domain.model.role.Role;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "User")
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @NotBlank
    @NotEmpty
    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotBlank
    @NotEmpty
    @NotNull
    @Column(name = "surname", nullable = false)
    private String surname;

    @Email
    @Column(name = "email", nullable = false , unique = true)
    private String email;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Client client;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Employee employee;

    @Size(min = 1)
    @ManyToMany
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "role_id"))
    private Set<Role> roles = new java.util.LinkedHashSet<>();

}
