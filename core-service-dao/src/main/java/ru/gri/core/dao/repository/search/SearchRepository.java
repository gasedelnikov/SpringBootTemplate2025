package ru.gri.core.dao.repository.search;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import ru.gri.core.dao.model.BaseEntity;

import java.io.Serializable;

@NoRepositoryBean
public interface SearchRepository<T extends BaseEntity<ID>, ID extends Serializable> extends JpaRepository<T, ID> {

    Class<T> getDomainClass();

    void refresh(T entity);
}
