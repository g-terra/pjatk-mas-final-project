package pjatk.mas.finalproject.devicemanufactureapi.domain.types;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IdDocument {

    @Column(name = "document_type")
    private String type;

    @Column(name = "document_number")
    private String number;
}
