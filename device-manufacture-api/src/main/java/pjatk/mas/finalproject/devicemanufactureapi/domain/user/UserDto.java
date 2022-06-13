package pjatk.mas.finalproject.devicemanufactureapi.domain.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDto {
    private final String name;
    private final String surname;
    private final String email;
}
