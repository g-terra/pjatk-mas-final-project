package pjatk.mas.finalproject.devicemanufactureapi.domain.devicetype.devicetypeversion;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pjatk.mas.finalproject.devicemanufactureapi.domain.functionality.Functionality;
import pjatk.mas.finalproject.devicemanufactureapi.domain.types.Property;
import pjatk.mas.finalproject.devicemanufactureapi.domain.types.PropertyType;
import pjatk.mas.finalproject.devicemanufactureapi.domain.types.PropertyValue;
import pjatk.mas.finalproject.devicemanufactureapi.domain.types.exceptions.PropertyOneToOneMappingMismatchException;
import pjatk.mas.finalproject.devicemanufactureapi.domain.types.exceptions.PropertyValueMappingMismatchException;

import java.util.*;
import java.util.stream.Collectors;

/**
 * DeviceTypeVersionValidator - validates A DeviceTypeVersion before it can be persisted.
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

    private Set<Property> getFunctionalityRequiredProperties(DeviceTypeVersion deviceTypeVersion) {
        return deviceTypeVersion.getFunctionalities()
                .stream()
                .map(Functionality::getProperties)
                .collect(HashSet::new, HashSet::addAll, HashSet::addAll);
    }

    private void validateAll(Set<Property> requiredProperties, List<PropertyValue> requiredPropertyValues) {
        validatePropertyMappingSize(requiredProperties, requiredPropertyValues);
        validatePropertiesMappings(requiredProperties, requiredPropertyValues);
        validatePropertiesConstraints(requiredProperties, requiredPropertyValues);
    }

    private void validatePropertyMappingSize(Set<Property> requiredProperties, List<PropertyValue> requiredPropertyValues) {
        if (requiredProperties.size() != requiredPropertyValues.size()) {
            throw new PropertyOneToOneMappingMismatchException();
        }
    }

    private void validatePropertiesMappings(Set<Property> requiredProperties, List<PropertyValue> requiredPropertyValues) {
        requiredProperties.forEach(property -> checkProperValuePresence(requiredPropertyValues, property));
    }

    private void validatePropertiesConstraints(Set<Property> requiredProperties, List<PropertyValue> requiredPropertyValues) {
        Map<String, Property> requiredPropertiesMap = requiredProperties.stream().collect(Collectors.toMap(Property::getName, r -> r));
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
