package com.appdiscipulado.backend.controllers.dto;

import com.appdiscipulado.backend.domain.Profile;
import com.appdiscipulado.backend.domain.VO.User;
import com.appdiscipulado.backend.domain.usefulEntities.PasswordMatches;
import com.appdiscipulado.backend.domain.usefulEntities.ValidEmail;
import com.appdiscipulado.backend.repositories.UserRepository;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Optional;

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
    private Profile profile;

    public UserDto(User user) {
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.profile = user.getProfiles().get(1);
    }

    public User convert(UserRepository userRepository) {
        Optional<User> user = userRepository.findByEmail(email);
        if(!user.isPresent()) {
            return new User(null, name, email, password, Arrays.asList(profile));
        }
       throw new IllegalArgumentException("Dados incorretos para cadastro");
    }
}
