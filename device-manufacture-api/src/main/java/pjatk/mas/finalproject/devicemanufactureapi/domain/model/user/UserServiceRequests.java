package pjatk.mas.finalproject.devicemanufactureapi.domain.model.user;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UserServiceRequests {

    @Getter
    @Builder
    public static class CreateUserDetails {

        @NotBlank(message = "Name cannot be empty")
        @NotEmpty(message = "Name cannot be empty")
        @NotNull(message = "Name cannot be null")
        private String name;

        @NotBlank(message = "Surname cannot be empty")
        @NotEmpty(message = "Surname cannot be empty")
        @NotNull(message = "Surname cannot be null")
        private String surname;

        @Email(message = "Email must be valid")
        private String email;

    }
}
