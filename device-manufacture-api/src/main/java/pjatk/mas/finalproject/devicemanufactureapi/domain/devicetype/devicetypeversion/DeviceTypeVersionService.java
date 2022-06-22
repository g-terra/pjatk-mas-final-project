package pjatk.mas.finalproject.devicemanufactureapi.domain.devicetype.devicetypeversion;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pjatk.mas.finalproject.devicemanufactureapi.domain.devicetype.DeviceType;
import pjatk.mas.finalproject.devicemanufactureapi.domain.devicetype.DeviceTypeService;
import pjatk.mas.finalproject.devicemanufactureapi.domain.exception.NotFoundException;
import pjatk.mas.finalproject.devicemanufactureapi.domain.functionality.Functionality;
import pjatk.mas.finalproject.devicemanufactureapi.domain.functionality.FunctionalityService;
import pjatk.mas.finalproject.devicemanufactureapi.domain.types.PropertyType;
import pjatk.mas.finalproject.devicemanufactureapi.domain.types.exceptions.PropertyOneToOneMappingMismatchException;
import pjatk.mas.finalproject.devicemanufactureapi.domain.types.exceptions.PropertyValueMappingMismatchException;
import pjatk.mas.finalproject.devicemanufactureapi.domain.types.Property;
import pjatk.mas.finalproject.devicemanufactureapi.domain.types.PropertyValue;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static pjatk.mas.finalproject.devicemanufactureapi.domain.devicetype.devicetypeversion.DeviceTypeVersionServiceRequest.DeviceTypeVersionCreateDetails;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeviceTypeVersionService {

    private final DeviceTypeVersionRepository deviceTypeVersionRepository;
    private final DeviceTypeService deviceTypeService;

    private final FunctionalityService functionalityService;

    public DeviceTypeVersion getDeviceTypeVersion(Long deviceTypeVersionId) {
        return deviceTypeVersionRepository.findById(deviceTypeVersionId).orElseThrow(() -> new NotFoundException(deviceTypeVersionId));
    }


    public List<DeviceTypeVersion> getAllDeviceTypeVersions() {
        return deviceTypeVersionRepository.findAll();
    }


    @Transactional
    public DeviceTypeVersion create(DeviceTypeVersionCreateDetails createDetails) {
        DeviceType deviceType = deviceTypeService.getDeviceType(createDetails.getDeviceId());
        return createVersion(createDetails, deviceType);
    }

    private DeviceTypeVersion createVersion(DeviceTypeVersionCreateDetails createDetails, DeviceType deviceType) {

        validateDeviceType(deviceType);

        DeviceTypeVersion deviceTypeVersion = buildDeviceTypeVersion(createDetails, deviceType);

        validateAllProperties(deviceTypeVersion);

        DeviceTypeVersion save = deviceTypeVersionRepository.save(deviceTypeVersion);

        deviceTypeService.setToVersioned(deviceType.getId());

        log.info("DeviceTypeVersion with id {} created", save.getId());

        return save;
    }

    private DeviceTypeVersion buildDeviceTypeVersion(DeviceTypeVersionCreateDetails createDetails, DeviceType deviceType) {
        List<Functionality> functionalities = functionalityService.getFunctionalities(createDetails.getFunctionalityIds());

        return DeviceTypeVersion.builder()
                .deviceType(deviceType)
                .functionalities(functionalities)
                .propertyValues(createDetails.getPropertyValues())
                .deviceTypeVersionStatus(DeviceTypeVersionStatus.AVAILABLE)
                .build();
    }

    private void validateDeviceType(DeviceType deviceType) {
        boolean deviceTypeExists = deviceTypeService.existsById(deviceType.getId());
        if (!deviceTypeExists) {
            throw new NotFoundException("No device type found with id: " + deviceType.getId());
        }
    }

    private void validateAllProperties(DeviceTypeVersion deviceTypeVersion) {

        validateAllProperties(
                getFunctionalityRequiredProperties(deviceTypeVersion),
                deviceTypeVersion.getPropertyValues()
        );
    }

    private List<Property> getFunctionalityRequiredProperties(DeviceTypeVersion deviceTypeVersion) {
        return deviceTypeVersion.getFunctionalities()
                .stream()
                .map(Functionality::getProperties)
                .collect(ArrayList::new, ArrayList::addAll, ArrayList::addAll);
    }

    private void validateAllProperties(List<Property> requiredProperties, List<PropertyValue> requiredPropertyValues) {
        validatePropertyMappingSize(requiredProperties, requiredPropertyValues);
        validatePropertiesMappings(requiredProperties, requiredPropertyValues);
        validatePropertiesConstraints(requiredProperties, requiredPropertyValues);
    }

    private void validatePropertyMappingSize(List<Property> requiredProperties, List<PropertyValue> requiredPropertyValues) {
        if (requiredProperties.size() != requiredPropertyValues.size()) {
            throw new PropertyOneToOneMappingMismatchException();
        }
    }

    private void validatePropertiesMappings(List<Property> requiredProperties, List<PropertyValue> requiredPropertyValues) {
        requiredProperties.forEach(property -> checkProperValuePresence(requiredPropertyValues, property));
    }

    private void validatePropertiesConstraints(List<Property> requiredProperties, List<PropertyValue> requiredPropertyValues) {
        Map<String, Property> requiredPropertiesMap = requiredProperties.stream().collect(Collectors.toMap(Property::getName, r->r));
        Map<String, String> requiredPropertiesValuesMap = requiredPropertyValues.stream().collect(Collectors.toMap(PropertyValue::getName, PropertyValue::getValue));

        requiredPropertiesMap.keySet().forEach(
                key -> PropertyType.Validate(requiredPropertiesMap.get(key), requiredPropertiesValuesMap.get(key))
        );
    }

    private void checkProperValuePresence(List<PropertyValue> requiredPropertyValues, Property property) {

        boolean match = requiredPropertyValues.stream()
                .map(PropertyValue::getName)
                .anyMatch(n -> Objects.equals(n, property.getName()));

        if (!match) {
            throw new PropertyValueMappingMismatchException(property.getName());
        }
    }


}
