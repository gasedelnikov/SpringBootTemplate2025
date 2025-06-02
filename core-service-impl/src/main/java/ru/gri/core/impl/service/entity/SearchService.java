package ru.gri.core.impl.service.entity;

import ru.gri.core.model.dto.out.OutputModel;

import java.io.Serializable;

public interface SearchService<ID extends Serializable, O extends OutputModel> {

    O getById(ID id);

}
