package pjatk.mas.finalproject.devicemanufactureapi.api.funcitonality;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import pjatk.mas.finalproject.devicemanufactureapi.domain.functionality.Functionality;
import pjatk.mas.finalproject.devicemanufactureapi.domain.types.Property;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Wrapper class for API response containing A more detailed view of a {@link Functionality} object.
 */
@Builder
@AllArgsConstructor
@Data
public class FunctionalitySummaryResponse {

    private final Long id;
    private final String name;
    private Map<String, String> requiredProperties;

    /**
     * Converts Functionality objects to FunctionalitySummaryResponse objects.
     * @param functionalities List of Functionality objects to be converted
     * @return List of FunctionalitySummaryResponse objects
     */
    static List<FunctionalitySummaryResponse> from(List<Functionality> functionalities) {

        return functionalities.stream()
                .map(FunctionalitySummaryResponse::mapToFunctionalitySummaryResponse)
                .sorted(Comparator.comparing(FunctionalitySummaryResponse::getId))
                .collect(Collectors.toList());
    }


    private static FunctionalitySummaryResponse mapToFunctionalitySummaryResponse(Functionality functionality) {
        return FunctionalitySummaryResponse.builder()
                .id(functionality.getId())
                .name(functionality.getName())
                .requiredProperties(
                        functionality.getProperties().stream().collect(Collectors.toMap(Property::getName, p -> p.getType().name())))
                .build();
    }
}
