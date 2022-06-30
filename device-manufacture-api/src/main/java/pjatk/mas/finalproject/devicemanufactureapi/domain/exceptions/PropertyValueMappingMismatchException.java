package pjatk.mas.finalproject.devicemanufactureapi.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import pjatk.mas.finalproject.devicemanufactureapi.domain.types.PropertyValue;

/**
 * Exception thrown when a {@link PropertyValue} object is created without a value.
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class PropertyValueMappingMismatchException extends RuntimeException {

    private static final String MISMATCH_MESSAGE = "Property '%s' is missing property value";
    public PropertyValueMappingMismatchException(String s) {
        super(String.format(MISMATCH_MESSAGE, s));
    }

}
