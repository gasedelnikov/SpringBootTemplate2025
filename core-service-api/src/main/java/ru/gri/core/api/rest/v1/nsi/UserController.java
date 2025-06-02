package ru.gri.core.api.rest.v1.nsi;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gri.core.model.dto.User;
import ru.gri.core.model.rest.BaseResponse;
import ru.gri.core.security.dto.UserWithSecurity;


@RestController
@RequestMapping("/template/v1/users")
public class UserController {

    @GetMapping("/current")
    @SecurityRequirement(name = "bearerScheme")
    public ResponseEntity<BaseResponse<User>> getUserByToken(@AuthenticationPrincipal final UserWithSecurity user) {
        return new ResponseEntity<>(new BaseResponse<>(user.getUser()), HttpStatus.OK);
    }

}
