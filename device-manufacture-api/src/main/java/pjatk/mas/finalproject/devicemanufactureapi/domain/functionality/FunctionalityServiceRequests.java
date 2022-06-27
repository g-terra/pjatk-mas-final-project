package pjatk.mas.finalproject.devicemanufactureapi.domain.functionality;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import pjatk.mas.finalproject.devicemanufactureapi.domain.types.Property;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class FunctionalityServiceRequests {

    @Builder
    @AllArgsConstructor
    @Data
    public static class FunctionalityCreateDetails {

        @NotEmpty(message = "Name cannot be empty")
        @NotNull(message = "Name cannot be null")
        private String name;

        @Size(min = 1, message = "At least one property must be specified")
        private List<Property> properties;
    }




}
