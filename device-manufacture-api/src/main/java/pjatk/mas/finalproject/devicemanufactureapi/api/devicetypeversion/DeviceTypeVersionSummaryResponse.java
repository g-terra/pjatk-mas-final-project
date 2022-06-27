package pjatk.mas.finalproject.devicemanufactureapi.api.devicetypeversion;

import lombok.Builder;
import lombok.Getter;
import pjatk.mas.finalproject.devicemanufactureapi.domain.devicetype.devicetypeversion.DeviceTypeVersion;
import pjatk.mas.finalproject.devicemanufactureapi.domain.functionality.Functionality;
import pjatk.mas.finalproject.devicemanufactureapi.domain.types.PropertyValue;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Wrapper class for API response containing a more details view of a DeviceTypeVersion object.
 */
@Getter
@Builder
public class DeviceTypeVersionSummaryResponse {

    private Long deviceTypeId;
    private Long versionId;
    private Long versionNumber;
    private LocalDateTime createDateTime;
    private List<String> functionalities;
    private String status;
    private Map<String, String> propertyValues;

    /**
     * @param deviceTypeVersion DeviceTypeVersion object to be converted
     * @return DeviceTypeVersionSummaryResponse
     */
    public static DeviceTypeVersionSummaryResponse from(DeviceTypeVersion deviceTypeVersion) {

        List<String> functionalities = deviceTypeVersion.getFunctionalities().stream()
                .map(Functionality::getName)
                .collect(Collectors.toList());

        Map<String, String> propertyValues = deviceTypeVersion.getPropertyValues().stream().collect(
                Collectors.toMap(PropertyValue::getName, PropertyValue::getValue));

        return DeviceTypeVersionSummaryResponse.builder()
                .deviceTypeId(deviceTypeVersion.getDeviceType().getId())
                .versionId(deviceTypeVersion.getId())
                .versionNumber(deviceTypeVersion.getVersionNumber())
                .functionalities(functionalities)
                .createDateTime(deviceTypeVersion.getCreateDateTime())
                .status(deviceTypeVersion.getDeviceTypeVersionStatus().name())
                .propertyValues(propertyValues)
                .build();
    }

    /**
     * @param deviceTypeVersions Collection of DeviceTypeVersion objects to be converted
     * @return List<DeviceTypeVersionSummaryResponse> list of DeviceTypeVersionSummaryResponse objects
     */
    public static List<DeviceTypeVersionSummaryResponse> from(Collection<DeviceTypeVersion> deviceTypeVersions) {
        return deviceTypeVersions.stream()
                .map(DeviceTypeVersionSummaryResponse::from)
                .collect(Collectors.toList());
    }


}
