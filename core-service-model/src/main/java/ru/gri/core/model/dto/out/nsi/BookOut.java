package ru.gri.core.model.dto.out.nsi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import ru.gri.core.model.dto.out.BaseOut;
import ru.gri.core.model.dto.out.OutputModel;


@Getter
@Setter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookOut extends BaseOut implements OutputModel {

    private Long id;
    private Long authorId;
    private AuthorOut author;
    private String name;
    private String description;

}
