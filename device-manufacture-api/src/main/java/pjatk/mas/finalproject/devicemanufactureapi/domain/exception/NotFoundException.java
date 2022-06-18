package pjatk.mas.finalproject.devicemanufactureapi.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public  class NotFoundException extends RuntimeException {
    public NotFoundException(Long id) {
        super(String.format("Resource not found for id: %d", id));
    }

    public NotFoundException(String message) {
        super(message);
    }
}
