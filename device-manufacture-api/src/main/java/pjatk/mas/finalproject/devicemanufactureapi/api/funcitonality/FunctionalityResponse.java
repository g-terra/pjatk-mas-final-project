package pjatk.mas.finalproject.devicemanufactureapi.api.funcitonality;

import lombok.Builder;
import lombok.Getter;
import pjatk.mas.finalproject.devicemanufactureapi.domain.functionality.Functionality;
import pjatk.mas.finalproject.devicemanufactureapi.domain.types.Property;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Wrapper class for API response containing details regarding a {@link Functionality} object.
 */
@Getter
@Builder
public class FunctionalityResponse {

    private final Long id;
    private final String name;
    private final List<Property> properties;

    /**
     * Converts Functionality objects to FunctionalityResponse objects.
     * @param functionality Functionality object to be converted
     * @return FunctionalityResponse object
     */
    public static FunctionalityResponse from(Functionality functionality) {
        return FunctionalityResponse.builder()
                .id(functionality.getId())
                .name(functionality.getName())
                .properties(functionality.getProperties())
                .build();
    }

    /**
     * Converts Functionality objects to FunctionalityResponse objects.
     * @param functionalities Functionality objects to be converted
     * @return List<FunctionalityResponse> list of FunctionalityResponse objects
     */
    public static List<FunctionalityResponse> from(List<Functionality> functionalities) {
        return functionalities.stream()
                .map(FunctionalityResponse::from)
                .collect(Collectors.toList());
    }



}
