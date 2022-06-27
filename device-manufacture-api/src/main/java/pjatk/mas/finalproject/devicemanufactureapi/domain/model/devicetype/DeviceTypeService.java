package pjatk.mas.finalproject.devicemanufactureapi.domain.model.devicetype;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pjatk.mas.finalproject.devicemanufactureapi.domain.exceptions.NotFoundException;
import pjatk.mas.finalproject.devicemanufactureapi.domain.exceptions.FunctionalityNameAlreadyTakenException;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

import static pjatk.mas.finalproject.devicemanufactureapi.domain.model.devicetype.DeviceTypeServiceRequests.DeviceTypeCreateDetails;


/**
 * Service class for creating and managing device types.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class DeviceTypeService {

    private final DeviceTypeRepository deviceTypeRepository;

    /**
     * Creates a new device type and persists it.
     * @param createDetails - details required for creating a new device type
     * @return DeviceType - created device type
     */
    @Transactional
    public DeviceType create(@Valid DeviceTypeCreateDetails createDetails) {

        validateFunctionalityName(createDetails);

        DeviceType deviceType = DeviceType.builder()
                .name(createDetails.getName())
                .powerConsumption(createDetails.getPowerConsumption())
                .deviceTypeStatus(DeviceTypeStatus.DRAFT)
                .build();
        DeviceType newDevice = deviceTypeRepository.save(deviceType);

        log.info("DeviceType created: {}", newDevice);

        return newDevice;
    }

    /**
     * Gets all device types.
     * @return List<DeviceType> - all device types
     * @throws {@link NotFoundException} - if no device types are found
     */
    public List<DeviceType> getAllDevices() {
        return deviceTypeRepository.findAll();
    }

    /**
     * Gets device type by its id.
     * @param deviceTypeId - id of the device type to be retrieved
     * @return DeviceType - device type with the given id
     */
    public DeviceType getDeviceType(Long deviceTypeId) {
        return deviceTypeRepository.findById(deviceTypeId).orElseThrow(() -> new NotFoundException(deviceTypeId));
    }

    /**
     * Updates status of a device type to versioned based its id.
     * @param deviceTypeId - id of the device type to be updated
     */
    public void setDeviceAsVersioned(Long deviceTypeId) {
        DeviceType deviceType = getDeviceType(deviceTypeId);
        deviceType.setDeviceTypeStatus(DeviceTypeStatus.VERSIONED);
        deviceTypeRepository.save(deviceType);
    }

    private void validateFunctionalityName(DeviceTypeCreateDetails createDetails) {
        boolean nameIsUsed = deviceTypeRepository.existsByName(createDetails.getName());

        if (nameIsUsed) {
            throw new FunctionalityNameAlreadyTakenException(createDetails.getName());
        }
    }

}
