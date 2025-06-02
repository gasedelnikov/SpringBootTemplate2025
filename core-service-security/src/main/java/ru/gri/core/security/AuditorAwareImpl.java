package ru.gri.core.security;

import org.springframework.data.domain.AuditorAware;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import ru.gri.core.security.dto.UserWithSecurity;

import java.util.Optional;

@Component
public class AuditorAwareImpl implements AuditorAware<Long> {
    private static final Long DEFAULT_AUDITOR_ID = 1L;

    @Override
    @NonNull
    public Optional<Long> getCurrentAuditor() {
        Long securityContextAuditorId = getSecurityContextAuditorId();
        Long result = Optional.ofNullable(securityContextAuditorId).orElse(DEFAULT_AUDITOR_ID);

        return Optional.of(result);
    }

    public Long getSecurityContextAuditorId() {
        return Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .map(Authentication::getPrincipal)
                .filter(p -> p instanceof UserWithSecurity)
                .map(p -> ((UserWithSecurity) p).getId())
                .orElse(null);
    }

}
