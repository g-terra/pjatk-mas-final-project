package pjatk.mas.finalproject.devicemanufactureapi.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pjatk.mas.finalproject.devicemanufactureapi.domain.client.Client;
import pjatk.mas.finalproject.devicemanufactureapi.domain.client.ClientDto;
import pjatk.mas.finalproject.devicemanufactureapi.domain.client.ClientMapper;
import pjatk.mas.finalproject.devicemanufactureapi.domain.employee.Employee;
import pjatk.mas.finalproject.devicemanufactureapi.domain.employee.EmployeeDto;
import pjatk.mas.finalproject.devicemanufactureapi.domain.employee.EmployeeMapper;
import pjatk.mas.finalproject.devicemanufactureapi.domain.user.exceptions.UserIsAlreadyClientException;
import pjatk.mas.finalproject.devicemanufactureapi.domain.user.exceptions.UserIsAlreadyEmployeeException;

import javax.transaction.Transactional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ClientMapper clientMapper;
    private final EmployeeMapper employeeMapper;

    @Transactional
    public User createClientUser(UserDto userDto, ClientDto clientDto) {
        User user = userMapper.mapFromDto(userDto);
        Client client = clientMapper.mapFromDto(clientDto);
        user.setClient(client);
        user.setRoles(Set.of(UserRole.DEFAULT, UserRole.CLIENT));
        return userRepository.save(user);
    }

    @Transactional
    public User createEmployeeUser(UserDto userDto, EmployeeDto employeeDto) {
        User user = userMapper.mapFromDto(userDto);
        Employee employee = employeeMapper.mapFromDto(employeeDto);
        user.setEmployee(employee);
        user.setRoles(Set.of(UserRole.DEFAULT, UserRole.EMPLOYEE));
        return userRepository.save(user);
    }

    @Transactional
    public User setClient(Long userId, ClientDto clientDto) {
        Client client = clientMapper.mapFromDto(clientDto);
        User user = userRepository.getUserById(userId);
        if (user.isClient()) throw new UserIsAlreadyClientException(userId);
        user.setClient(client);
        user.getRoles().add(UserRole.CLIENT);
        return userRepository.save(user);
    }

    @Transactional
    public User setEmployee(Long userId, EmployeeDto employeeDto) {
        Employee employee = employeeMapper.mapFromDto(employeeDto);
        User user = userRepository.getUserById(userId);
        if (user.isEmployee()) throw new UserIsAlreadyEmployeeException(userId);
        user.setEmployee(employee);
        user.getRoles().add(UserRole.EMPLOYEE);
        return userRepository.save(user);
    }

    @Transactional
    public void revoke(Long userId, UserRole userRole) {
        User user = userRepository.getUserById(userId);
        Set<UserRole> roles = user.getRoles();
        if (userRole == UserRole.DEFAULT) return;
        roles.remove(userRole);
        userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.getUserById(id);
    }


}
