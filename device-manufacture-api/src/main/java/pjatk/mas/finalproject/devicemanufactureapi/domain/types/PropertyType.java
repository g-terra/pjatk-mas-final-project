package pjatk.mas.finalproject.devicemanufactureapi.domain.types;

import pjatk.mas.finalproject.devicemanufactureapi.domain.exceptions.PropertyValueTypeMismatchException;

import java.util.Map;
import java.util.function.BiConsumer;
import java.util.regex.Pattern;

import static org.apache.commons.lang3.StringUtils.isNumeric;

public enum PropertyType {


    YES_NO,
    NUMBER,
    TEXT;

    private final static Pattern TEXT_REGEX = Pattern.compile("^[a-zA-Z\\d_\\-\\s]*$");

    /**
     * validate a value against a given property type
     *
     * @param property Property Type to be used as base for validation.
     * @param value    value to be checked
     */
    public static void Validate(Property property, String value) {
        validations.get(property.getType()).accept(property.getName(), value);
    }

    private static final Map<PropertyType, BiConsumer<String, String>> validations = Map.of(
            PropertyType.YES_NO, PropertyType::validateYesNo,
            PropertyType.NUMBER, PropertyType::validateNumber,
            PropertyType.TEXT, PropertyType::validateText
    );

    private static void validateYesNo(String propertyName, String value) {
        if (value.equals("yes")) return;
        if (value.equals("no")) return;
        throw new PropertyValueTypeMismatchException(propertyName, "yes or no");
    }

    private static void validateNumber(String propertyName, String number) {
        if (isNumeric(number)) return;
        throw new PropertyValueTypeMismatchException(propertyName, "a numeric value");
    }

    private static void validateText(String propertyName, String text) {
        if (TEXT_REGEX.matcher(text).find()) return;
        throw new PropertyValueTypeMismatchException(propertyName, "a numeric value");
    }

}
