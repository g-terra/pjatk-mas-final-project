package pjatk.mas.finalproject.devicemanufactureapi.domain.devicetype.devicetypeversion;

import lombok.Builder;
import lombok.Getter;
import pjatk.mas.finalproject.devicemanufactureapi.domain.types.PropertyValue;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class DeviceTypeVersionServiceRequest {
    @Getter
    @Builder
    public static class DeviceTypeVersionCreateDetails {

        @NotNull(message = "Device type name cannot be null")
        private Long deviceId;

        @Size(min=1 , message = "At least one property is required")
        private List<Long> functionalityIds;

        @Size(min=1 , message = "At least one property value is required")
        private List<PropertyValue> propertyValues ;


    }
}
