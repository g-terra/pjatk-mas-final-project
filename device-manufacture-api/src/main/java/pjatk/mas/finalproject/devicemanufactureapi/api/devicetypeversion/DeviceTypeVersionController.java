package pjatk.mas.finalproject.devicemanufactureapi.api.devicetypeversion;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pjatk.mas.finalproject.devicemanufactureapi.domain.model.devicetypeversion.DeviceTypeVersion;
import pjatk.mas.finalproject.devicemanufactureapi.domain.model.devicetypeversion.DeviceTypeVersionService;
import pjatk.mas.finalproject.devicemanufactureapi.domain.types.PropertyValue;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static pjatk.mas.finalproject.devicemanufactureapi.domain.model.devicetypeversion.DeviceTypeVersionServiceRequest.DeviceTypeVersionCreateDetails;

@RestController
@RequestMapping("/device-type-version")
@RequiredArgsConstructor
@Validated
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class DeviceTypeVersionController {

    private final DateTimeFormatter dateTimeFormatter;
    private final DeviceTypeVersionService deviceTypeVersionService;

    /**
     * Post endpoint for creating a new device type version.
     *
     * @param createDeviceTypeVersionRequest - client request with device type version details that will be used to create new device type version
     * @return DeviceTypeVersionResponse - response with created device type version details
     */
    @PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public DeviceTypeVersionResponse createDeviceTypeVersion(@RequestBody @Valid CreateDeviceTypeVersionRequest createDeviceTypeVersionRequest) {

        DeviceTypeVersionCreateDetails deviceTypeVersionCreateDetails = DeviceTypeVersionCreateDetails.builder()
                .deviceId(createDeviceTypeVersionRequest.getDeviceId())
                .functionalityIds(createDeviceTypeVersionRequest.getFunctionalityIds())
                .propertyValues(createDeviceTypeVersionRequest.getPropertyValues())
                .build();


        DeviceTypeVersion deviceTypeVersion = deviceTypeVersionService.create(deviceTypeVersionCreateDetails);

        return DeviceTypeVersionResponse.from(deviceTypeVersion, dateTimeFormatter);
    }


    /**
     * Get endpoint for listing all device type versions.
     *
     * @return List<DeviceTypeVersionSummaryResponse> - list of device type version summaries for all device type versions
     */
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<DeviceTypeVersionSummaryResponse> list() {
        List<DeviceTypeVersion> allDevices = deviceTypeVersionService.getAllDeviceTypeVersions();
        return DeviceTypeVersionSummaryResponse.from(allDevices, dateTimeFormatter);
    }


    /**
     * Endpoint for getting device type version details by id.
     * @param id - id of device type version to be retrieved
     * @return
     */
    @GetMapping("/{id}")
    public DeviceTypeVersionResponse getDeviceTypeVersion(@PathVariable Long id) {
        DeviceTypeVersion deviceTypeVersion = deviceTypeVersionService.getDeviceTypeVersion(id);
        return DeviceTypeVersionResponse.from(deviceTypeVersion, dateTimeFormatter);
    }


    /**
     * Endpoint deprecating a device type version.
     * @param id - id of device type version to be updated
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deprecateDeviceVersion(@PathVariable Long id) {
        deviceTypeVersionService.deprecateDeviceTypeVersionByVersionId(id);
    }



    @Getter
    @Builder
    public static class CreateDeviceTypeVersionRequest {

        @NotNull(message = "Device id cannot be null")
        private Long deviceId;

        @Size(min = 1, message = "At least one functionality id must be provided")
        private List<Long> functionalityIds;

        @Size(min = 1, message = "At least one property value is required")
        private List<PropertyValue> propertyValues;
    }
}
