package ru.ilka.apartments.validator;

import org.springframework.stereotype.Component;

@Component
public class AccountValidator {
    private static final String LOGIN_REGEX = "[a-zA-Z0-9_]{4,45}";
    private static final String EMAIL_REGEX = "([a-zA-Z0-9._-]+)*[a-zA-Z0-9._-]+@[a-z0-9_-]+([a-z0-9_-]+)*([.]{1})[a-z]{2,6}";
    private static final String PASSWORD_REGEX = "(?=^.{6,40}$)(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?!.*\\s)(?!.*\\W).*$";

    public boolean validateLogin(String login) {
        return login.matches(LOGIN_REGEX);
    }

    public boolean validateEmail(String email) {
        return email.matches(EMAIL_REGEX);
    }

    public boolean validatePassword(String password) {
        return password.matches(PASSWORD_REGEX);
    }

    public boolean validateAuthorization(String login, String password) {
        return (validateLogin(login) && validatePassword(password));
    }
}