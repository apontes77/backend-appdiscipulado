package com.appdiscipulado.backend.domain.usefulEntities;

import com.appdiscipulado.backend.controllers.dto.UserDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class Password implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        UserDTO user = (UserDTO) obj;
        return user.getPassword().equals(user.getMatchingPassword());
    }
}
