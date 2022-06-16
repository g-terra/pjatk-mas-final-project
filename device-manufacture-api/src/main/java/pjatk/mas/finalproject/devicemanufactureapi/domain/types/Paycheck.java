package pjatk.mas.finalproject.devicemanufactureapi.domain.types;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class Paycheck {

    private BigDecimal value;

    private LocalDateTime paymentDATE;


}
