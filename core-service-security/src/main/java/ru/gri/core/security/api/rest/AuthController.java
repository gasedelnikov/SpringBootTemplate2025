package ru.gri.core.security.api.rest;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gri.core.model.rest.BaseResponse;
import ru.gri.core.security.dto.JwtAuthenticationResponse;
import ru.gri.core.security.dto.JwtLoginRequest;
import ru.gri.core.security.dto.UserWithSecurity;
import ru.gri.core.security.service.impl.AuthenticationServiceImpl;


@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationServiceImpl authenticationService;

    @Autowired
    public AuthController(AuthenticationServiceImpl authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<BaseResponse<JwtAuthenticationResponse>> login(@RequestBody final JwtLoginRequest request) {
        return new ResponseEntity<>(new BaseResponse<>(authenticationService.login(request)), HttpStatus.OK);
    }

    @PostMapping("/refresh")
    @SecurityRequirement(name = "bearerScheme")
    public ResponseEntity<BaseResponse<JwtAuthenticationResponse>> refresh(@NonNull HttpServletRequest request,
                                                                           @AuthenticationPrincipal final UserWithSecurity user) {
        return new ResponseEntity<>(new BaseResponse<>(authenticationService.refreshToken(request, user)), HttpStatus.OK);
    }

}
