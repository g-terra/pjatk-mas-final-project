package pjatk.mas.finalproject.devicemanufactureapi.api.devicetypeversion;

import lombok.Builder;
import lombok.Getter;
import pjatk.mas.finalproject.devicemanufactureapi.domain.model.devicetypeversion.DeviceTypeVersion;
import pjatk.mas.finalproject.devicemanufactureapi.domain.model.functionality.Functionality;
import pjatk.mas.finalproject.devicemanufactureapi.domain.types.PropertyValue;

import java.time.format.DateTimeFormatter;
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
    private String createDateTime;
    private List<String> functionalities;
    private String status;
    private Map<String, String> propertyValues;

    /**
     * @param deviceTypeVersion DeviceTypeVersion object to be converted
     * @param dateTimeFormatter DateTimeFormatter object that converts LocalDateTime to readable String
     * @return DeviceTypeVersionSummaryResponse
     */
    public static DeviceTypeVersionSummaryResponse from(DeviceTypeVersion deviceTypeVersion, DateTimeFormatter dateTimeFormatter) {

        List<String> functionalities = deviceTypeVersion.getFunctionalities().stream()
                .map(Functionality::getName)
                .collect(Collectors.toList());

        Map<String, String> propertyValues = getPropertyValues(deviceTypeVersion);

        return DeviceTypeVersionSummaryResponse.builder()
                .deviceTypeId(deviceTypeVersion.getDeviceType().getId())
                .versionId(deviceTypeVersion.getId())
                .versionNumber(deviceTypeVersion.getVersionNumber())
                .functionalities(functionalities)
                .createDateTime(deviceTypeVersion.getCreateDateTime().format(dateTimeFormatter))
                .status(deviceTypeVersion.getDeviceTypeVersionStatus().name())
                .propertyValues(propertyValues)
                .build();
    }

    private static Map<String, String> getPropertyValues(DeviceTypeVersion deviceTypeVersion) {
        return deviceTypeVersion.getPropertyValues().stream().collect(
                Collectors.toMap(propertyValue -> {

                    String functionalityName = deviceTypeVersion.getFunctionalities()
                            .stream()
                            .filter(f -> f.getId().equals(propertyValue.getParentFunctionalityId()))
                            .findFirst()
                            .map(Functionality::getName).orElseThrow(() -> new IllegalStateException("Functionality not found"));

                    return "(" + functionalityName + ") " + propertyValue.getName();

                }, PropertyValue::getValue));
    }

    /**
     * @param deviceTypeVersions Collection of DeviceTypeVersion objects to be converted
     * @return List<DeviceTypeVersionSummaryResponse> list of DeviceTypeVersionSummaryResponse objects
     */
    public static List<DeviceTypeVersionSummaryResponse> from(Collection<DeviceTypeVersion> deviceTypeVersions, DateTimeFormatter dateTimeFormatter) {
        return deviceTypeVersions.stream()
                .map(
                        deviceTypeVersion -> DeviceTypeVersionSummaryResponse.from(deviceTypeVersion, dateTimeFormatter)
                )
                .collect(Collectors.toList());
    }


}
