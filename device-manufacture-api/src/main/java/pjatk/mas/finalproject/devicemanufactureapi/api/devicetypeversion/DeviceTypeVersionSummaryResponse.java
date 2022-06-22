package pjatk.mas.finalproject.devicemanufactureapi.api.devicetypeversion;

import lombok.Builder;
import lombok.Getter;
import pjatk.mas.finalproject.devicemanufactureapi.domain.devicetype.devicetypeversion.DeviceTypeVersion;
import pjatk.mas.finalproject.devicemanufactureapi.domain.functionality.Functionality;
import pjatk.mas.finalproject.devicemanufactureapi.domain.types.PropertyValue;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Builder
public class DeviceTypeVersionSummaryResponse {

    private Long deviceTypeId;
    private Long versionId;
    private String deviceName;
    private Integer powerConsumption;
    private List<String> functionalities;
    private String status;
    private Map<String, String> propertyValues;

    public static DeviceTypeVersionSummaryResponse from(DeviceTypeVersion deviceTypeVersion) {

        List<String> functionalities = deviceTypeVersion.getFunctionalities().stream()
                .map(Functionality::getName)
                .collect(Collectors.toList());

        Map<String, String> propertyValues = deviceTypeVersion.getPropertyValues().stream().collect(
                Collectors.toMap(PropertyValue::getName, PropertyValue::getValue));

        return DeviceTypeVersionSummaryResponse.builder()
                .deviceTypeId(deviceTypeVersion.getDeviceType().getId())
                .versionId(deviceTypeVersion.getId())
                .deviceName(deviceTypeVersion.getDeviceType().getName())
                .powerConsumption(deviceTypeVersion.getDeviceType().getPowerConsumption())
                .functionalities(functionalities)
                .status(deviceTypeVersion.getDeviceTypeVersionStatus().name())
                .propertyValues(propertyValues)
                .build();
    }

    public static List<DeviceTypeVersionSummaryResponse> from(List<DeviceTypeVersion> deviceTypeVersion) {
        return deviceTypeVersion.stream()
                .map(DeviceTypeVersionSummaryResponse::from)
                .collect(Collectors.toList());
    }


}
