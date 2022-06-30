package pjatk.mas.finalproject.devicemanufactureapi.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * Exception thrown when trying to retrieve an entity that does not exist.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public  class NotFoundException extends RuntimeException {
    public NotFoundException(Long id) {
        super(String.format("Resource not found for id: %d", id));
    }

    public NotFoundException(String message) {
        super(message);
    }
}
