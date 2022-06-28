package pjatk.mas.finalproject.devicemanufactureapi.domain.model.devicetypeversion;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pjatk.mas.finalproject.devicemanufactureapi.domain.exceptions.NotFoundException;
import pjatk.mas.finalproject.devicemanufactureapi.domain.model.devicetype.DeviceType;
import pjatk.mas.finalproject.devicemanufactureapi.domain.model.devicetype.DeviceTypeService;
import pjatk.mas.finalproject.devicemanufactureapi.domain.model.functionality.Functionality;
import pjatk.mas.finalproject.devicemanufactureapi.domain.model.functionality.FunctionalityService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Service for creating and managing device type versions.
 */
@Service
@RequiredArgsConstructor
public class DeviceTypeVersionService {

    private final DeviceTypeVersionRepository deviceTypeVersionRepository;
    private final DeviceTypeService deviceTypeService;
    private final FunctionalityService functionalityService;
    private final DeviceTypeVersionValidator deviceTypeVersionValidator;


    /**
     * Creates a new device type version and persists it.
     *
     * @param createDetails - details of the device type version to be created
     * @return DeviceTypeVersion - created device type version
     */
    @Transactional
    public DeviceTypeVersion create(DeviceTypeVersionServiceRequest.DeviceTypeVersionCreateDetails createDetails) {
        DeviceTypeVersion deviceTypeVersion = createNewVersion(createDetails);
        return persist(deviceTypeVersion);
    }



    /**
     * Gets all device type by its version id.
     *
     * @param deviceTypeVersionId id of the device type version to be retrieved
     * @return device type version with the given id
     */
    public DeviceTypeVersion getDeviceTypeVersion(Long deviceTypeVersionId) {
        return deviceTypeVersionRepository.findById(deviceTypeVersionId).orElseThrow(() -> new NotFoundException(deviceTypeVersionId));
    }

    /**
     * Creates a new device type version.
     *
     * @return List<DeviceTypeVersion> - all device type versions
     */
    public List<DeviceTypeVersion> getAllDeviceTypeVersions() {
        return deviceTypeVersionRepository.findAll();
    }




    private DeviceTypeVersion createNewVersion(DeviceTypeVersionServiceRequest.DeviceTypeVersionCreateDetails createDetails) {
        DeviceType deviceType = deviceTypeService.getDeviceType(createDetails.getDeviceId());
        DeviceTypeVersion deviceTypeVersion = buildDeviceTypeVersion(createDetails, deviceType);
        deviceTypeVersionValidator.validate(deviceTypeVersion);
        return deviceTypeVersion;
    }

    private DeviceTypeVersion persist(DeviceTypeVersion deviceTypeVersion) {
        return deviceTypeVersionRepository.save(deviceTypeVersion);
    }


    private DeviceTypeVersion buildDeviceTypeVersion(DeviceTypeVersionServiceRequest.DeviceTypeVersionCreateDetails createDetails, DeviceType deviceType) {
        List<Functionality> functionalities = functionalityService.getFunctionalities(createDetails.getFunctionalityIds());

        Long versionNumber = getVersionNumber(deviceType);

        return DeviceTypeVersion.builder()
                .deviceType(deviceType)
                .functionalities(functionalities)
                .propertyValues(createDetails.getPropertyValues())
                .deviceTypeVersionStatus(DeviceTypeVersionStatus.AVAILABLE)
                .versionNumber(versionNumber)
                .build();
    }

    private Long getVersionNumber(DeviceType deviceType) {
        return deviceTypeVersionRepository
                .findFirstByDeviceTypeIdOrderByCreateDateTimeDesc(deviceType.getId())
                .map(DeviceTypeVersion::getVersionNumber)
                .map(v -> v + 1)
                .orElse(1L);
    }


    @Transactional
    public void deleteDeviceTypeVersion(Long id) {
        Optional<DeviceTypeVersion> optionalDeviceTypeVersion = deviceTypeVersionRepository.findById(id);
        if (optionalDeviceTypeVersion.isPresent()) {
            DeviceTypeVersion deviceTypeVersion = optionalDeviceTypeVersion.get();
            deviceTypeVersion.setDeviceTypeVersionStatus(DeviceTypeVersionStatus.DEPRECATED);
            deviceTypeVersionRepository.save(deviceTypeVersion);
        } else {
            throw new NotFoundException(id);
        }

    }
}
