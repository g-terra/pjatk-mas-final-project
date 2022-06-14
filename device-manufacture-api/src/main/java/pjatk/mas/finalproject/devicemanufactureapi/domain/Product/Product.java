package pjatk.mas.finalproject.devicemanufactureapi.domain.Product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pjatk.mas.finalproject.devicemanufactureapi.domain.component.Component;
import pjatk.mas.finalproject.devicemanufactureapi.domain.devicetype.DeviceType;
import pjatk.mas.finalproject.devicemanufactureapi.domain.factory.Factory;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Map;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(name = "build_date")
    private LocalDateTime buildDate;

    @Column(name = "serial")
    private String serial;

    @OneToMany
    @JoinTable(name = "product_component_map",
            joinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "product_id")},
            inverseJoinColumns = {@JoinColumn(name = "component_id", referencedColumnName = "component_id")})
    @MapKey(name = "id")
    private Map<Long, Component> componentMap;

    @Enumerated(EnumType.STRING)
    @Column(name = "product_status")
    private ProductStatus productStatus;

    @ManyToOne
    @JoinColumn(name = "factory_id")
    private Factory factory;


    @Column(name = "device_type_version")
    private String deviceVersion;

    @ManyToOne
    @JoinColumn(name="device_type_id")
    private DeviceType deviceType;


}
