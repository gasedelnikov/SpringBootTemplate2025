package ru.gri.core.impl.service.entity.nsi;

import ru.gri.core.impl.service.entity.JpaService;
import ru.gri.core.impl.service.entity.SearchService;
import ru.gri.core.model.dto.in.nsi.BookIn;
import ru.gri.core.model.dto.out.nsi.BookOut;

public interface BookService extends SearchService<Long, BookOut>, JpaService<Long, BookIn, BookOut> {

}
