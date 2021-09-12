package com.appdiscipulado.backend.controllers;

import com.appdiscipulado.backend.config.security.TokenService;
import com.appdiscipulado.backend.controllers.dto.TokenDto;
import com.appdiscipulado.backend.controllers.dto.UserDto;
import com.appdiscipulado.backend.domain.VO.User;
import com.appdiscipulado.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<TokenDto> authenticate(@RequestBody @Valid LoginForm form) {
        UsernamePasswordAuthenticationToken loginData = form.convert();

        try {
            final Authentication authentication = authManager.authenticate(loginData);
            final String token = tokenService.generateToken(authentication);

            return ResponseEntity.ok(new TokenDto(token, "Bearer"));
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/newUser")
    @Transactional
    public ResponseEntity<UserDto> createUser(@RequestBody @Valid UserDto userDTO, UriComponentsBuilder uriBuilder) {
        User user = userDTO.convert(userRepository);
        userRepository.save(user);

        URI uri = uriBuilder.path("/newUser/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(new UserDto(user));
    }
}