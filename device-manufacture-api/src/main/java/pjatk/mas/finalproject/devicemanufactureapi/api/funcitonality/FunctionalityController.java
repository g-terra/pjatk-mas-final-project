package pjatk.mas.finalproject.devicemanufactureapi.api.funcitonality;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
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

    @GetMapping
    @PostMapping(produces = APPLICATION_JSON_VALUE , consumes = APPLICATION_JSON_VALUE)
    public List<FunctionalitySummaryResponse> getAllFunctionalities() {
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


    @Builder
    @Getter
    @AllArgsConstructor
    private static class CreateFunctionalityRequest {

        @NotEmpty(message = "Name cannot be empty")
        @NotNull(message = "Name cannot be null")
        private String name;

        @Size(min = 1, message = "At least one property must be specified")
        private List<@Valid Property> properties;
    }

}
