package ru.gri.core.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import ru.gri.core.model.error.CustomExceptionMessage;
import ru.gri.core.model.exception.CustomException;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum UserStatus {

    CREATED("created"),
    REGISTERED("registered"),
    DELETED("deleted"),
    ;

    private static final Map<String, UserStatus> LOOK_UP_MAP =
            Arrays.stream(values()).collect(Collectors.toMap(UserStatus::value, Function.identity()));
    private final String value;

    UserStatus(String value) {
        this.value = value;
    }

    public static UserStatus getByValue(String value) {
        return Optional.ofNullable(value).map((v) -> LOOK_UP_MAP.get(value))
                .orElseThrow(() -> new CustomException(CustomExceptionMessage.UNKNOWN_ENUM_VALUE_ERROR,
                        Map.of("Unknown enum value", value == null ? "null" : value)));
    }

    @JsonValue
    public String value() {
        return value;
    }
}

