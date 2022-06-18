package pjatk.mas.finalproject.devicemanufactureapi.domain.Product;

import lombok.*;
import pjatk.mas.finalproject.devicemanufactureapi.domain.component.Component;
import pjatk.mas.finalproject.devicemanufactureapi.domain.devicetype.devicetypeversion.DeviceTypeVersion;
import pjatk.mas.finalproject.devicemanufactureapi.domain.factory.Factory;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "Product")
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "product_status", nullable = false)
    private ProductStatus productStatus;

    @Column(name = "build_date")
    private LocalDateTime buildDate;

    @NotBlank
    @NotEmpty
    @NotNull
    @Column(name = "serial", nullable = false)
    private String serial;


    @ManyToOne(optional = false)
    @JoinColumn(name = "factory_id", nullable = false)
    private Factory factory;

    @ManyToOne(optional = false)
    @JoinColumn(name = "device_type_version_id", nullable = false)
    private DeviceTypeVersion deviceTypeVersion;

    @OneToMany(mappedBy = "product", orphanRemoval = true)
    @MapKey(name = "id")
    private Map<Long,Component> components = new HashMap<>();

}
