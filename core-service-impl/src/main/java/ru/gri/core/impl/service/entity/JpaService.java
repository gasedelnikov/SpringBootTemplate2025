package ru.gri.core.impl.service.entity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.gri.core.model.dto.in.InputModel;
import ru.gri.core.model.dto.out.OutputModel;

import java.io.Serializable;
import java.util.Optional;

public interface JpaService<ID extends Serializable, I extends InputModel, O extends OutputModel> {

    Optional<O> findById(ID id);

    O getById(ID id);

    Page<O> getPage(Pageable pageable);

    ID create(final I value);

    void update(final I value);

}
