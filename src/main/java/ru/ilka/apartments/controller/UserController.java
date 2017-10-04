package ru.ilka.apartments.controller;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.ilka.apartments.entity.Apartment;
import ru.ilka.apartments.entity.User;
import ru.ilka.apartments.exception.LogicException;
import ru.ilka.apartments.logic.UserLogic;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {

    private static Logger logger = LogManager.getLogger(UserController.class);
    private static final String MEDIA_TYPE_JSON = "application/json";

    @Autowired
    private UserLogic userLogic;

    @GetMapping(produces = MEDIA_TYPE_JSON)
    public List<User> getAllUsers() {
        return userLogic.findAll();
    }

    @GetMapping(value = "/{id}", produces = MEDIA_TYPE_JSON)
    public User getUser(@PathVariable int id) {
        User user;
        try {
            user = userLogic.findById(id);
        } catch (LogicException e) {
            user = new User();
            user.setLogin(e.getMessage());
        }
        return user;
    }

    @PostMapping(consumes = MEDIA_TYPE_JSON, produces = MEDIA_TYPE_JSON)
    public User createUser(@RequestBody User user) {
        return userLogic.save(user);
    }

    @PutMapping(consumes = MEDIA_TYPE_JSON, produces = MEDIA_TYPE_JSON)
    public User updateUser(@RequestBody User user) {
        return userLogic.save(user);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteUser(@PathVariable int id) {
        try {
            userLogic.delete(id);
        } catch (LogicException e) {
            logger.error(e.getMessage());
        }
    }

    @DeleteMapping
    public void deleteAllUsers() {
        userLogic.deleteAll();
    }

    @GetMapping(value = "/{id}/apartments", produces = MEDIA_TYPE_JSON)
    public List<Apartment> getUserApartments(@PathVariable int id) {
        try {
            return userLogic.getUserApartments(id);
        } catch (LogicException e) {
            return new ArrayList<Apartment>();
        }
    }
}