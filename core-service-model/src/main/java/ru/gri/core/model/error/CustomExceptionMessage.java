package ru.gri.core.model.error;

import org.springframework.http.HttpStatus;

public enum CustomExceptionMessage {

    SOME_ERROR("E-0001", "Some error", HttpStatus.INTERNAL_SERVER_ERROR),
    FEIGN_ERROR("E-0004", "Integration http request error", HttpStatus.INTERNAL_SERVER_ERROR),
    FEIGN_AUTHORIZATION_ERROR("E-0005", "Integration authorization error", HttpStatus.INTERNAL_SERVER_ERROR),
    GET_AUDITOR_ID_ERROR("E-0008", "Get auditorId error", HttpStatus.INTERNAL_SERVER_ERROR),

    FIELD_MAPPING_ERROR("V-0001", "Property not found", HttpStatus.BAD_REQUEST),
    FIELD_VALIDATION_ERROR("V-0002", "Property validation error", HttpStatus.BAD_REQUEST),
    AGE_CATEGORY_VALIDATION_ERROR("V-0006", "AgeCategory validation error", HttpStatus.BAD_REQUEST),
    FILTER_OPERATION_FOUND_ERROR("V-0007", "Filter operation not supported", HttpStatus.BAD_REQUEST),
    FILTER_UNSUPPORTED_FIELD_ERROR("V-0008", "Unsupported filter field", HttpStatus.BAD_REQUEST),
    DATA_INTEGRITY_VIOLATION_EXCEPTION("V-0009", "Data Integrity Violation Exception", HttpStatus.BAD_REQUEST),
    UNKNOWN_ENUM_VALUE_ERROR("V-0010", "Unknown enum value error", HttpStatus.BAD_REQUEST),

    BAD_REQUEST("R-0000", "Bad request", HttpStatus.BAD_REQUEST),
    NOT_FOUND("R-0001", "Not found", HttpStatus.NOT_FOUND),
    USER_NOT_FOUND("R-0002", "User not found", HttpStatus.NOT_FOUND),
    BAD_CREDENTIALS("R-0003", "Bad credentials", HttpStatus.UNAUTHORIZED),
    HTTP_ENDPOINT_NOT_FOUND("R-0005", "Endpoint not found", HttpStatus.NOT_FOUND),
    HTTP_METHOD_NOT_ALLOWED("R-0006", "Method not allowed", HttpStatus.METHOD_NOT_ALLOWED),
    UNEXPECTED_NOT_NULL_ENTITY_ID_ON_CREATE("R-0007", "Unexpected not null entity id", HttpStatus.BAD_REQUEST),
    MULTIPLE_IDS_IS_NOT_SUPPORTED("R-0009", "Multiple ids is not supported", HttpStatus.BAD_REQUEST),
    ;

    private final String code;
    private final String message;
    private final HttpStatus httpStatus;


    CustomExceptionMessage(String code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public String code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }

    public HttpStatus httpStatus() {
        return this.httpStatus;
    }

}
