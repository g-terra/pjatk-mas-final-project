package pjatk.mas.finalproject.devicemanufactureapi.domain.types;

import lombok.*;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class PropertyValue {

    @NotEmpty(message = "Name cannot be empty")
    @NotNull(message = "Name cannot be null")
    private String Name;

    @NotEmpty(message = "Value cannot be empty")
    @NotNull(message = "value cannot be null")
    private String value;
}
