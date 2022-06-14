package pjatk.mas.finalproject.devicemanufactureapi.domain.client;

import lombok.*;
import pjatk.mas.finalproject.devicemanufactureapi.domain.Order.Order;
import pjatk.mas.finalproject.devicemanufactureapi.domain.types.IdDocument;

import javax.persistence.*;
import java.util.List;

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

    @Column(name = "phone")
    private String phone;

    @Column(name = "regon")
    private String regon;

    private IdDocument idDocument;

    @OneToMany(mappedBy = "recommender", fetch = FetchType.LAZY)
    private List<Client> recommended;

    @ManyToOne
    @JoinColumn(name = "recommended_by_client_id")
    private Client recommender;

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    private List<Order> orders;


}
