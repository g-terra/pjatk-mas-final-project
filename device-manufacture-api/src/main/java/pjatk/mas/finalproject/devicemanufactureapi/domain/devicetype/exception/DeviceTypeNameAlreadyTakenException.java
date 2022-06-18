package pjatk.mas.finalproject.devicemanufactureapi.domain.devicetype.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class DeviceTypeNameAlreadyTakenException extends RuntimeException {

    private static final String NAME_ALREADY_TAKEN_MESSAGE =  "Device type with name \"%s\" already exists";
    public DeviceTypeNameAlreadyTakenException(String name) {
        super(String.format(NAME_ALREADY_TAKEN_MESSAGE, name));
    }
}
