package ru.gri.core.impl.mapper;


import ru.gri.core.dao.model.BaseEntity;
import ru.gri.core.model.dto.in.InputModel;
import ru.gri.core.model.dto.out.OutputModel;

import java.io.Serializable;

public interface BaseMapper<E extends BaseEntity<ID>, ID extends Serializable, I extends InputModel, O extends OutputModel> {

    E toEntity(I inObj);

    O toOutModel(E entity);

    I toInModel(E entity);

    default ID entityToId(E entity) {
        if (entity == null) {
            return null;
        }
        return entity.getId();
    }
}
