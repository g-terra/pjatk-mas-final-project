package pjatk.mas.finalproject.devicemanufactureapi.domain.employee;

import lombok.*;
import org.hibernate.annotations.Parent;
import pjatk.mas.finalproject.devicemanufactureapi.domain.client.Client;
import pjatk.mas.finalproject.devicemanufactureapi.domain.user.User;

import java.time.LocalDateTime;
import java.util.Objects;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    private String phone;
    private LocalDateTime employmentDate;

    @Parent
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return user.equals(employee.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user);
    }
}
