package pjatk.mas.finalproject.devicemanufactureapi.api.devicetype;

import lombok.Builder;
import lombok.Getter;
import pjatk.mas.finalproject.devicemanufactureapi.domain.model.devicetype.DeviceType;

import java.time.LocalDateTime;

/**
 * Wrapper class for API response containing details of a DeviceType object.
 */
@Getter
@Builder
public class DeviceTypeResponse {

    private final Long id;
    private final String name;
    private final int powerConsumption;
    private final LocalDateTime createdAt;

    /**
     * Converts DeviceType(domain object) to DeviceTypeResponse
     * @param deviceType DeviceType object to be converted
     * @return DeviceTypeResponse
     */
    static DeviceTypeResponse from(DeviceType deviceType) {
        return DeviceTypeResponse.builder()
                .id(deviceType.getId())
                .name(deviceType.getName())
                .powerConsumption(deviceType.getPowerConsumption())
                .createdAt(deviceType.getCreatedAt())
                .build();
    }




}
