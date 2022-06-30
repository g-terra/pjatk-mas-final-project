package pjatk.mas.finalproject.devicemanufactureapi.domain.model.employee;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pjatk.mas.finalproject.devicemanufactureapi.domain.exceptions.NotFoundException;
import pjatk.mas.finalproject.devicemanufactureapi.domain.model.factory.Factory;
import pjatk.mas.finalproject.devicemanufactureapi.domain.model.factory.FactoryService;
import pjatk.mas.finalproject.devicemanufactureapi.domain.model.role.Role;
import pjatk.mas.finalproject.devicemanufactureapi.domain.model.team.Team;
import pjatk.mas.finalproject.devicemanufactureapi.domain.model.user.User;
import pjatk.mas.finalproject.devicemanufactureapi.domain.model.user.UserService;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final UserService userService;
    private final FactoryService factoryService;


    @Transactional
    public Employee create(EmployeeServiceRequests.CreateEmployeeDetails createEmployeeDetails) {

        User user = userService.getUser(createEmployeeDetails.getUserId());

        Factory factory = factoryService.getFactory(createEmployeeDetails.getFactoryId());

        Employee employeeToBeCreated = Employee.builder()
                .phone(createEmployeeDetails.getPhone())
                .user(user)
                .worksAt(factory)
                .build();

        Employee createdEmployee = employeeRepository.save(employeeToBeCreated);

        userService.setUserRole(user.getId(), Role.RoleName.EMPLOYEE.name());

        return createdEmployee;
    }


    public List<Employee> getEmployees(List<Long> employeeIds) {
        return employeeRepository.findAllById(employeeIds);
    }

    public void setEmployeeTeam(Long employeeId, Team team) {

        Employee employee = getEmployee(employeeId);

        employee.setTeam(team);

        employeeRepository.save(employee);
    }

    private Employee getEmployee(Long employeeId) {
        return employeeRepository.findById(employeeId).orElseThrow(() -> new NotFoundException(employeeId));
    }

    public void refreshEmployee(Employee employee) {
        employeeRepository.save(employee);
    }
}
