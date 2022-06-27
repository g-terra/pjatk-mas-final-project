package pjatk.mas.finalproject.devicemanufactureapi.domain.model.devicetype;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.*;


/**
 * Wrapper for DeviceTypeService requests
 */
public class DeviceTypeServiceRequests {

    /**
     * Class to provide information required to create a new DeviceType
     */
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
