package ru.gri.core.model.dto.out;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@SuperBuilder(toBuilder = true)
public class BaseOut {

    private AuditOut audit;

}
