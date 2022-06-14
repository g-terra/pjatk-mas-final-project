package pjatk.mas.finalproject.devicemanufactureapi.domain.types;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Paycheck {

    @Column(name = "value")
    private BigDecimal value;

    @Column(name = "payment_date")
    private LocalDateTime paymentDATE;


}
