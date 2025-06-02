package ru.gri.core.dao.model;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import ru.gri.core.dao.converter.TimeStampToLongConverter;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode
@ToString
@Embeddable
@EntityListeners({AuditingEntityListener.class})
public class TimestampsAuditEntity {

    @CreatedDate
    @Column(name = "created_ts", columnDefinition = "timestamp with time zone", nullable = false, updatable = false)
    @Convert(converter = TimeStampToLongConverter.class)
    private Long createdTs;

    @Column(name = "updated_ts", columnDefinition = "timestamp with time zone")
    @Convert(converter = TimeStampToLongConverter.class)
    @LastModifiedDate
    private Long updatedTs;

}
