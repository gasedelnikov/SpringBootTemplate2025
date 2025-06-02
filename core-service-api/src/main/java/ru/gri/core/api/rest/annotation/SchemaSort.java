package ru.gri.core.api.rest.annotation;


import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Schema(description = "Параметры сортировки. Формат: поля через запятую, '-' перед полем: по убыванию",
        type = "fields",
        example = "-id, field1, -field2")
public @interface SchemaSort {

    @AliasFor(annotation = Schema.class, attribute = "type")
    String fields();
}
