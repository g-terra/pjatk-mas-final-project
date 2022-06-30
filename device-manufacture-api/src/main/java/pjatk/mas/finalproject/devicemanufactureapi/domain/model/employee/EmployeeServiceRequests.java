package pjatk.mas.finalproject.devicemanufactureapi.domain.model.employee;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class EmployeeServiceRequests {

    @Getter
    @Builder
    public static class CreateEmployeeDetails{

        @NotBlank(message = "Please provide a phone number")
        @NotEmpty(message = "Please provide a phone number")
        @NotNull(message = "Please provide a phone number")
        private String phone;

        @ManyToOne(optional = false)
        private Long factoryId;

        @NotNull
        private Long userId;



    }


}
