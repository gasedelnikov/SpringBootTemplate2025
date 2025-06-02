package ru.gri.core.impl.mapper.nsi;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.gri.core.dao.model.nsi.AuthorEntity;
import ru.gri.core.dao.model.nsi.BookEntity;
import ru.gri.core.impl.mapper.BaseMapper;
import ru.gri.core.impl.mapper.ConfigMapper;
import ru.gri.core.model.dto.in.nsi.AuthorIn;
import ru.gri.core.model.dto.out.nsi.AuthorOut;
import ru.gri.core.model.dto.out.nsi.BookOut;

@Mapper(config = ConfigMapper.class)
public interface AuthorMapper extends BaseMapper<AuthorEntity, Long, AuthorIn, AuthorOut> {

    @Mappings(value = {
            @Mapping(target = "bookEntities", ignore = true),
    })
    @Override
    AuthorEntity toEntity(AuthorIn inObj);

    @Mappings(value = {
            @Mapping(target = "books", source = "bookEntities"),
    })
    @Override
    AuthorOut toOutModel(AuthorEntity entity);

    @Override
    AuthorIn toInModel(AuthorEntity entity);

    @Mappings(value = {
            @Mapping(target = "audit", ignore = true),
            @Mapping(target = "author", ignore = true),
    })
    BookOut toOutModel(BookEntity destination);
}
