package ru.gri.core.impl.mapper.nsi;

import org.mapstruct.Mapper;
import ru.gri.core.dao.model.nsi.UserEntity;
import ru.gri.core.impl.mapper.ConfigMapper;
import ru.gri.core.model.dto.out.nsi.UserAuditOut;


@Mapper(config = ConfigMapper.class)
public interface UserAuditMapper {

    UserAuditOut toOutModel(UserEntity entity);

}
