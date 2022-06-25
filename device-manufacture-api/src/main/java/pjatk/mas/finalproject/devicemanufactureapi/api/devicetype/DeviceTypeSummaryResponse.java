package pjatk.mas.finalproject.devicemanufactureapi.api.devicetype;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import pjatk.mas.finalproject.devicemanufactureapi.api.devicetypeversion.DeviceTypeVersionSummaryResponse;
import pjatk.mas.finalproject.devicemanufactureapi.domain.devicetype.DeviceType;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@AllArgsConstructor
@Data
public class DeviceTypeSummaryResponse {

    private final Long deviceTypeId;
    private final String deviceTypeName;
    private final int powerConsumption;
    private final String deviceTypeStatus;
    private final List<DeviceTypeVersionSummaryResponse> versions;

    static List<DeviceTypeSummaryResponse> from(List<DeviceType> deviceTypes) {

        return deviceTypes.stream()
                .map(DeviceTypeSummaryResponse::mapToDeviceTypeSummaryResponse)
                .collect(Collectors.toList());
    }

    private static DeviceTypeSummaryResponse mapToDeviceTypeSummaryResponse(DeviceType deviceType) {
        return DeviceTypeSummaryResponse.builder()
                .deviceTypeId(deviceType.getId())
                .deviceTypeName(deviceType.getName())
                .powerConsumption(deviceType.getPowerConsumption())
                .deviceTypeStatus(deviceType.getDeviceTypeStatus().name())
                .versions(collectVersionsToVersionIdFunctionalityMap(deviceType))
                .build();
    }

    private static List<DeviceTypeVersionSummaryResponse> collectVersionsToVersionIdFunctionalityMap(DeviceType deviceType) {
        return DeviceTypeVersionSummaryResponse.from(deviceType.getDeviceTypeVersions().values());
    }

}
