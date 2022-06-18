package pjatk.mas.finalproject.devicemanufactureapi.api.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pjatk.mas.finalproject.devicemanufactureapi.domain.exception.NotFoundException;
import pjatk.mas.finalproject.devicemanufactureapi.domain.functionality.exception.FunctionalityNameAlreadyTakenException;
import pjatk.mas.finalproject.devicemanufactureapi.domain.types.exceptions.PropertyOneToOneMappingMismatchException;
import pjatk.mas.finalproject.devicemanufactureapi.domain.types.exceptions.PropertyValueMappingMismatchException;
import pjatk.mas.finalproject.devicemanufactureapi.domain.types.exceptions.PropertyValueTypeMismatchException;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ApiExceptionHandler{


    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Error httpMessageNotReadableException(HttpMessageNotReadableException ex){
          return processException("Invalid request body", BAD_REQUEST, ex.getMessage());
    }


    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Error methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<org.springframework.validation.FieldError> fieldErrors = result.getFieldErrors();
        return processFieldErrors(fieldErrors);
    }

    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public Error handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException ex) {
        return processException("constraint-violation", BAD_REQUEST, ex);
    }

    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(FunctionalityNameAlreadyTakenException.class)
    public Error handleFunctionalityNameAlreadyTakenException(FunctionalityNameAlreadyTakenException ex) {
        return processException("functionality-already-exists", BAD_REQUEST, ex);
    }

    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(PropertyValueMappingMismatchException.class)
    public Error handlePropertyValueMappingMismatchException(PropertyValueMappingMismatchException ex) {
        return processException("property-mapping-mismatch", BAD_REQUEST, ex);
    }

    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(PropertyValueTypeMismatchException.class)
    public Error handlePropertyValueTypeMismatchException(PropertyValueTypeMismatchException ex) {
        return processException("property-mapping-mismatch", BAD_REQUEST, ex);
    }


    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(PropertyOneToOneMappingMismatchException.class)
    public Error handlePropertyOneToOneMappingMismatchException(PropertyOneToOneMappingMismatchException ex) {
        return processException("property-mapping-mismatch", BAD_REQUEST, ex);
    }

    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public Error handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException ex) {
        return processException("invalid-request-body", BAD_REQUEST, ex);
    }

    @ResponseStatus(NOT_FOUND)
    @ResponseBody
    @ExceptionHandler(NotFoundException.class)
    public Error handleNotFoundException(NotFoundException ex) {
        return processException("resource-not-found", NOT_FOUND, ex);
    }

    private Error processException(String Type, HttpStatus status, Exception ex) {
        return processException(Type, status, ex.getMessage());
    }

    private Error processException(String Type, HttpStatus status, String message) {
        return new Error(Type, status.value(), message);
    }


    private Error processFieldErrors(List<org.springframework.validation.FieldError> fieldErrors) {
        ValidationError validationError = new ValidationError(BAD_REQUEST.value(), "validation error");
        for (org.springframework.validation.FieldError fieldError : fieldErrors) {
            validationError.addFieldError(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return validationError;
    }

    @Getter
    @AllArgsConstructor
    static class Error {
        private final String type;
        private final int status;
        private final String message;
    }

    @Getter
    public static class ValidationError extends Error {


        private final List<FieldErrorSimplified> fieldErrors = new ArrayList<>();

        ValidationError(int status, String message) {
            super("validation-error", status, message);
        }

        public void addFieldError(String path, String message) {
            fieldErrors.add(FieldErrorSimplified.builder().fieldName(path).message(message).build());
        }

        @Getter
        @AllArgsConstructor
        @Builder
        private static class FieldErrorSimplified {
            private String fieldName;
            private String message;
        }
    }
}