package ru.gri.core.model.dto.out;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import ru.gri.core.model.dto.out.nsi.UserAuditOut;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@SuperBuilder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuditOut {

    private Long createdTs;
    private UserAuditOut createdUser;

    private Long updatedTs;
    private UserAuditOut updatedUser;
}
