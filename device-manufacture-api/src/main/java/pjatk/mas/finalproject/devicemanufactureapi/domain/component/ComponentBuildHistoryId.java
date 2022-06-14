package pjatk.mas.finalproject.devicemanufactureapi.domain.component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ComponentBuildHistoryId implements Serializable {

    @Column(name="main_component_id")
    private Long mainComponentId;

    @Column(name="sub_component_id")
    private Long subComponentId;
}
