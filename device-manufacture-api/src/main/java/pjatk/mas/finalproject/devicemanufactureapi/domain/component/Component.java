package pjatk.mas.finalproject.devicemanufactureapi.domain.component;

import lombok.*;
import org.hibernate.Hibernate;
import pjatk.mas.finalproject.devicemanufactureapi.domain.Product.Product;
import pjatk.mas.finalproject.devicemanufactureapi.domain.types.Material;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "Component")
@Table(name = "components")
public class Component {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "component_id", nullable = false)
    private Long id;

    @Column(nullable = false)
    @NotNull
    private Integer catalogNumber;

    @NotBlank
    @NotEmpty
    @NotNull
    private String securityCertificate;

    @PositiveOrZero
    @Positive
    private BigDecimal basePrice;

    @NotNull
    @ElementCollection
    @Size(min = 1)
    private List<Material> materials = new java.util.ArrayList<>();

    @OneToMany(mappedBy = "mainComponent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ComponentBuildHistoryEntry> componentBuildHistoryEntriesAsMain = new java.util.ArrayList<>();

    @OneToMany(mappedBy = "subComponent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ComponentBuildHistoryEntry> componentBuildHistoryEntriesAsSub = new java.util.ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Component component = (Component) o;
        return id != null && Objects.equals(id, component.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
