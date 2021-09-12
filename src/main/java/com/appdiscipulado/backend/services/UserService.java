package com.appdiscipulado.backend.services;

import com.appdiscipulado.backend.controllers.dto.UserDTO;
import com.appdiscipulado.backend.controllers.exceptions.UserAlreadyExistException;
import com.appdiscipulado.backend.domain.VO.User;
import com.appdiscipulado.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User registerNewUserAccount(UserDTO userDto) throws UserAlreadyExistException {
        if (emailExist(userDto.getEmail())) {
            throw new UserAlreadyExistException("There is an account with that email address: "
              + userDto.getEmail());
        }
        return userDto.convert(userRepository);
    }
    private boolean emailExist(String email) {
        return userRepository.findByEmail(email) != null;
    }
}