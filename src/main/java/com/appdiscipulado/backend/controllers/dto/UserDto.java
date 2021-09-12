package com.appdiscipulado.backend.controllers.dto;

import com.appdiscipulado.backend.domain.VO.User;
import com.appdiscipulado.backend.domain.VO.UserProfile;
import com.appdiscipulado.backend.domain.usefulEntities.PasswordMatches;
import com.appdiscipulado.backend.domain.usefulEntities.ValidEmail;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@PasswordMatches
public class UserDto {
    @NotNull
    @NotEmpty
    private String name;
    @ValidEmail
    @NotNull
    @NotEmpty
    private String email;
    @NotNull
    @NotEmpty
    private String password;
    @NotNull
    @NotEmpty
    private String matchingPassword;
    @NotNull
    @NotEmpty
    private UserProfile profile;

    public UserDto(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.profile = user.getUserProfile();
    }
}
