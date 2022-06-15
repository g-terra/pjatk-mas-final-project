package pjatk.mas.finalproject.devicemanufactureapi.domain.role;

import lombok.*;
import pjatk.mas.finalproject.devicemanufactureapi.domain.user.User;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id" , nullable = false)
    private Long id;

    @Column(name = "name" , nullable = false)
    private String name;

}
