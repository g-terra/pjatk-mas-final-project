package pjatk.mas.finalproject.devicemanufactureapi.api.funcitonality;

import lombok.Builder;
import lombok.Getter;
import pjatk.mas.finalproject.devicemanufactureapi.domain.model.functionality.Functionality;
import pjatk.mas.finalproject.devicemanufactureapi.domain.types.Property;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Wrapper class for API response containing a details regarding the required properties of a {@link Functionality} object.
 */
@Getter
@Builder
public class FunctionalitiesRequiredPropertiesResponse {

    private List<FunctionalityRequirementsGroup> requiredProperties;

    /**
     * Converts Functionality objects to FunctionalityRequiredPropertiesResponse objects.
     *
     * @param functionalities Functionality objects to be converted
     * @return {@link FunctionalitiesRequiredPropertiesResponse} list of FunctionalityRequiredPropertiesResponse objects
     */
    public static FunctionalitiesRequiredPropertiesResponse from(List<Functionality> functionalities) {
        List<FunctionalityRequirementsGroup> requiredProperties = functionalities.stream()
                .map(FunctionalityRequirementsGroup::from)
                .collect(Collectors.toList());


        return FunctionalitiesRequiredPropertiesResponse.builder()
                .requiredProperties(requiredProperties)
                .build();
    }


    @Getter
    @Builder
    private static class FunctionalityRequirementsGroup {
        private Long functionalityId;
        private String functionalityName;
        private List<Property> properties;

        public static FunctionalityRequirementsGroup from(Functionality functionality) {
            return FunctionalityRequirementsGroup.builder()
                    .functionalityId(functionality.getId())
                    .functionalityName(functionality.getName())
                    .properties(functionality.getProperties())
                    .build();
        }
    }
}
