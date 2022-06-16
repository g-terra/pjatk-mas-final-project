package pjatk.mas.finalproject.devicemanufactureapi.domain.component;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "ComponentBuildHistoryEntry")
@Table(name = "componenet_build_history")
public class ComponentBuildHistoryEntry {

    @EmbeddedId
    private ComponentBuildHistoryId id;


    @NotNull
    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @ManyToOne(optional = false)
    @JoinColumn(name = "main_component_id", nullable = false)
    @MapsId("mainComponentId")
    private Component mainComponent;

    @ManyToOne(optional = false)
    @JoinColumn(name = "sub_component_id", nullable = false)
    @MapsId("subComponentId")
    private Component subComponent;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComponentBuildHistoryEntry that = (ComponentBuildHistoryEntry) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
