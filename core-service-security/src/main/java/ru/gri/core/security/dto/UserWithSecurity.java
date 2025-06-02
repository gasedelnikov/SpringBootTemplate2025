package ru.gri.core.security.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.gri.core.model.dto.User;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserWithSecurity implements UserDetails {

    @JsonIgnore
    private User user;

    public UserWithSecurity(User user) {
        this.user = user;
    }

    public Long getId() {
        return user.getId();
    }

    @Override
    public String getUsername() {
        return user.getLogin();
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @JsonIgnore
    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
