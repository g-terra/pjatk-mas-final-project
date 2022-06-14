package pjatk.mas.finalproject.devicemanufactureapi.domain.OrderItem;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pjatk.mas.finalproject.devicemanufactureapi.domain.Order.Order;
import pjatk.mas.finalproject.devicemanufactureapi.domain.employee.Employee;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Column(name = "color")
    private String color;

    @ManyToOne
    @JoinColumn(name = "employee_ID", nullable = false)
    private Employee advisor;

}
