package pjatk.mas.finalproject.devicemanufactureapi.domain.component;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class ComponentBuildHistoryId implements Serializable {

    @Column(name = "main_component_id", nullable = false)
    @NotNull
    private Long mainComponentId;

    @Column(name = "sub_component_id", nullable = false)
    @NotNull
    private Long subComponentId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComponentBuildHistoryId that = (ComponentBuildHistoryId) o;
        return mainComponentId.equals(that.mainComponentId) && subComponentId.equals(that.subComponentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mainComponentId, subComponentId);
    }
}
