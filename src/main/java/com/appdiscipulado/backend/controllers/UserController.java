package com.appdiscipulado.backend.controllers;

import com.appdiscipulado.backend.controllers.dto.UserDto;
import com.appdiscipulado.backend.domain.VO.User;
import com.appdiscipulado.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    @Transactional
    public ResponseEntity<UserDto> createUser(@RequestBody @Valid UserDto userDTO, UriComponentsBuilder uriBuilder) {
        final User user = userService.registerNewUserAccount(userDTO);

        if(user!=null) {
            URI uri = uriBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri();
            return ResponseEntity.created(uri).body(new UserDto(user));
        }
        else return ResponseEntity.noContent().build();
    }
}
