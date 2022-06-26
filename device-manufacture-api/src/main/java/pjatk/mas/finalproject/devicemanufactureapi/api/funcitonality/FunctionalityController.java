package pjatk.mas.finalproject.devicemanufactureapi.api.funcitonality;

import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pjatk.mas.finalproject.devicemanufactureapi.domain.functionality.Functionality;
import pjatk.mas.finalproject.devicemanufactureapi.domain.functionality.FunctionalityService;
import pjatk.mas.finalproject.devicemanufactureapi.domain.types.Property;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static pjatk.mas.finalproject.devicemanufactureapi.domain.functionality.FunctionalityServiceRequests.FunctionalityCreateDetails;

@RestController
@RequestMapping("/functionality")
@RequiredArgsConstructor
@Validated
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class FunctionalityController {
    private final FunctionalityService functionalityService;

    @PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public FunctionalityResponse createFunctionality(@RequestBody @Valid CreateFunctionalityRequest createFunctionalityRequest) {

        FunctionalityCreateDetails functionalityCreateDetails = buildFunctionalityCreateDetails(createFunctionalityRequest);

        Functionality functionality = functionalityService.create(functionalityCreateDetails);

        return FunctionalityResponse.from(functionality);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<FunctionalitySummaryResponse> getAll() {
        List<Functionality> functionalities = functionalityService.getAllFunctionalities();
        return FunctionalitySummaryResponse.from(functionalities);
    }

    @GetMapping("/{id}")
    public FunctionalityResponse getFunctionality(@PathVariable Long id) {
        Functionality functionality = functionalityService.getFunctionality(id);
        return FunctionalityResponse.from(functionality);
    }

    private FunctionalityCreateDetails buildFunctionalityCreateDetails(CreateFunctionalityRequest createFunctionalityRequest) {
        return FunctionalityCreateDetails.builder()
                .name(createFunctionalityRequest.getName())
                .properties(createFunctionalityRequest.getProperties())
                .build();
    }

    @PostMapping("/required-properties")
    public FunctionalitiesRequiredPropertiesResponse getAllRequiredPropertiesFromFunctionalities(@RequestBody @Valid GetFunctionalitiesRequiredPropertiesRequest getFunctionalitiesRequiredPropertiesRequest) {
        List<Functionality> functionalities = functionalityService.getFunctionalities(getFunctionalitiesRequiredPropertiesRequest.getFunctionalityIds());
        return FunctionalitiesRequiredPropertiesResponse.from(functionalities);
    }


    @Builder
    @Getter
    @AllArgsConstructor
    private static class CreateFunctionalityRequest {

        @NotEmpty(message = "name must be provided")
        @NotNull(message = "name must be provided")
        private String name;

        @Size(min = 1, message = "At least one property must be specified")
        private List<@Valid Property> properties;
    }
    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    private static class GetFunctionalitiesRequiredPropertiesRequest {

        @Size(min = 1, message = "At least one functionality must be specified")
        private List<Long> functionalityIds;

    }
}
