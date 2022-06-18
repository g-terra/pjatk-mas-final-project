package pjatk.mas.finalproject.devicemanufactureapi.api.funcitonality;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import pjatk.mas.finalproject.devicemanufactureapi.domain.functionality.Functionality;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@AllArgsConstructor
@Data
public class FunctionalitySummaryResponse {

    private final Long id;
    private final String name;

    static List<FunctionalitySummaryResponse> from(List<Functionality> functionalities) {

        return functionalities.stream()
                .map(FunctionalitySummaryResponse::mapToFunctionalitySummaryResponse)
                .collect(Collectors.toList());
    }


    private static FunctionalitySummaryResponse mapToFunctionalitySummaryResponse(Functionality functionality) {
        return FunctionalitySummaryResponse.builder()
                .id(functionality.getId())
                .name(functionality.getName())
                .build();
    }
}
