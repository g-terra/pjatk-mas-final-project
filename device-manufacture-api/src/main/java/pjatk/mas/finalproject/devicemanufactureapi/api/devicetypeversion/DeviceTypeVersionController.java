package pjatk.mas.finalproject.devicemanufactureapi.api.devicetypeversion;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pjatk.mas.finalproject.devicemanufactureapi.domain.devicetype.devicetypeversion.DeviceTypeVersion;
import pjatk.mas.finalproject.devicemanufactureapi.domain.devicetype.devicetypeversion.DeviceTypeVersionService;
import pjatk.mas.finalproject.devicemanufactureapi.domain.types.PropertyValue;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static pjatk.mas.finalproject.devicemanufactureapi.domain.devicetype.devicetypeversion.DeviceTypeVersionServiceRequest.DeviceTypeVersionCreateDetails;

@RestController
@RequestMapping("/device-type-version")
@RequiredArgsConstructor
@Validated
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class DeviceTypeVersionController {

    private final DeviceTypeVersionService deviceTypeVersionService;

    @PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public DeviceTypeVersionResponse createDeviceTypeVersion(@RequestBody @Valid CreateDeviceTypeVersionRequest createDeviceTypeVersionRequest) {

        DeviceTypeVersionCreateDetails deviceTypeVersionCreateDetails = DeviceTypeVersionCreateDetails.builder()
                .deviceId(createDeviceTypeVersionRequest.getDeviceId())
                .functionalityIds(createDeviceTypeVersionRequest.getFunctionalityIds())
                .propertyValues(createDeviceTypeVersionRequest.getPropertyValues())
                .build();


        DeviceTypeVersion deviceTypeVersion = deviceTypeVersionService.create(deviceTypeVersionCreateDetails);

        return DeviceTypeVersionResponse.from(deviceTypeVersion);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<DeviceTypeVersionSummaryResponse> list() {
        List<DeviceTypeVersion> allDevices = deviceTypeVersionService.getAllDeviceTypeVersions();
        return DeviceTypeVersionSummaryResponse.from(allDevices);
    }

    @GetMapping("/{id}")
    public DeviceTypeVersionResponse getDeviceTypeVersion(@PathVariable Long id) {
        DeviceTypeVersion deviceTypeVersion = deviceTypeVersionService.getDeviceTypeVersion(id);
        return DeviceTypeVersionResponse.from(deviceTypeVersion);
    }

    @Getter
    @Builder
    private static class CreateDeviceTypeVersionRequest {

        @NotNull
        private Long deviceId;

        @Size(min=1 , message = "At least one functionality is required")
        private List<Long> functionalityIds;

        @Size(min=1 , message = "At least one property value is required")
        private List<PropertyValue> propertyValues ;
    }
}
