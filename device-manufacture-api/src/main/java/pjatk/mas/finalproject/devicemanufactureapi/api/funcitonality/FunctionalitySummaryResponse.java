package pjatk.mas.finalproject.devicemanufactureapi.api.funcitonality;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import pjatk.mas.finalproject.devicemanufactureapi.domain.functionality.Functionality;
import pjatk.mas.finalproject.devicemanufactureapi.domain.types.Property;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Builder
@AllArgsConstructor
@Data
public class FunctionalitySummaryResponse {

    private final Long functionalityId;
    private final String functionalityName;
    private Map<String, String> requiredProperties;

    static List<FunctionalitySummaryResponse> from(List<Functionality> functionalities) {

        return functionalities.stream()
                .map(FunctionalitySummaryResponse::mapToFunctionalitySummaryResponse)
                .collect(Collectors.toList());
    }


    private static FunctionalitySummaryResponse mapToFunctionalitySummaryResponse(Functionality functionality) {
        return FunctionalitySummaryResponse.builder()
                .functionalityId(functionality.getId())
                .functionalityName(functionality.getName())
                .requiredProperties(functionality.getProperties().stream()
                        .collect(Collectors.toMap(Property::getName, property -> property.getType().name())))
                .build();
    }
}
