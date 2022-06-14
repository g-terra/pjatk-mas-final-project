package pjatk.mas.finalproject.devicemanufactureapi.domain.Order;

import lombok.*;
import pjatk.mas.finalproject.devicemanufactureapi.domain.OrderItem.OrderItem;
import pjatk.mas.finalproject.devicemanufactureapi.domain.client.Client;
import pjatk.mas.finalproject.devicemanufactureapi.domain.types.Address;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @Column(name = "placement_date")
    private LocalDateTime placemenDate;

    @Column(name = "shipping_date")
    private LocalDateTime shippingDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    private OrderStatus orderStatus;

    @Column(name = "deleted")
    private boolean deleted;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    private List<OrderItem> items;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client owner;

    private Address shippingAddress;
}
