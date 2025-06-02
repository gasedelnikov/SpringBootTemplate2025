package ru.gri.core.model.dto.in.nsi;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import ru.gri.core.model.dto.in.InputModel;
import ru.gri.core.model.enums.AuthorType;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthorIn implements InputModel {

    private Long id;
    private AuthorType type;
    private String fio;
    private String description;

}
