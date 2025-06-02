package ru.gri.core.impl.mapper.nsi;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.gri.core.dao.model.nsi.BookEntity;
import ru.gri.core.impl.mapper.BaseMapper;
import ru.gri.core.impl.mapper.ConfigMapper;
import ru.gri.core.model.dto.in.nsi.BookIn;
import ru.gri.core.model.dto.out.nsi.BookOut;

@Mapper(config = ConfigMapper.class, uses = {AuthorMapper.class})
public interface BookMapper extends BaseMapper<BookEntity, Long, BookIn, BookOut> {

    @Override
    @Mappings(value = {
            @Mapping(target = "authorEntity", ignore = true),
    })
    BookEntity toEntity(BookIn source);

    @Override
    @Mappings(value = {
            @Mapping(target = "author", source = "authorEntity"),
            @Mapping(target = "author.audit", ignore = true),
            @Mapping(target = "author.books", ignore = true),
    })
    BookOut toOutModel(BookEntity destination);

    @Override
    BookIn toInModel(BookEntity destination);

}
