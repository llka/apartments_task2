package ru.ilka.apartments.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.ilka.apartments.entity.User;
import ru.ilka.apartments.logic.UserLogic;

@RestController
@RequestMapping("/signup")
public class SignUpController {
    private static Logger logger = LogManager.getLogger(SignUpController.class);

    private static final String MEDIA_TYPE_JSON = "application/json";
    private static final String NOT_UNIQUE_LOGIN_MSG = "This login is already used, try another one.";
    private static final String SIGNUP_SUCCESS_MSG = "Congratulations, You have successfully signed up!";

    @Autowired
    private UserLogic userLogic;

    @PostMapping(consumes = MEDIA_TYPE_JSON)
    public String register(@RequestBody User user){
        return userLogic.register(user).toString();
    }

    @PostMapping
    public String registerByAuthority(@RequestParam(value = "login") String login, @RequestParam(value = "password") String password){
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        return userLogic.register(user).toString();
    }
}
