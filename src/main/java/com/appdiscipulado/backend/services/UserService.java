package com.appdiscipulado.backend.services;

import com.appdiscipulado.backend.controllers.dto.UserDto;
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

    public User registerNewUserAccount(UserDto userDto) throws UserAlreadyExistException {
        if (emailExist(userDto.getEmail())) {
            throw new UserAlreadyExistException("Há uma conta com este endereço de email: "
              + userDto.getEmail());
        }
        User userToBeInserted = new User();
        userToBeInserted.setName(userDto.getName());
        userToBeInserted.setEmail(userDto.getEmail());
        userToBeInserted.setPassword(userDto.getPassword());
        userToBeInserted.setUserProfile(userDto.getProfile());
        return userToBeInserted;
    }

    private boolean emailExist(String email) {
        return userRepository.findByEmail(email) != null;
    }
}