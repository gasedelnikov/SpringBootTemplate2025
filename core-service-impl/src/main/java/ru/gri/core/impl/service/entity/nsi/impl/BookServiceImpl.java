package ru.gri.core.impl.service.entity.nsi.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gri.core.dao.model.nsi.BookEntity;
import ru.gri.core.dao.repository.entity.nsi.BookRepository;
import ru.gri.core.impl.mapper.nsi.BookMapper;
import ru.gri.core.impl.service.entity.BaseServiceImpl;
import ru.gri.core.impl.service.entity.nsi.BookService;
import ru.gri.core.model.dto.in.nsi.BookIn;
import ru.gri.core.model.dto.out.nsi.BookOut;

@Service
public class BookServiceImpl extends BaseServiceImpl<BookEntity, Long, BookIn, BookOut>
        implements BookService {

    @Autowired
    public BookServiceImpl(BookMapper mapper, BookRepository repository) {
        super(mapper, repository);
    }

}
