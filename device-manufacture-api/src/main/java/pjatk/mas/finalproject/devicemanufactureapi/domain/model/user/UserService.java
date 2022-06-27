package pjatk.mas.finalproject.devicemanufactureapi.domain.model.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

//    private final UserRepository userRepository;
//    private final UserMapper userMapper;
//    private final ClientMapper clientMapper;
//    private final EmployeeMapper employeeMapper;
//    private final RoleService roleService;
//
//
//    @Transactional
//    public User createClientUser(UserDto userDto, ClientDto clientDto) {
//        User user = userMapper.mapFromDto(userDto);
//        Client client = clientMapper.mapFromDto(clientDto);
//        user.setClient(client);
//        user.setRoles(Set.of(roleService.getRole(RoleService.AvailableRole.DEFAULT), roleService.getRole(RoleService.AvailableRole.CLIENT)));
//        return userRepository.save(user);
//    }
//
//    @Transactional
//    public User createEmployeeUser(UserDto userDto, EmployeeDto employeeDto) {
//        User user = userMapper.mapFromDto(userDto);
//        Employee employee = employeeMapper.mapFromDto(employeeDto);
//        user.setEmployee(employee);
//        user.setRoles(Set.of(roleService.getRole(RoleService.AvailableRole.DEFAULT), roleService.getRole(RoleService.AvailableRole.EMPLOYEE)));
//        return userRepository.save(user);
//    }
//
//    @Transactional
//    public User setClient(Long userId, ClientDto clientDto) {
//        Role clientRole = roleService.getRole(RoleService.AvailableRole.CLIENT);
//        User user = userRepository.getUserById(userId);
//
//        if (user.getRoles().contains(clientRole)) throw new UserIsAlreadyClientException(userId);
//
//        if (user.getClient().isEmpty()) {
//            Client client = clientMapper.mapFromDto(clientDto);
//            user.setClient(client);
//            user.getRoles().add(clientRole);
//        }
//
//
//        return userRepository.save(user);
//    }
//
//    @Transactional
//    public User setEmployee(Long userId, EmployeeDto employeeDto) {
//        Role employeeRole = roleService.getRole(RoleService.AvailableRole.EMPLOYEE);
//        User user = userRepository.getUserById(userId);
//
//        if (user.getRoles().contains(employeeRole)) throw new UserIsAlreadyEmployeeException(userId);
//
//        if (user.getEmployee().isEmpty()) {
//            Employee employee = employeeMapper.mapFromDto(employeeDto);
//            user.setEmployee(employee);
//            user.getRoles().add(employeeRole);
//        }
//
//        return userRepository.save(user);
//    }
//
//    @Transactional
//    public void revoke(Long userId, RoleService.AvailableRole role) {
//        User user = userRepository.getUserById(userId);
//        Set<Role> roles = user.getRoles();
//        if (role == RoleService.AvailableRole.DEFAULT) return;
//        Role roleToRemove = roleService.getRole(role);
//
//        roles.remove(roleToRemove);
//        userRepository.save(user);
//    }
//
//    public User getUserById(Long id) {
//        return userRepository.getUserById(id);
//    }


}
