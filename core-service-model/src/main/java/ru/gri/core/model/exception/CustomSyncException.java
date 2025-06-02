package ru.gri.core.model.exception;

import lombok.Getter;
import ru.gri.core.model.error.CustomExceptionMessage;

import java.io.Serial;
import java.util.Map;

@Getter
public class CustomSyncException extends CustomException {
    @Serial
    private static final long serialVersionUID = -1022897190745766933L;

    private final String traceId;

    public CustomSyncException(String traceId, CustomExceptionMessage message, Map<String, ?> params) {
        super(message, params);
        this.traceId = traceId;
    }

}