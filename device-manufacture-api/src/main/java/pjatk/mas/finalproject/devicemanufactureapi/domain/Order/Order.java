package pjatk.mas.finalproject.devicemanufactureapi.domain.Order;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import pjatk.mas.finalproject.devicemanufactureapi.domain.OrderItem.OrderItem;
import pjatk.mas.finalproject.devicemanufactureapi.domain.client.Client;
import pjatk.mas.finalproject.devicemanufactureapi.domain.types.Address;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "Order")
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "placement_date", nullable = false)
    @CreationTimestamp
    private LocalDateTime placemenDate;

    @NotNull
    @Embedded
    private Address shippingAddress;

    @Column(name = "shipping_date")
    private LocalDateTime shippingDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "order_status", nullable = false)
    private OrderStatus orderStatus;

    @Column(name = "deleted", nullable = false)
    private boolean deleted;

    @ManyToOne(optional = false)
    @JoinColumn(name = "owner_id", nullable = false)
    private Client owner;

    @Size(min = 1)
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "order_id")
    private List<OrderItem> orderItems = new java.util.ArrayList<>();



}
