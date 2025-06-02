package ru.gri.core.impl.service.entity.nsi.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gri.core.dao.model.nsi.AuthorEntity;
import ru.gri.core.dao.repository.entity.nsi.AuthorRepository;
import ru.gri.core.impl.mapper.BaseMapper;
import ru.gri.core.impl.service.entity.BaseServiceImpl;
import ru.gri.core.impl.service.entity.nsi.AuthorService;
import ru.gri.core.model.dto.in.nsi.AuthorIn;
import ru.gri.core.model.dto.out.nsi.AuthorOut;

@Service
public class AuthorServiceImpl extends BaseServiceImpl<AuthorEntity, Long, AuthorIn, AuthorOut>
        implements AuthorService {

    @Autowired
    public AuthorServiceImpl(BaseMapper<AuthorEntity, Long, AuthorIn, AuthorOut> mapper,
                             AuthorRepository repository) {
        super(mapper, repository);
    }

}
