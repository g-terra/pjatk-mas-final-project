package pjatk.mas.finalproject.devicemanufactureapi.domain.devicetype.devicetypeversion;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pjatk.mas.finalproject.devicemanufactureapi.domain.devicetype.DeviceType;
import pjatk.mas.finalproject.devicemanufactureapi.domain.devicetype.DeviceTypeService;
import pjatk.mas.finalproject.devicemanufactureapi.domain.exception.NotFoundException;
import pjatk.mas.finalproject.devicemanufactureapi.domain.functionality.Functionality;
import pjatk.mas.finalproject.devicemanufactureapi.domain.functionality.FunctionalityService;

import javax.transaction.Transactional;
import java.util.List;

import static pjatk.mas.finalproject.devicemanufactureapi.domain.devicetype.devicetypeversion.DeviceTypeVersionServiceRequest.DeviceTypeVersionCreateDetails;

/**
 * Service for creating and managing device type versions.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class DeviceTypeVersionService {

    private final DeviceTypeVersionRepository deviceTypeVersionRepository;
    private final DeviceTypeService deviceTypeService;
    private final FunctionalityService functionalityService;
    private final DeviceTypeVersionValidator deviceTypeVersionValidator;

    /**
     * Gets all device type by its version id.
     * @param deviceTypeVersionId id of the device type version to be retrieved
     * @return device type version with the given id
     */
    public DeviceTypeVersion getDeviceTypeVersion(Long deviceTypeVersionId) {
        return deviceTypeVersionRepository.findById(deviceTypeVersionId).orElseThrow(() -> new NotFoundException(deviceTypeVersionId));
    }

    /**
     * Creates a new device type version.
     * @return List<DeviceTypeVersion> - all device type versions
     */
    public List<DeviceTypeVersion> getAllDeviceTypeVersions() {
        return deviceTypeVersionRepository.findAll();
    }


    /**
     * Creates a new device type version and persists it.
     * @param createDetails - details of the device type version to be created
     * @return DeviceTypeVersion - created device type version
     */
    @Transactional
    public DeviceTypeVersion create(DeviceTypeVersionCreateDetails createDetails) {
        DeviceTypeVersion deviceTypeVersion = createNewVersion(createDetails);
        return  persist(deviceTypeVersion);
    }

    private DeviceTypeVersion createNewVersion(DeviceTypeVersionCreateDetails createDetails) {
        DeviceType deviceType = deviceTypeService.getDeviceType(createDetails.getDeviceId());
        DeviceTypeVersion deviceTypeVersion = buildDeviceTypeVersion(createDetails, deviceType);
        deviceTypeVersionValidator.validate(deviceTypeVersion);
        return deviceTypeVersion;
    }

    private DeviceTypeVersion persist(DeviceTypeVersion deviceTypeVersion) {
        DeviceTypeVersion save = deviceTypeVersionRepository.save(deviceTypeVersion);

        deviceTypeService.setToVersioned(deviceTypeVersion.getDeviceType().getId());

        log.info("DeviceTypeVersion with id {} created", save.getId());
        return save;
    }


    private DeviceTypeVersion buildDeviceTypeVersion(DeviceTypeVersionCreateDetails createDetails, DeviceType deviceType) {
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



}
