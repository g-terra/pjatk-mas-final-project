package pjatk.mas.finalproject.devicemanufactureapi.domain.component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "component_build_history")
public class ComponentBuildHistory {

    @EmbeddedId
    private ComponentBuildHistoryId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("mainComponentId")
    private Component mainComponent;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("subComponentId")
    private Component subComponent;


    @CreationTimestamp
    @Column(name = "added_date")
    private LocalDateTime createDateTime;
}
