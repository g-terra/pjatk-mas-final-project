package pjatk.mas.finalproject.devicemanufactureapi.domain.functionality.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class FunctionalityNameAlreadyTakenException extends RuntimeException {

    private static final String NAME_ALREADY_TAKEN_MESSAGE =  "Functionality with name \"%s\" already exists";
    public FunctionalityNameAlreadyTakenException(String name) {
        super(String.format(NAME_ALREADY_TAKEN_MESSAGE, name));
    }
}
