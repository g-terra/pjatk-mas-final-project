package pjatk.mas.finalproject.devicemanufactureapi.domain.employee;

import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {
    public Employee mapFromDto(EmployeeDto employeeDto) {
        return Employee.builder()
                .phone(employeeDto.getPhone())
                .employmentDate(employeeDto.getEmploymentDate())
                .build();
    }
}

