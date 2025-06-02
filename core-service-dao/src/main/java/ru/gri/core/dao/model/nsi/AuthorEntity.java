package ru.gri.core.dao.model.nsi;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
import ru.gri.core.model.enums.AuthorType;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@ToString(exclude = {"bookEntities"})
@Table(schema = "public", name = "author", uniqueConstraints = {@UniqueConstraint(name = "author_pk", columnNames = {"id"})})
public class AuthorEntity extends AbstractTimestampsAndUsersAuditEntity implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "author_id_seq_generator")
    @SequenceGenerator(schema = "public", name = "author_id_seq_generator", sequenceName = "author_id_seq", allocationSize = 1)
    @Column(name = "id", columnDefinition = "SERIAL", nullable = false, updatable = false, unique = true)
    private Long id;

    @Column(name = "type", columnDefinition = "enum", nullable = false)
    @Type(value = StringEnumSqlUserType.class)
    private String type;
    @Column(name = "fio", columnDefinition = "varchar", nullable = false)
    private String fio;
    @Column(name = "description", columnDefinition = "varchar", nullable = false)
    private String description;
    @OneToMany(mappedBy = "authorEntity", fetch = FetchType.LAZY)
    private List<BookEntity> bookEntities;

    public AuthorType getType() {
        return (type == null) ? null : AuthorType.getByValue(type);
    }

    public void setType(AuthorType type) {
        this.type = (type == null) ? null : type.value();
    }

}
