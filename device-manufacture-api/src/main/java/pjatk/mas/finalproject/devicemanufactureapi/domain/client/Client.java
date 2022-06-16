package pjatk.mas.finalproject.devicemanufactureapi.domain.client;

import lombok.*;
import pjatk.mas.finalproject.devicemanufactureapi.domain.Order.Order;
import pjatk.mas.finalproject.devicemanufactureapi.domain.types.IdDocument;
import pjatk.mas.finalproject.devicemanufactureapi.domain.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "Client")
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id", nullable = false)
    private Long id;

    @Embedded
    private IdDocument idDocument;

    @NotBlank
    @NotEmpty
    @NotNull
    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "regon")
    private String regon;


    @NotNull
    @OneToOne(optional = false, orphanRemoval = true)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @ManyToOne
    @JoinColumn(name = "recommender_id")
    private Client recommender;

    @OneToMany(mappedBy = "recommender", orphanRemoval = true)
    private List<Client> recommended = new java.util.ArrayList<>();


    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders = new java.util.ArrayList<>();


}
