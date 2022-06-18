package pjatk.mas.finalproject.devicemanufactureapi.api.devicetype;

import lombok.Builder;
import lombok.Getter;
import pjatk.mas.finalproject.devicemanufactureapi.domain.devicetype.DeviceType;

import java.time.LocalDateTime;

@Getter
@Builder
public class DeviceTypeResponse {

    private final Long id;
    private final String name;
    private final int powerConsumption;
    private final LocalDateTime createdAt;

    static DeviceTypeResponse from(DeviceType deviceType) {
        return DeviceTypeResponse.builder()
                .id(deviceType.getId())
                .name(deviceType.getName())
                .powerConsumption(deviceType.getPowerConsumption())
                .createdAt(deviceType.getCreatedAt())
                .build();
    }




}
