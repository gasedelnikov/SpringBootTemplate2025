package ru.gri.core.impl.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.gri.core.dao.model.TimestampsAndUsersAuditEntity;
import ru.gri.core.dao.model.TimestampsAuditEntity;
import ru.gri.core.impl.mapper.nsi.UserAuditMapper;
import ru.gri.core.model.dto.out.AuditOut;

@Mapper(config = ConfigMapper.class, uses = {UserAuditMapper.class})
public interface AuditMapper {

    @Mappings(value = {
            @Mapping(target = "createdUser", source = "createdUserEntity"),
            @Mapping(target = "updatedUser", source = "updatedUserEntity")
    })
    AuditOut toOutModel(TimestampsAndUsersAuditEntity entity);

    @Mappings(value = {
            @Mapping(target = "createdUser", ignore = true),
            @Mapping(target = "updatedUser", ignore = true),
    })
    AuditOut toOutModel(TimestampsAuditEntity entity);

}
