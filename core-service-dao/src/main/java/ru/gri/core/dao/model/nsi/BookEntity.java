package ru.gri.core.dao.model.nsi;


import com.fasterxml.jackson.databind.JsonNode;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import ru.gri.core.dao.model.AbstractTimestampsAndUsersAuditEntity;
import ru.gri.core.dao.model.BaseEntity;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
@ToString(exclude = {"authorEntity"})
@Entity
@Table(schema = "public", name = "book",
        uniqueConstraints = {@UniqueConstraint(name = "book_pk", columnNames = {"id"})})
public class BookEntity extends AbstractTimestampsAndUsersAuditEntity implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_id_seq_generator")
    @SequenceGenerator(schema = "public", name = "book_id_seq_generator",
            sequenceName = "book_id_seq", allocationSize = 1)
    @Column(name = "id", columnDefinition = "SERIAL", nullable = false, updatable = false, unique = true)
    private Long id;

    @Column(name = "author_id", columnDefinition = "BIGINT", nullable = false)
    private Long authorId;

    @Column(name = "name", columnDefinition = "varchar", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", updatable = false, insertable = false)
    private AuthorEntity authorEntity;

    @Column(name = "description", columnDefinition = "varchar")
    private String description;

    @Column(name = "info", columnDefinition = "JSONB")
    @JdbcTypeCode(SqlTypes.JSON)
    private JsonNode info;

}
