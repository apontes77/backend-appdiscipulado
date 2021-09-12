package com.appdiscipulado.backend.domain.usefulEntities;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Email implements ConstraintValidator<ValidEmail, String> {

    private Pattern pattern;
    private Matcher matcher;
    private static StringBuilder sb = new StringBuilder();
    private static final StringBuilder EMAIL_PATTERN = sb.append("^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@")
                                                            .append("[A-Za-z0-9-]+(.[A-Za-z0-9]+)*")
                                                            .append("(.[A-Za-z]{2,})$");
    @Override
    public void initialize(ValidEmail constraintAnnotation) {
    }
    @Override
    public boolean isValid(String email, ConstraintValidatorContext context){
        return (validateEmail(email));
    }
    private boolean validateEmail(String email) {
        pattern = Pattern.compile(EMAIL_PATTERN.toString());
        matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
