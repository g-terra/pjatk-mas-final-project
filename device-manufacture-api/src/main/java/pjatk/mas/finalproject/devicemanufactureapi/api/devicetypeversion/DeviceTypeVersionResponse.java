package pjatk.mas.finalproject.devicemanufactureapi.api.devicetypeversion;

import lombok.Builder;
import lombok.Getter;
import pjatk.mas.finalproject.devicemanufactureapi.domain.model.devicetypeversion.DeviceTypeVersion;
import pjatk.mas.finalproject.devicemanufactureapi.domain.model.functionality.Functionality;
import pjatk.mas.finalproject.devicemanufactureapi.domain.types.PropertyValue;

import java.time.format.DateTimeFormatter;
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

    private Long versionNumber;

    private List<PropertyValue> propertyValues;

    private String createDateTime;

    private Long deviceTypeId;

    private Map<Long, String> functionalities;

    /**
     * Converts DeviceTypeVersion(domain object) to DeviceTypeVersionResponse
     *
     * @param deviceTypeVersion DeviceTypeVersion object to be converted
     * @param dateTimeFormatter DateTimeFormatter object that converts LocalDateTime to readable String
     * @return DeviceTypeVersionResponse
     */
    public static DeviceTypeVersionResponse from(DeviceTypeVersion deviceTypeVersion, DateTimeFormatter dateTimeFormatter) {

        Map<Long, String> functionalities = deviceTypeVersion.getFunctionalities().stream().collect(
                Collectors.toMap(Functionality::getId, Functionality::getName));

        String formattedDate = deviceTypeVersion.getCreateDateTime().format(dateTimeFormatter);

        return DeviceTypeVersionResponse.builder()
                .id(deviceTypeVersion.getId())
                .versionNumber(deviceTypeVersion.getVersionNumber())
                .propertyValues(deviceTypeVersion.getPropertyValues())
                .functionalities(functionalities)
                .createDateTime(formattedDate)
                .deviceTypeId(deviceTypeVersion.getDeviceType().getId())
                .build();
    }

}
