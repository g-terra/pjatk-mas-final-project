package pjatk.mas.finalproject.devicemanufactureapi.domain.Order;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
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
    @Column(name = "order_id" , nullable = false)
    private Long id;

    @CreationTimestamp
    @Column(name = "placement_date", nullable = false)
    private LocalDateTime placemenDate;

    private Address shippingAddress;

    @Column(name = "shipping_date")
    private LocalDateTime shippingDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status", nullable = false , columnDefinition = "varchar(255) default 'PLACED'")
    private OrderStatus orderStatus;

    @Column(name = "deleted")
    private boolean deleted;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client owner;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "Order_order_items")
    private List<OrderItem> orderItems;

}
