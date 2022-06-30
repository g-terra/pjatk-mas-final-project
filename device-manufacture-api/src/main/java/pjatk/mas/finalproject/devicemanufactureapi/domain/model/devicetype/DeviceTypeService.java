package pjatk.mas.finalproject.devicemanufactureapi.domain.model.devicetype;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pjatk.mas.finalproject.devicemanufactureapi.domain.exceptions.FunctionalityNameAlreadyTakenException;
import pjatk.mas.finalproject.devicemanufactureapi.domain.exceptions.NotFoundException;

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
     *
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
        return deviceTypeRepository.save(deviceType);
    }

    /**
     * Gets all device types.
     *
     * @return List<DeviceType> - all device types
     * @throws {@link NotFoundException} - if no device types are found
     */
    public List<DeviceType> getAllDevices() {
        return deviceTypeRepository.findAll();
    }


    /**
     * Gets all device types divided into pages.
     * @param pageNumber - the number of page to be returned
     * @param pageSize - the number of items to be present in a page
     * @param name - the name of the device type to be searched for
     *             - if null or blank, all device types are returned(paged)
     * @return Page<DeviceType> - all device types
     */
    public Page<DeviceType> getAllDevices(String name, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("id"));
        return deviceTypeRepository.findAllByNameContainingIgnoreCase(name, pageable);

    }


    /**
     * Gets device type by its id.
     *
     * @param deviceTypeId - id of the device type to be retrieved
     * @return DeviceType - device type with the given id
     */
    public DeviceType getDeviceType(Long deviceTypeId) {
        return deviceTypeRepository.findById(deviceTypeId).orElseThrow(() -> new NotFoundException(deviceTypeId));
    }

    /**
     * Updates status of a device type to versioned based its id.
     *
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

    @Transactional
    public void deprecateDevice(Long id) {
        DeviceType deviceType = getDeviceType(id);
        deviceType.setDeviceTypeStatus(DeviceTypeStatus.DEPRECATED);
        deviceTypeRepository.save(deviceType);
    }

    public DeviceType findById(Long deviceTypeId) {
        return deviceTypeRepository.findById(deviceTypeId).orElseThrow(() -> new NotFoundException(deviceTypeId));
    }
}
