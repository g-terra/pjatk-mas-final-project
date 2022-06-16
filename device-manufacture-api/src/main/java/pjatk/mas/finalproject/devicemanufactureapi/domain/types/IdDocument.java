package pjatk.mas.finalproject.devicemanufactureapi.domain.types;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Embeddable
public class IdDocument {

    @NotNull
    @NotEmpty
    @NotBlank
    @Column(name = "id_document_type")
    private String type;

    @NotEmpty
    @NotBlank
    @NotNull
    @Column(name = "id_document_number")
    private String number;

}
