package ru.gri.core.model.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Map;


@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class ErrorResponse {

    private String code;
    private String message;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Map<String, String> params;

    public ErrorResponse(String code, String message) {
        this(code, message, null);
    }

    public ErrorResponse(String code, String message, Map<String, String> params) {
        this.code = code;
        this.message = message;
        this.params = params;
    }
}
