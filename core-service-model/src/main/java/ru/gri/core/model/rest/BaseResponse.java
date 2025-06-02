package ru.gri.core.model.rest;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@ToString(callSuper = true)
public class BaseResponse<T> {

    private ResponseStatus status;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private T data;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private ErrorResponse error;

    public BaseResponse(ResponseStatus status, T data) {
        this.status = status;
        this.data = data;
    }

    public BaseResponse(T data) {
        this(ResponseStatus.OK, data);
    }

    public BaseResponse(ErrorResponse error) {
        this.status = ResponseStatus.ERROR;
        this.error = error;
    }

    public BaseResponse(String code, String message) {
        this(new ErrorResponse(code, message));
    }

    public BaseResponse(String code, String message, Map<String, String> params) {
        this(new ErrorResponse(code, message, params));
    }

}
