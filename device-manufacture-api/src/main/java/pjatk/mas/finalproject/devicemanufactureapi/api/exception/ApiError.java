package pjatk.mas.finalproject.devicemanufactureapi.api.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Generic error class for handling errors.
 */
@Getter
@AllArgsConstructor
public class ApiError {
    private final String type;
    private final int status;
    private final String message;
}
