package ru.gri.core.impl.service.entity.nsi;

import ru.gri.core.impl.service.entity.JpaService;
import ru.gri.core.impl.service.entity.SearchService;
import ru.gri.core.model.dto.in.nsi.AuthorIn;
import ru.gri.core.model.dto.out.nsi.AuthorOut;

public interface AuthorService extends SearchService<Long, AuthorOut>, JpaService<Long, AuthorIn, AuthorOut> {

}