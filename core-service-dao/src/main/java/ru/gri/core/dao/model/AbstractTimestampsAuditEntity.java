package ru.gri.core.dao.model;

import jakarta.persistence.Embedded;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode
@ToString
@EntityListeners({AuditingEntityListener.class})
public abstract class AbstractTimestampsAuditEntity {

    @Embedded
    private final TimestampsAuditEntity audit = new TimestampsAuditEntity();

}
