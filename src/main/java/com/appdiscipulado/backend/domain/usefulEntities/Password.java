package com.appdiscipulado.backend.domain.usefulEntities;

import com.appdiscipulado.backend.controllers.dto.UserDto;
import com.appdiscipulado.backend.domain.VO.User;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Password implements ConstraintValidator<PasswordMatches, String> {
    private Pattern pattern;
    private Matcher matcher;
    private static StringBuilder sb = new StringBuilder();

    /**
     * password rule: Minimum eight characters, at least one letter and one number
     */
    private static final StringBuilder PASSWORD_PATTERN = sb.append("^(?=.*[A-Za-z])")
            .append("(?=.*\\d)")
            .append("[A-Za-z\\d]{8,}$");

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }


    public boolean isValid(UserDto userDto, ConstraintValidatorContext context) {
        validatePassword(userDto.getPassword());

        if(validatePassword(userDto.getPassword())) {
            if(userDto.getPassword().equals(userDto.getMatchingPassword())){
                return true;
            }
        }
        return false;
    }

    private boolean validatePassword(String password){
        pattern = Pattern.compile(PASSWORD_PATTERN.toString());
        matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
