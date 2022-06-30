package pjatk.mas.finalproject.devicemanufactureapi.domain.model.team;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class TeamServiceRequests {

    @Getter
    @Builder
    public static class CreateTeamDetails{

        @NotBlank
        @NotEmpty
        @NotNull
        private String name;

        @NotNull
        private List<Long> EmployeeIds;

        @NotNull
        private Long targetDeviceTypeId;

    }


}
