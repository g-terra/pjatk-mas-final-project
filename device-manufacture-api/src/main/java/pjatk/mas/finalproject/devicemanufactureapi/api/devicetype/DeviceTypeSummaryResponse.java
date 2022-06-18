package pjatk.mas.finalproject.devicemanufactureapi.api.devicetype;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import pjatk.mas.finalproject.devicemanufactureapi.domain.devicetype.DeviceType;
import pjatk.mas.finalproject.devicemanufactureapi.domain.devicetype.devicetypeversion.DeviceTypeVersion;
import pjatk.mas.finalproject.devicemanufactureapi.domain.functionality.Functionality;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Builder
@AllArgsConstructor
@Data
public class DeviceTypeSummaryResponse {

    private final Long id;
    private final String name;
    private final int powerConsumption;
    private final String status;
    private final Map<Long, List<String>> versions;

    static List<DeviceTypeSummaryResponse> from(List<DeviceType> deviceTypes) {

        return deviceTypes.stream()
                .map(DeviceTypeSummaryResponse::mapToDeviceTypeSummaryResponse)
                .collect(Collectors.toList());
    }

    private static DeviceTypeSummaryResponse mapToDeviceTypeSummaryResponse(DeviceType deviceType) {
        return DeviceTypeSummaryResponse.builder()
                .id(deviceType.getId())
                .name(deviceType.getName())
                .powerConsumption(deviceType.getPowerConsumption())
                .status(deviceType.getDeviceTypeStatus().name())
                .versions(collectVersionsToVersionIdFunctionalityMap(deviceType))
                .build();
    }

    private static Map<Long, List<String>> collectVersionsToVersionIdFunctionalityMap(DeviceType deviceType) {
        return deviceType.getDeviceTypeVersions()
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, DeviceTypeSummaryResponse::collectFunctionalityToMap));
    }

    private static List<String> collectFunctionalityToMap(Map.Entry<Long, DeviceTypeVersion> entry) {
        return entry.getValue().getFunctionalities().stream().map(Functionality::getName).collect(Collectors.toList());
    }
}
