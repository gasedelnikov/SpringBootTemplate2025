package ru.gri.core.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import ru.gri.core.model.error.CustomExceptionMessage;
import ru.gri.core.model.exception.CustomException;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum AuthorType {

    A1("a1"),
    A2("a2"),
    B1("b1"),
    B2("b2"),
    ;

    private static final Map<String, AuthorType> LOOK_UP_MAP =
            Arrays.stream(values()).collect(Collectors.toMap(AuthorType::value, Function.identity()));
    private final String value;

    AuthorType(String value) {
        this.value = value;
    }

    public static AuthorType getByValue(String value) {
        return Optional.ofNullable(value).map((v) -> LOOK_UP_MAP.get(value))
                .orElseThrow(() -> new CustomException(CustomExceptionMessage.UNKNOWN_ENUM_VALUE_ERROR,
                        Map.of("Unknown enum value", value == null ? "null" : value)));
    }

    @JsonValue
    public String value() {
        return value;
    }
}

