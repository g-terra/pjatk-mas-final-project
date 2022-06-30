package pjatk.mas.finalproject.devicemanufactureapi.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when the number of required properties of a functionality
 * is not equal to the number of provided property values
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class PropertyOneToOneMappingMismatchException extends RuntimeException {
    private static final String MISMATCH_MESSAGE = "Number of functionality properties and property values must be the same";
    public PropertyOneToOneMappingMismatchException() {
        super(MISMATCH_MESSAGE);
    }

}
