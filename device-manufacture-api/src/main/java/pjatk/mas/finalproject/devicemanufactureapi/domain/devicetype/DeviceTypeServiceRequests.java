package pjatk.mas.finalproject.devicemanufactureapi.domain.devicetype;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.*;

public class DeviceTypeServiceRequests {

    @Getter
    @Builder
    public static class DeviceTypeCreateDetails {

        @NotEmpty(message = "Name cannot be empty")
        @NotNull(message = "Name cannot be null")
        private String name;

        @Max(value = 2000, message = "Power consumption cannot be greater than 2000")
        @PositiveOrZero(message = "Power consumption cannot be negative")
        private Integer powerConsumption;
    }

}
