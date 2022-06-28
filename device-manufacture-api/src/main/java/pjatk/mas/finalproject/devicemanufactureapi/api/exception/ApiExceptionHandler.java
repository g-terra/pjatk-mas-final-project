package pjatk.mas.finalproject.devicemanufactureapi.api.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pjatk.mas.finalproject.devicemanufactureapi.domain.exceptions.*;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * Global exception handler for API.
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@RequiredArgsConstructor
public class ApiExceptionHandler{
    
    private final ApiErrorProcessor apiErrorProcessor;


    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ApiError httpMessageNotReadableException(HttpMessageNotReadableException ex){
          return apiErrorProcessor.processException("Invalid request body", BAD_REQUEST, ex.getMessage());
    }

    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiError methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<org.springframework.validation.FieldError> fieldErrors = result.getFieldErrors();
        return apiErrorProcessor.processFieldErrors(fieldErrors);
    }

    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ApiError handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException ex) {
        return apiErrorProcessor.processException("constraint-violation", BAD_REQUEST, ex);
    }

    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(FunctionalityNameAlreadyTakenException.class)
    public ApiError handleFunctionalityNameAlreadyTakenException(FunctionalityNameAlreadyTakenException ex) {
        return apiErrorProcessor.processException("functionality-already-exists", BAD_REQUEST, ex);
    }

    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(PropertyValueMappingMismatchException.class)
    public ApiError handlePropertyValueMappingMismatchException(PropertyValueMappingMismatchException ex) {
        return apiErrorProcessor.processException("property-mapping-mismatch", BAD_REQUEST, ex);
    }

    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(PropertyValueTypeMismatchException.class)
    public ApiError handlePropertyValueTypeMismatchException(PropertyValueTypeMismatchException ex) {
        return apiErrorProcessor.processException("property-mapping-mismatch", BAD_REQUEST, ex);
    }


    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(PropertyOneToOneMappingMismatchException.class)
    public ApiError handlePropertyOneToOneMappingMismatchException(PropertyOneToOneMappingMismatchException ex) {
        return apiErrorProcessor.processException("property-mapping-mismatch", BAD_REQUEST, ex);
    }

    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ApiError handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException ex) {
        return apiErrorProcessor.processException("invalid-request-body", BAD_REQUEST, ex);
    }

    @ResponseStatus(NOT_FOUND)
    @ResponseBody
    @ExceptionHandler(NotFoundException.class)
    public ApiError handleNotFoundException(NotFoundException ex) {
        return apiErrorProcessor.processException("resource-not-found", NOT_FOUND, ex);
    }

    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(DuplicatedPropertyNameException.class)
    public ApiError handleDuplicatedPropertyNameException(DuplicatedPropertyNameException ex) {
        return apiErrorProcessor.processException("duplicated", NOT_FOUND, ex);
    }


}