package ru.gri.core.model.exception;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import ru.gri.core.model.error.CustomExceptionMessage;

import java.io.Serial;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public class CustomException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -3422897190745756933L;

    private final String errorCode;
    private final Map<String, ?> params;
    private final HttpStatus httpStatus;

    private CustomException(String errorCode, String message, Map<String, ?> params, HttpStatus httpStatus) {
        super(message);
        this.errorCode = errorCode;
        this.params = params;
        this.httpStatus = httpStatus;
    }

    public CustomException(String errorCode, String message) {
        this(errorCode, message, null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public CustomException(CustomExceptionMessage message) {
        this(message.code(), message.message(), null, message.httpStatus());
    }

    public CustomException(CustomExceptionMessage message, Map<String, ?> params) {
        this(message.code(), message.message(), params, message.httpStatus());
    }

    public HashMap<String, String> getParams() {
        return (params == null) ? null : convertParamsMap(params);
    }

    private HashMap<String, String> convertParamsMap(Map<String, ?> params) {
        return params.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> {
                            if (entry.getValue() instanceof Collection) {
                                return ((Collection<?>) entry.getValue()).stream()
                                        .map(String::valueOf)
                                        .collect(Collectors.joining(","));
                            } else return String.valueOf(entry.getValue());
                        },
                        (v1, v2) -> {
                            throw new IllegalStateException("Duplicate key");
                        },
                        HashMap::new));
    }

    public JsonNode asJsonNode(ObjectMapper objectMapper) {
        ObjectNode result = objectMapper.createObjectNode();
        result.put("errorCode", errorCode);
        result.put("message", super.getMessage());

        JsonNode paramsNode = objectMapper.valueToTree(params);
        result.putIfAbsent("data", paramsNode);

        return result;
    }
}
