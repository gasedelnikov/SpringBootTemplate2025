package ru.gri.core.impl.mapper.nsi;

import org.mapstruct.Mapper;
import ru.gri.core.dao.model.nsi.UserEntity;
import ru.gri.core.impl.mapper.BaseMapper;
import ru.gri.core.impl.mapper.ConfigMapper;
import ru.gri.core.model.dto.User;
import ru.gri.core.model.dto.out.nsi.UserOut;


@Mapper(config = ConfigMapper.class)
public interface UserMapper extends BaseMapper<UserEntity, Long, User, UserOut> {

    @Override
    UserEntity toEntity(User source);

    @Override
    UserOut toOutModel(UserEntity destination);

}
