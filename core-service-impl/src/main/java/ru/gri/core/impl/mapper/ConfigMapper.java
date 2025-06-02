package ru.gri.core.impl.mapper;

import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.MapperConfig;

@MapperConfig(componentModel = "spring",
        injectionStrategy = InjectionStrategy.FIELD,
        implementationPackage = "<PACKAGE_NAME>.impl",
        builder = @Builder(disableBuilder = true),
        uses = {AuditMapper.class})
public interface ConfigMapper {
}
