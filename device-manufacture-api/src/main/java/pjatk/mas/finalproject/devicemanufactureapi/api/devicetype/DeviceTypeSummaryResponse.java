package pjatk.mas.finalproject.devicemanufactureapi.api.devicetype;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import pjatk.mas.finalproject.devicemanufactureapi.api.devicetypeversion.DeviceTypeVersionSummaryResponse;
import pjatk.mas.finalproject.devicemanufactureapi.domain.model.devicetype.DeviceType;

import java.time.format.DateTimeFormatter;
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
    private final List<DeviceTypeVersionSummaryResponse> versions;

    /**
     * Converts list of DeviceType(domain object) to DeviceTypeSummaryResponse
     *
     * @param deviceTypes List of DeviceType objects to be converted to DeviceTypeSummaryResponse objects
     * @return List<DeviceTypeSummaryResponse> List of DeviceTypeSummaryResponse objects
     */
    public static List<DeviceTypeSummaryResponse> from(List<DeviceType> deviceTypes, DateTimeFormatter dateTimeFormatter) {

        return deviceTypes.stream()
                .map(deviceType -> mapToDeviceTypeSummaryResponse(deviceType, dateTimeFormatter))
                .collect(Collectors.toList());
    }


    private static DeviceTypeSummaryResponse mapToDeviceTypeSummaryResponse(DeviceType deviceType, DateTimeFormatter dateTimeFormatter) {
        return DeviceTypeSummaryResponse.builder()
                .deviceTypeId(deviceType.getId())
                .deviceTypeName(deviceType.getName())
                .powerConsumption(deviceType.getPowerConsumption())
                .deviceTypeStatus(deviceType.getDeviceTypeStatus().name())
                .versions(collectVersionsToVersionIdFunctionalityMap(deviceType, dateTimeFormatter))
                .build();
    }

    private static List<DeviceTypeVersionSummaryResponse> collectVersionsToVersionIdFunctionalityMap(DeviceType deviceType, DateTimeFormatter dateTimeFormatter) {
        return DeviceTypeVersionSummaryResponse.from(deviceType.getDeviceTypeVersions().values(), dateTimeFormatter);
    }

}
