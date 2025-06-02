package ru.gri.core.dao.repository.search.impl;

import jakarta.annotation.Nonnull;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.gri.core.dao.model.BaseEntity;
import ru.gri.core.dao.repository.search.SearchRepository;

import java.io.Serializable;

@Slf4j
public class BaseRepositoryImpl<T extends BaseEntity<ID>, ID extends Serializable>
        extends SimpleJpaRepository<T, ID>
        implements SearchRepository<T, ID> {


    private final EntityManager entityManager;

    public BaseRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
        super(domainClass, entityManager);
        this.entityManager = entityManager;
    }

    public BaseRepositoryImpl(JpaEntityInformation<T, ID> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    @Transactional(readOnly = true)
    public void refresh(T entity) {
        entityManager.refresh(entity);
    }

    @Override
    @Nonnull
    public Class<T> getDomainClass() {
        return super.getDomainClass();
    }

}