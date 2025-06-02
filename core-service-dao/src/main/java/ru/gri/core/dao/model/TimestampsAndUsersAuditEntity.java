package ru.gri.core.dao.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import ru.gri.core.dao.model.nsi.UserEntity;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
@ToString(exclude = {"createdUserEntity", "updatedUserEntity"})
@Embeddable
@EntityListeners({AuditingEntityListener.class})
public class TimestampsAndUsersAuditEntity extends TimestampsAuditEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_user_id", insertable = false, updatable = false)
    private UserEntity createdUserEntity;

    @Column(name = "created_user_id", nullable = false, updatable = false)
    @LastModifiedBy
    private Long createdUserId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updated_user_id", insertable = false, updatable = false)
    private UserEntity updatedUserEntity;

    @Column(name = "updated_user_id")
    @LastModifiedBy
    private Long updatedUserId;

}
