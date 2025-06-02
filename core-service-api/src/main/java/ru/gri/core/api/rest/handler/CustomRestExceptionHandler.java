package ru.gri.core.api.rest.handler;


import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.servlet.NoHandlerFoundException;
import ru.gri.core.model.error.CustomExceptionMessage;
import ru.gri.core.model.exception.CustomException;
import ru.gri.core.model.exception.CustomSyncException;
import ru.gri.core.model.rest.BaseResponse;

import java.util.Map;
import java.util.Optional;

@ControllerAdvice
@Slf4j
public class CustomRestExceptionHandler {

    @ExceptionHandler({Exception.class})
    public ResponseEntity<BaseResponse<Object>> handleException(Exception exception) {
        String message = logException(exception);
        return returnCustomException(CustomExceptionMessage.SOME_ERROR, message);
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResponseEntity<BaseResponse<Object>> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception) {
        String message = logException(exception);
        return returnCustomException(CustomExceptionMessage.BAD_REQUEST, message);
    }

    @ExceptionHandler({NoHandlerFoundException.class})
    public ResponseEntity<BaseResponse<Object>> handleNoHandlerFoundException(NoHandlerFoundException exception) {
        String message = logException(exception);
        return returnCustomException(CustomExceptionMessage.HTTP_ENDPOINT_NOT_FOUND, message);
    }

    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public ResponseEntity<BaseResponse<Object>> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException exception) {
        String message = logException(exception);
        return returnCustomException(CustomExceptionMessage.HTTP_METHOD_NOT_ALLOWED, message);
    }

    @ExceptionHandler({DataIntegrityViolationException.class})
    public ResponseEntity<BaseResponse<Object>> handleException(DataIntegrityViolationException exception) {
        String message = logException(exception);
        return returnCustomException(CustomExceptionMessage.DATA_INTEGRITY_VIOLATION_EXCEPTION, message);
    }

    @ExceptionHandler({MissingServletRequestParameterException.class})
    public ResponseEntity<BaseResponse<Object>> handleMissingServletRequestParameterException(Exception exception) {
        String message = logException(exception);
        return returnCustomException(CustomExceptionMessage.FIELD_MAPPING_ERROR, message);
    }

    @ExceptionHandler({FeignException.class})
    public ResponseEntity<BaseResponse<Object>> handleFeignException(FeignException exception) {
        String message = logException(exception);

        if (exception.status() == 401) {
            return returnCustomException(CustomExceptionMessage.FEIGN_AUTHORIZATION_ERROR, message);
        } else {
            return returnCustomException(CustomExceptionMessage.FEIGN_ERROR, message);
        }
    }

    @ExceptionHandler({IllegalArgumentException.class, HandlerMethodValidationException.class})
    public ResponseEntity<BaseResponse<Object>> handleIllegalArgumentException(Exception exception) {
        String message = logException(exception);
        BaseResponse<Object> em = new BaseResponse<>(
                CustomExceptionMessage.FIELD_VALIDATION_ERROR.code(),
                CustomExceptionMessage.FIELD_VALIDATION_ERROR.message(),
                Map.of("detailMessage", message));
        HttpStatus status = CustomExceptionMessage.FIELD_VALIDATION_ERROR.httpStatus();
        return ResponseEntity.status(status).body(em);
    }

    @ExceptionHandler({PropertyReferenceException.class})
    public ResponseEntity<BaseResponse<Object>> handlePropertyReferenceException(PropertyReferenceException exception) {
        logException(exception);
        BaseResponse<Object> em = new BaseResponse<>(
                CustomExceptionMessage.FIELD_MAPPING_ERROR.code(),
                CustomExceptionMessage.FIELD_MAPPING_ERROR.message(),
                Map.of("propertyName", exception.getPropertyName(),
                        "type", exception.getType().toString()));
        HttpStatus status = CustomExceptionMessage.FIELD_MAPPING_ERROR.httpStatus();
        return ResponseEntity.status(status).body(em);
    }

    @ExceptionHandler({CustomException.class})
    public ResponseEntity<BaseResponse<Object>> handleCustomException(CustomException exception) {
        String message = logException(exception);
        BaseResponse<Object> em = new BaseResponse<>(exception.getErrorCode(), message, exception.getParams());
        HttpStatus status = exception.getHttpStatus();
        return ResponseEntity.status(status).body(em);
    }

    @ExceptionHandler({CustomSyncException.class})
    public ResponseEntity<BaseResponse<Object>> handleCustomValidationException(CustomSyncException exception) {
        String message = logException(exception);
        BaseResponse<Object> em = new BaseResponse<>(exception.getErrorCode(), message, exception.getParams());
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        return ResponseEntity.status(status).body(em);
    }

    @ExceptionHandler({InternalAuthenticationServiceException.class})
    public ResponseEntity<BaseResponse<Object>> handleInternalAuthenticationServiceException(InternalAuthenticationServiceException exception) {
        logException(exception);
        if (exception.getCause() instanceof CustomException customException) {
            return handleCustomException(customException);
        } else {
            return handleException(exception);
        }
    }

    @ExceptionHandler({BadCredentialsException.class})
    public ResponseEntity<BaseResponse<Object>> handleBadCredentialsException(BadCredentialsException exception) {
        logException(exception);
        BaseResponse<Object> em = new BaseResponse<>(CustomExceptionMessage.BAD_CREDENTIALS.code(),
                CustomExceptionMessage.BAD_CREDENTIALS.message());
        HttpStatus status = CustomExceptionMessage.BAD_CREDENTIALS.httpStatus();
        return ResponseEntity.status(status).body(em);
    }

    private String logException(Exception exception) {
        String message = Optional.ofNullable(exception.getMessage()).orElse(exception.toString());

        log.error("Exception: {}", message);
        return message;
    }

    private ResponseEntity<BaseResponse<Object>> returnCustomException(CustomExceptionMessage exception, String detailMessage) {
        return returnCustomException(exception, Map.of("detailMessage", String.valueOf(detailMessage)));
    }

    private ResponseEntity<BaseResponse<Object>> returnCustomException(CustomExceptionMessage exception, Map<String, String> params) {
        BaseResponse<Object> em = new BaseResponse<>(exception.code(), exception.message(), params);
        HttpStatus status = exception.httpStatus();
        return ResponseEntity.status(status).body(em);
    }
}
