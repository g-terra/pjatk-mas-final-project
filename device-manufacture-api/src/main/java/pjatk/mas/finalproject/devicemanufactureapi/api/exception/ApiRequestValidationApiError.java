package pjatk.mas.finalproject.devicemanufactureapi.api.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * Specific error class for handling validation errors.
 */
@Getter
public class ApiRequestValidationApiError extends ApiError {

    private final List<FieldErrorSimplified> fieldErrors = new ArrayList<>();


    /**
     * Constructor - Creates a new ApiRequestValidationApiError object with default type of validation-error.
     * @param status the status of the error (e.g. 400)
     * @param message the message of the error (e.g. "Cannot be null")
     */
    public ApiRequestValidationApiError(int status, String message) {
        super("validation-error", status, message);
    }

    /**
     * Adds a field error to the list of field errors.
     * @param path the path of the field that caused the error
     * @param message the message of the error
     */
    public void addFieldError(String path, String message) {
        fieldErrors.add(FieldErrorSimplified.builder().fieldName(path).message(message).build());
    }

    /**
     * Reduced version of FieldError for easier handling and presentation.
     */
    @Getter
    @AllArgsConstructor
    @Builder
    private static class FieldErrorSimplified {
        private String fieldName;
        private String message;
    }
}
