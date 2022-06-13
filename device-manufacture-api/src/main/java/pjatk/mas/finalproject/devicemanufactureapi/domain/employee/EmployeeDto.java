package pjatk.mas.finalproject.devicemanufactureapi.domain.employee;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class EmployeeDto {
    private final String phone;
    private final LocalDateTime employmentDate;
}
