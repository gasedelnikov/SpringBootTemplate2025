package ru.gri.core.impl.service.entity;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import ru.gri.core.dao.model.BaseEntity;
import ru.gri.core.dao.repository.search.SearchRepository;
import ru.gri.core.impl.mapper.BaseMapper;
import ru.gri.core.model.dto.in.InputModel;
import ru.gri.core.model.dto.out.OutputModel;
import ru.gri.core.model.error.CustomExceptionMessage;
import ru.gri.core.model.exception.CustomException;

import java.io.Serializable;
import java.util.Map;
import java.util.Optional;


public class BaseServiceImpl<E extends BaseEntity<ID>, ID extends Serializable, I extends InputModel, O extends OutputModel>
        implements SearchService<ID, O>, JpaService<ID, I, O> {

    protected final BaseMapper<E, ID, I, O> mapper;
    protected final SearchRepository<E, ID> repository;

    public BaseServiceImpl(BaseMapper<E, ID, I, O> mapper,
                           SearchRepository<E, ID> repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<O> findById(ID id) {
        return repository.findById(id).map(mapper::toOutModel);
    }

    @Override
    @Transactional(readOnly = true)
    public O getById(ID id) {
        E entity = repository.findById(id)
                .orElseThrow(() -> new CustomException(CustomExceptionMessage.NOT_FOUND,
                        Map.of("object", repository.getDomainClass().getSimpleName(), "id", id)));
        return mapper.toOutModel(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<O> getPage(Pageable pageable) {
        Page<E> pageResult = repository.findAll(pageable);

        return pageResult.map(mapper::toOutModel);
    }


    @Override
    @Transactional
    public ID create(final I value) {
        E entity = mapper.toEntity(value);
        if (entity.getId() != null) {
            throw new CustomException(CustomExceptionMessage.UNEXPECTED_NOT_NULL_ENTITY_ID_ON_CREATE);
        }
        repository.save(entity);
        return entity.getId();
    }

    @Override
    @Transactional
    public void update(final I value) {
        E entity = mapper.toEntity(value);
        repository.save(entity);
    }

}
