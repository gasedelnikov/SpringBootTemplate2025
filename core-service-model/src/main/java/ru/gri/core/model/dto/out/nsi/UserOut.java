package ru.gri.core.model.dto.out.nsi;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import ru.gri.core.model.dto.out.BaseOut;
import ru.gri.core.model.dto.out.OutputModel;
import ru.gri.core.model.enums.UserStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserOut extends BaseOut implements OutputModel {

    private Long id;

    private String login;
    private String description;
    private UserStatus status;

}
