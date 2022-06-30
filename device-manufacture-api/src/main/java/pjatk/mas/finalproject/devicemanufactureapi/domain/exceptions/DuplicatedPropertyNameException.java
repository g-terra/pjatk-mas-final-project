package pjatk.mas.finalproject.devicemanufactureapi.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * Exception thrown when a functionality have property with same name.
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class DuplicatedPropertyNameException extends RuntimeException {
    private static final String DUPLICATED_PROPERTY_MESSAGE = "Functionality cannot have duplicate property names";
    public DuplicatedPropertyNameException() {
        super(DUPLICATED_PROPERTY_MESSAGE);
    }
}
