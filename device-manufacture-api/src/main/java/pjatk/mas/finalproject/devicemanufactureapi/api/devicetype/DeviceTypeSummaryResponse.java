package pjatk.mas.finalproject.devicemanufactureapi.api.devicetype;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;
import pjatk.mas.finalproject.devicemanufactureapi.api.devicetypeversion.DeviceTypeVersionSummaryResponse;
import pjatk.mas.finalproject.devicemanufactureapi.domain.model.devicetype.DeviceType;
import pjatk.mas.finalproject.devicemanufactureapi.domain.model.employee.Employee;
import pjatk.mas.finalproject.devicemanufactureapi.domain.model.team.Team;
import pjatk.mas.finalproject.devicemanufactureapi.domain.model.user.User;

import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Wrapper class for API response containing a more detailed view of a DeviceType object.
 */
@Builder
@AllArgsConstructor
@Data
public class DeviceTypeSummaryResponse {

    private final Long deviceTypeId;
    private final String deviceTypeName;
    private final int powerConsumption;
    private final String deviceTypeStatus;
    private final List<String> teams;
    private final List<String> employees;
    private final List<DeviceTypeVersionSummaryResponse> versions;

    /**
     * Converts list of DeviceType(domain object) to DeviceTypeSummaryResponse
     *
     * @param deviceTypes List of {@link DeviceType} objects to be converted to DeviceTypeSummaryResponse objects
     * @return List<DeviceTypeSummaryResponse> List of DeviceTypeSummaryResponse objects
     */
    public static List<DeviceTypeSummaryResponse> from(List<DeviceType> deviceTypes, DateTimeFormatter dateTimeFormatter) {

        return deviceTypes.stream()
                .map(deviceType -> mapToDeviceTypeSummaryResponse(deviceType, dateTimeFormatter))
                .collect(Collectors.toList());
    }

    /**
     * Converts Page of device types to page of device type summaries.
     * @param deviceTypes Page of device types to be converted to page of device type summaries.
     */
    public static Page<DeviceTypeSummaryResponse> from(Page<DeviceType> deviceTypes, DateTimeFormatter dateTimeFormatter) {
        return deviceTypes.map(deviceType -> mapToDeviceTypeSummaryResponse(deviceType, dateTimeFormatter));
    }


    private static DeviceTypeSummaryResponse mapToDeviceTypeSummaryResponse(DeviceType deviceType, DateTimeFormatter dateTimeFormatter) {
        return DeviceTypeSummaryResponse.builder()
                .deviceTypeId(deviceType.getId())
                .deviceTypeName(deviceType.getName())
                .powerConsumption(deviceType.getPowerConsumption())
                .deviceTypeStatus(deviceType.getDeviceTypeStatus().name())
                .versions(collectVersionsToVersionIdFunctionalityMap(deviceType, dateTimeFormatter))
                .teams(getTeamNames(deviceType))
                .employees(getEmployeeList(deviceType.getSellingTeams()))
                .build();
    }

    private static List<String> getTeamNames(DeviceType deviceType) {
        return deviceType.getSellingTeams().stream().map(Team::getName).collect(Collectors.toList());
    }

    private static List<DeviceTypeVersionSummaryResponse> collectVersionsToVersionIdFunctionalityMap(DeviceType deviceType, DateTimeFormatter dateTimeFormatter) {
        return DeviceTypeVersionSummaryResponse.from(deviceType.getDeviceTypeVersions().values(), dateTimeFormatter);
    }

    private static List<String> getEmployeeList(List<Team> teams){
        return teams.stream().map(DeviceTypeSummaryResponse::getEmployeeList).flatMap(Collection::stream).collect(Collectors.toList());
    }

    private static List<String> getEmployeeList(Team team){
        return team.getEmployees().stream().map(Employee::getUser).map(User::getFullName).collect(Collectors.toList());
    }
}
