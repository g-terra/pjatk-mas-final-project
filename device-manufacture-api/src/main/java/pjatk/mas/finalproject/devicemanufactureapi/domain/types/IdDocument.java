package pjatk.mas.finalproject.devicemanufactureapi.domain.types;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class IdDocument {

    private String type;

    private String number;
}
