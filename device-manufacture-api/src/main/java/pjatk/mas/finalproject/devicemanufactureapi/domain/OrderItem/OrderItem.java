package pjatk.mas.finalproject.devicemanufactureapi.domain.OrderItem;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pjatk.mas.finalproject.devicemanufactureapi.domain.Order.Order;
import pjatk.mas.finalproject.devicemanufactureapi.domain.Product.Product;
import pjatk.mas.finalproject.devicemanufactureapi.domain.employee.Employee;

import javax.persistence.*;
import java.util.List;

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

    @Column(name = "color")
    private String color;

    @ManyToOne
    @JoinColumn(name = "employee_ID", nullable = false)
    private Employee advisor;

    @OneToMany
    @JoinColumn(name = "order_item_id" , referencedColumnName="order_item_id")
    private List<Product> products;


}
