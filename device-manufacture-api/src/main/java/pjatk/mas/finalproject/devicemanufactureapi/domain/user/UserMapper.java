package pjatk.mas.finalproject.devicemanufactureapi.domain.user;

import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User mapFromDto(UserDto userDto) {
        return User.builder()
                .name(userDto.getName())
                .email(userDto.getEmail())
                .surname(userDto.getSurname()).build();
    }
}
