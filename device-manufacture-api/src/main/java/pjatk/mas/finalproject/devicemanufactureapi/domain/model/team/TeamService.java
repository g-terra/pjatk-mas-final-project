package pjatk.mas.finalproject.devicemanufactureapi.domain.model.team;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pjatk.mas.finalproject.devicemanufactureapi.domain.model.devicetype.DeviceType;
import pjatk.mas.finalproject.devicemanufactureapi.domain.model.devicetype.DeviceTypeService;
import pjatk.mas.finalproject.devicemanufactureapi.domain.model.employee.Employee;
import pjatk.mas.finalproject.devicemanufactureapi.domain.model.employee.EmployeeService;

import java.util.List;

@Service
@AllArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;
    private final EmployeeService employeeService;
    private final DeviceTypeService deviceTypeService;

    public Team create(TeamServiceRequests.CreateTeamDetails createTeamDetails) {

        List<Employee> teamMembers = employeeService.getEmployees(createTeamDetails.getEmployeeIds());

        DeviceType targetDeviceType = deviceTypeService.getDeviceType(createTeamDetails.getTargetDeviceTypeId());

        Team team = Team.builder()
                .name(createTeamDetails.getName())
                .employees(teamMembers)
                .targetDeviceType(targetDeviceType)
                .build();

        Team createdTeam = teamRepository.save(team);

        teamMembers.forEach(employee -> employee.setTeam(createdTeam));

        teamMembers.forEach(employeeService::refreshEmployee);

        return createdTeam;
    }





}
