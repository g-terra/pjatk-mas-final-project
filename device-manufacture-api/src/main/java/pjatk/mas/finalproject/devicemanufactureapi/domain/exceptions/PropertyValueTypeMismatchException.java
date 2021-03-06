package pjatk.mas.finalproject.devicemanufactureapi.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import pjatk.mas.finalproject.devicemanufactureapi.domain.types.PropertyValue;

/**
 * Exception thrown when a {@link PropertyValue} object is created with a value that does not match its type.
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class PropertyValueTypeMismatchException extends RuntimeException {

    private static final String MISMATCH_MESSAGE = "Property %s should have value equals to %s";
    public PropertyValueTypeMismatchException(String propertyName, String propertyExpectedType) {
        super(String.format(MISMATCH_MESSAGE,propertyName,propertyExpectedType));
    }

}
