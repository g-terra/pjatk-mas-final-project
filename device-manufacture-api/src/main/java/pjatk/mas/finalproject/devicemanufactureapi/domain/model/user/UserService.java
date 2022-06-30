package pjatk.mas.finalproject.devicemanufactureapi.domain.model.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pjatk.mas.finalproject.devicemanufactureapi.domain.model.role.Role;
import pjatk.mas.finalproject.devicemanufactureapi.domain.model.role.RoleService;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    public User save(UserServiceRequests.CreateUserDetails createUserDetails) {
        User user = User.builder()
                .name(createUserDetails.getName())
                .surname(createUserDetails.getSurname())
                .email(createUserDetails.getEmail())
                .build();

        Role defaultRole = roleService.findByName(Role.RoleName.DEFAULT.name());

        user.setRoles(Set.of(defaultRole));

        return userRepository.save(user);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }


}
