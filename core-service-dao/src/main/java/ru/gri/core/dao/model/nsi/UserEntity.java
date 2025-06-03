package ru.gri.core.dao.model.nsi;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Type;
import ru.gri.core.dao.model.AbstractTimestampsAndUsersAuditEntity;
import ru.gri.core.dao.model.BaseEntity;
import ru.gri.core.dao.type.StringEnumSqlUserType;
import ru.gri.core.model.enums.UserStatus;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
@ToString
@Entity
@Table(schema = "public", name = "usr", uniqueConstraints = {@UniqueConstraint(name = "user_pk", columnNames = {"id"})})
public class UserEntity extends AbstractTimestampsAndUsersAuditEntity implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq_generator")
    @SequenceGenerator(schema = "public", name = "user_id_seq_generator", sequenceName = "user_id_seq", allocationSize = 1)
    @Column(name = "id", columnDefinition = "SERIAL", nullable = false, updatable = false, unique = true)
    private Long id;

    @Column(name = "login", columnDefinition = "varchar", nullable = false)
    private String login;

    @Column(name = "description", columnDefinition = "varchar", nullable = false)
    private String description;

    @Column(name = "status", columnDefinition = "enum", nullable = false)
    @Type(value = StringEnumSqlUserType.class)
    private String status;
    public UserStatus getStatus() {
        return (status == null) ? null : UserStatus.getByValue(status);
    }
    public void setStatus(UserStatus status) {
        this.status = (status == null) ? null : status.value();
    }

    @Column(name = "password", columnDefinition = "varchar", nullable = false)
    private String password;

}
