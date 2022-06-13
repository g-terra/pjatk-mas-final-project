package pjatk.mas.finalproject.devicemanufactureapi.domain.client;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Optional;

@Data
@AllArgsConstructor
public class ClientDto {

    private String documentType;
    private String documentNumber;
    private String phone;
    private String regon;

    private Long recommenderId;

    public Optional<Long> getRecommenderId(){
        return Optional.ofNullable(recommenderId);
    }
}
