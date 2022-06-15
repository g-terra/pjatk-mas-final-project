package pjatk.mas.finalproject.devicemanufactureapi.domain.client;

import lombok.*;
import pjatk.mas.finalproject.devicemanufactureapi.domain.Order.Order;
import pjatk.mas.finalproject.devicemanufactureapi.domain.types.IdDocument;
import pjatk.mas.finalproject.devicemanufactureapi.domain.user.User;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private Long id;

    private IdDocument idDocument;

    @Column(name = "phone" , nullable = false)
    private String phone;

    @Column(name = "regon")
    private String regon;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "recommended_by_client_id")
    private Client recommender;

    @OneToMany(mappedBy = "recommender", fetch = FetchType.LAZY)
    private List<Client> recommended;


    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    private List<Order> orders;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return id.equals(client.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
