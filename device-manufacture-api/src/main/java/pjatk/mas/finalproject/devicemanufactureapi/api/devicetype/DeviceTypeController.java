package pjatk.mas.finalproject.devicemanufactureapi.api.devicetype;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pjatk.mas.finalproject.devicemanufactureapi.domain.model.devicetype.DeviceType;
import pjatk.mas.finalproject.devicemanufactureapi.domain.model.devicetype.DeviceTypeService;
import pjatk.mas.finalproject.devicemanufactureapi.domain.model.devicetypeversion.DeviceTypeVersionService;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.format.DateTimeFormatter;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static pjatk.mas.finalproject.devicemanufactureapi.domain.model.devicetype.DeviceTypeServiceRequests.DeviceTypeCreateDetails;

@RestController
@RequestMapping("/device-type")
@RequiredArgsConstructor
@Validated
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class DeviceTypeController {

    private final DeviceTypeService deviceTypeService;
    private final DateTimeFormatter dateTimeFormatter;
    private final DeviceTypeVersionService deviceTypeVersionService;


    /**
     * Post endpoint for creating a new device type.
     * @param createDeviceTypeRequest - client request with device type details that will be used to create new device type
     * @return DeviceTypeResponse - response with created device type details
     */
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


    /**
     * Get endpoint for listing all device types paged.
     * @return List<DeviceTypeSummaryResponse> - list of device type summaries for all device types
     */
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public Page<DeviceTypeSummaryResponse> list(
            @RequestParam @Min(0) @Max(100) int pageNumber,
            @RequestParam @Min(0) @Max(100) int pageSize,
            @RequestParam(required = false , defaultValue = "") String name) {
        Page<DeviceType> allDevices = deviceTypeService.getAllDevices(name , pageNumber, pageSize);
        return DeviceTypeSummaryResponse.from(allDevices,dateTimeFormatter);
    }


    /**
     * Get endpoint for getting device type details by id.
     * @param id - id of device type to get
     * @return DeviceTypeResponse - response with device type details
     */
    @GetMapping("/{id}")
    public DeviceTypeResponse getDeviceType(@PathVariable Long id) {
        DeviceType deviceType = deviceTypeService.getDeviceType(id);
        return DeviceTypeResponse.from(deviceType);
    }


    /**
     * endpoint to deprecate device type with all its versions.
     * @param id - id of device type to be deprecated
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deprecateDeviceType(@PathVariable Long id) {
        deviceTypeService.deprecateDevice(id);
        deviceTypeVersionService.deprecateAllDeviceTypeVersionByDeviceTypeId(id);
    }

    /**
     * Data transformation object for handling required client data on creating new device type endpoint.
     */
    @Getter
    @Builder
    public static class CreateDeviceTypeRequest {

        @NotNull
        @Max(value = 2000, message = "power consumption must be less than 2000")
        @Min(value = 1, message = "power consumption must be greater than 0")
        private Integer powerConsumption;

        @NotNull(message = "name must be provided")
        @NotBlank(message = "name is required")
        private String name;
    }
}
