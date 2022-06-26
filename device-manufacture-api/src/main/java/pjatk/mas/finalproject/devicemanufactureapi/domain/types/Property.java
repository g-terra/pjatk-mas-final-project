package pjatk.mas.finalproject.devicemanufactureapi.domain.types;

import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class Property {

    @NotEmpty(message = "Name cannot be empty")
    @NotNull(message = "Name cannot be null")
    private String name;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Type cannot be null")
    private PropertyType type;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Property property = (Property) o;
        return name.equals(property.name) && type == property.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type);
    }
}
