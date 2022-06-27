package pjatk.mas.finalproject.devicemanufactureapi.domain.model.OrderItem;

import lombok.*;
import pjatk.mas.finalproject.devicemanufactureapi.domain.model.Product.Product;
import pjatk.mas.finalproject.devicemanufactureapi.domain.model.employee.Employee;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "OrderItem")
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id", nullable = false)
    private Long id;

    @Pattern(message = "must be hex", regexp = "^#(?:[0-9a-fA-F]{3}){1,2}$ ")
    @NotNull
    private String color;

    @ManyToOne(optional = false)
    @JoinColumn(name = "advisor_id", nullable = false)
    private Employee advisor;


    @Size(min = 1)
    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "order_item_id", unique = true)
    private List<Product> products = new java.util.ArrayList<>();

}
