package pjatk.mas.finalproject.devicemanufactureapi.domain.model.role;

import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "Role")
@Table(name = "roles")
@EntityListeners(RoleListener.class)
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", nullable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    public enum RoleName {
        DEFAULT,
        EMPLOYEE,
        CLIENT,
        OWNER
    }

}
