package pjatk.mas.finalproject.devicemanufactureapi.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * Api Error Processor. Supports conversion of exceptions to ApiErrors.
 */
@Component
public class ApiErrorProcessor {

    /**
     * Method overloading of  processException(String Type, HttpStatus status, String message) - used for direct handling exceptions.
     * @param ex the exception to process
     * @return an {@link ApiError} with the appropriate status and message
     */
    public ApiError processException(String Type, HttpStatus status, Exception ex) {
        return processException(Type, status, ex.getMessage());
    }

    /**
     * Processes error information and returns an {@link ApiError} with the appropriate status and message.
     *
     * @param Type    the type of error (e.g. "constraint-violation")
     * @param status  the status of the error (e.g. 400)
     * @param message the message of the error (e.g. "Cannot be null")
     * @return an {@link ApiError} with the appropriate status and message
     */
    public ApiError processException(String Type, HttpStatus status, String message) {
        return new ApiError(Type, status.value(), message);
    }

    /**
     * Processes field error form javax validation and returns an {@link ApiError} with the appropriate status and message.
     *
     * @param fieldErrors the field errors to process
     * @return an {@link ApiError} with the appropriate status and message
     */
    public ApiError processFieldErrors(List<FieldError> fieldErrors) {
        ApiRequestValidationApiError validationError = new ApiRequestValidationApiError(BAD_REQUEST.value(), "validation error");
        for (org.springframework.validation.FieldError fieldError : fieldErrors) {
            validationError.addFieldError(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return validationError;
    }

}
