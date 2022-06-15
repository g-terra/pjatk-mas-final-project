package pjatk.mas.finalproject.devicemanufactureapi.domain.component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pjatk.mas.finalproject.devicemanufactureapi.domain.types.Material;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "components")
public class Component {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "component_id")
    private Long id;

    @Column(name = "catalog_number")
    private int catalogNumber;

    @Column(name = "security_certificate")
    private String securityCertificate;

    @Column(name = "base_price")
    private BigDecimal basePrice;

    @ElementCollection
    @CollectionTable(name = "component_materials")
    private List<Material> materials;

    @OneToMany(
            mappedBy = "mainComponent"
    )
    private List<ComponentBuildHistoryEntry> mainHistory;

    @OneToMany(
            mappedBy = "subComponent"
    )
    private List<ComponentBuildHistoryEntry> subHistory;
}
