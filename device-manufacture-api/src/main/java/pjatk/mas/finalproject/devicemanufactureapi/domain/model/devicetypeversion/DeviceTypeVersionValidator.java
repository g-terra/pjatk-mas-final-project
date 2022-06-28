package pjatk.mas.finalproject.devicemanufactureapi.domain.model.devicetypeversion;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pjatk.mas.finalproject.devicemanufactureapi.domain.exceptions.PropertyOneToOneMappingMismatchException;
import pjatk.mas.finalproject.devicemanufactureapi.domain.exceptions.PropertyValueMappingMismatchException;
import pjatk.mas.finalproject.devicemanufactureapi.domain.model.functionality.Functionality;
import pjatk.mas.finalproject.devicemanufactureapi.domain.types.Property;
import pjatk.mas.finalproject.devicemanufactureapi.domain.types.PropertyValue;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * DeviceTypeVersionValidator - validates integrity of device type version data.
 */
@Component
@RequiredArgsConstructor
public class DeviceTypeVersionValidator {

    /**
     * Validates a DeviceTypeVersion before it can be persisted.
     *
     * @param deviceTypeVersion - DeviceTypeVersion to be validated
     * @throws PropertyOneToOneMappingMismatchException if number of property values in the DeviceTypeVersion does not match number of required by the version's functionalities
     * @throws PropertyValueMappingMismatchException    if the property values set are not the ones required by the version's functionalities
     */
    public void validate(DeviceTypeVersion deviceTypeVersion) {

        validateAll(
                getFunctionalityRequiredProperties(deviceTypeVersion),
                deviceTypeVersion.getPropertyValues()
        );
    }

    private Map<Long, List<Property>> getFunctionalityRequiredProperties(DeviceTypeVersion deviceTypeVersion) {
        return deviceTypeVersion.getFunctionalities()
                .stream()
                .collect(Collectors.toMap(
                        Functionality::getId,
                        Functionality::getProperties
                ));

    }

    private void validateAll(Map<Long, List<Property>> requiredProperties, List<PropertyValue> requiredPropertyValues) {
        validatePropertyMappingSize(requiredProperties, requiredPropertyValues);
        validatePropertiesMappings(requiredProperties, requiredPropertyValues);
    }

    private void validatePropertyMappingSize(Map<Long, List<Property>> requiredProperties, List<PropertyValue> requiredPropertyValues) {
        requiredProperties.keySet().forEach(
                functionalityId -> {
                    long valueCount = requiredPropertyValues.stream().filter(
                            propertyValue -> propertyValue.getParentFunctionalityId() == functionalityId).count();

                    if (requiredProperties.get(functionalityId).size() != valueCount) {
                        throw new PropertyOneToOneMappingMismatchException();
                    }
                }
        );
    }

    private void validatePropertiesMappings(
            Map<Long, List<Property>> functionalityProperties,
            List<PropertyValue> providedPropertyValues
    ) {
        functionalityProperties.keySet().forEach(
                functionalityId -> checkRequiredPropertiesPresence(functionalityProperties, providedPropertyValues, functionalityId)
        );
    }

    private void checkRequiredPropertiesPresence(Map<Long, List<Property>> functionalityProperties, List<PropertyValue> providedPropertyValues, Long functionalityId) {
        List<Property> requiredProperty = functionalityProperties.get(functionalityId);

        List<PropertyValue> propertyValuesForFunctionality = providedPropertyValues.stream().filter(
                propertyValue -> propertyValue.getParentFunctionalityId() == functionalityId).collect(Collectors.toList());

        requiredProperty.forEach(
                property -> checkRequiredPropertyPresence(propertyValuesForFunctionality, property)
        );
    }

    private void checkRequiredPropertyPresence(List<PropertyValue> propertyValuesForFunctionality, Property property) {

        propertyValuesForFunctionality.stream().filter(
                value -> Objects.equals(value.getName(), property.getName())).findFirst().orElseThrow(
                () -> new PropertyValueMappingMismatchException(property.getName()));
    }
}
