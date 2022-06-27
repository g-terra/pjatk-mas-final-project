package pjatk.mas.finalproject.devicemanufactureapi.api.funcitonality;

import lombok.Builder;
import lombok.Getter;
import pjatk.mas.finalproject.devicemanufactureapi.domain.model.functionality.Functionality;
import pjatk.mas.finalproject.devicemanufactureapi.domain.types.Property;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Wrapper class for API response containing a details regarding the required properties of a {@link Functionality} object.
 */
@Getter
@Builder
public class FunctionalitiesRequiredPropertiesResponse {

    private Set<Long> functionalityIds;
    private Set<Property> requiredProperties;

    /**
     * Converts Functionality objects to FunctionalityRequiredPropertiesResponse objects.
     * @param functionalities Functionality objects to be converted
     * @return List<FunctionalityRequiredPropertiesResponse> list of FunctionalityRequiredPropertiesResponse objects
     */
    public static FunctionalitiesRequiredPropertiesResponse from(List<Functionality> functionalities) {
        Set<Long> functionalityIds = functionalities.stream().map(Functionality::getId).collect(Collectors.toSet());

        Set<Property> requiredProperties = functionalities.stream()
                .map(Functionality::getProperties)
                .flatMap(List::stream)
                .collect(Collectors.toSet());

        return FunctionalitiesRequiredPropertiesResponse.builder()
                .functionalityIds(functionalityIds)
                .requiredProperties(requiredProperties)
                .build();


    }
}
