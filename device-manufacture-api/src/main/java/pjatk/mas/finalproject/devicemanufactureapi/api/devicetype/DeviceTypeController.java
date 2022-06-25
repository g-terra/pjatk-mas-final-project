package pjatk.mas.finalproject.devicemanufactureapi.api.devicetype;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pjatk.mas.finalproject.devicemanufactureapi.domain.devicetype.DeviceType;
import pjatk.mas.finalproject.devicemanufactureapi.domain.devicetype.DeviceTypeService;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static pjatk.mas.finalproject.devicemanufactureapi.domain.devicetype.DeviceTypeServiceRequests.DeviceTypeCreateDetails;

@RestController
@RequestMapping("/device-type")
@RequiredArgsConstructor
@Validated
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class DeviceTypeController {

    private final DeviceTypeService deviceTypeService;

    @PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public DeviceTypeResponse createDeviceType(@RequestBody @Valid CreateDeviceTypeRequest createDeviceTypeRequest) {

        DeviceTypeCreateDetails createDetails = DeviceTypeCreateDetails.builder()
                .name(createDeviceTypeRequest.getName())
                .powerConsumption(createDeviceTypeRequest.getPowerConsumption())
                .build();

        DeviceType deviceType = deviceTypeService.create(createDetails);

        return DeviceTypeResponse.from(deviceType);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<DeviceTypeSummaryResponse> list() {
        List<DeviceType> allDevices = deviceTypeService.getAllDevices();
        return DeviceTypeSummaryResponse.from(allDevices);
    }

    @GetMapping("/{id}")
    public DeviceTypeResponse getDeviceType(@PathVariable Long id) {
        DeviceType deviceType = deviceTypeService.getDeviceType(id);
        return DeviceTypeResponse.from(deviceType);
    }


    @Getter
    @Builder
    private static class CreateDeviceTypeRequest {

        @NotNull
        @Max(value = 2000, message = "power consumption must be less than 2000")
        @Positive
        private Integer powerConsumption;

        @NotNull
        @NotBlank
        private String name;
    }
}
