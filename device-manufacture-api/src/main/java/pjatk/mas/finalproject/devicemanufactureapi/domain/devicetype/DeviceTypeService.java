package pjatk.mas.finalproject.devicemanufactureapi.domain.devicetype;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pjatk.mas.finalproject.devicemanufactureapi.domain.exception.NotFoundException;
import pjatk.mas.finalproject.devicemanufactureapi.domain.functionality.exception.FunctionalityNameAlreadyTakenException;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

import static pjatk.mas.finalproject.devicemanufactureapi.domain.devicetype.DeviceTypeServiceRequests.DeviceTypeCreateDetails;

@Service
@RequiredArgsConstructor
public class DeviceTypeService {

    private final DeviceTypeRepository deviceTypeRepository;

    @Transactional
    public DeviceType create(@Valid DeviceTypeCreateDetails createDetails){

        validateFunctionalityName(createDetails);

        DeviceType deviceType = DeviceType.builder()
                .name(createDetails.getName())
                .powerConsumption(createDetails.getPowerConsumption())
                .deviceTypeStatus(DeviceTypeStatus.DRAFT)
                .build();

        return deviceTypeRepository.save(deviceType);
    }

    public List<DeviceType> getAllDevices(){
        return deviceTypeRepository.findAll();
    }

    public DeviceType getDeviceType(Long id){
        return deviceTypeRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public boolean existsById(Long deviceId) {
       return deviceTypeRepository.existsById(deviceId);
    }

    private void validateFunctionalityName(DeviceTypeCreateDetails createDetails) {
        boolean nameIsUsed = deviceTypeRepository.existsByName(createDetails.getName());

        if (nameIsUsed) {
            throw new FunctionalityNameAlreadyTakenException(createDetails.getName());
        }
    }

    public void setToVersioned(Long deviceId) {
        DeviceType deviceType = getDeviceType(deviceId);
        deviceType.setDeviceTypeStatus(DeviceTypeStatus.VERSIONED);
        deviceTypeRepository.save(deviceType);
    }
}
