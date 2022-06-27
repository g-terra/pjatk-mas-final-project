package pjatk.mas.finalproject.devicemanufactureapi.api.devicetypeversion;

import lombok.Builder;
import lombok.Getter;
import pjatk.mas.finalproject.devicemanufactureapi.domain.devicetype.devicetypeversion.DeviceTypeVersion;
import pjatk.mas.finalproject.devicemanufactureapi.domain.functionality.Functionality;
import pjatk.mas.finalproject.devicemanufactureapi.domain.types.PropertyValue;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Wrapper class for API response containing details of a DeviceTypeVersion object.
 */
@Getter
@Builder
public class DeviceTypeVersionResponse {

    private Long id;

    private String versionUniqueId;

    private List<PropertyValue> propertyValues;

    private LocalDateTime createDateTime;

    private Long deviceTypeId;

    private Map<Long, String> functionalities;


    /**
     * Converts DeviceTypeVersion(domain object) to DeviceTypeVersionResponse
     * @param deviceTypeVersion DeviceTypeVersion object to be converted
     * @return DeviceTypeVersionResponse
     */
    public static DeviceTypeVersionResponse from(DeviceTypeVersion deviceTypeVersion) {

        Map<Long, String> functionalities = deviceTypeVersion.getFunctionalities().stream().collect(
                Collectors.toMap(Functionality::getId, Functionality::getName));


        return DeviceTypeVersionResponse.builder()
                .id(deviceTypeVersion.getId())
                .propertyValues(deviceTypeVersion.getPropertyValues())
                .functionalities(functionalities)
                .createDateTime(deviceTypeVersion.getCreateDateTime())
                .deviceTypeId(deviceTypeVersion.getDeviceType().getId())
                .build();
    }
}
