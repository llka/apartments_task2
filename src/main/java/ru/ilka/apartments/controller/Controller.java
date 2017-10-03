package ru.ilka.apartments.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;
import ru.ilka.apartments.entity.Apartment;
import ru.ilka.apartments.entity.User;
import ru.ilka.apartments.exception.LogicException;
import ru.ilka.apartments.logic.ApartmentLogic;
import ru.ilka.apartments.logic.UserLogic;

import java.io.PrintWriter;
import java.util.List;

@RestController
public class Controller {

    @Autowired
    private UserLogic userLogic;

    @Autowired
    private ApartmentLogic apartmentLogic;

    @RequestMapping(value = {"/apartment"}, method = RequestMethod.GET, produces = "application/json")
    public List<Apartment> getAllApartments() {
        return apartmentLogic.findAll();
    }

    @RequestMapping(value = {"/user"}, method = RequestMethod.GET, produces = "application/json")
    public List<User> getAllUsers() {
        return userLogic.findAll();
    }

//    @RequestMapping(value = {"/users"}, method = RequestMethod.GET, produces = "application/json")
//    public List<User> getUsers(@RequestParam(value = "ban") boolean legal) {
//        if(legal){
//            return userLogic.findAllNotBanned();
//        }else {
//            return userLogic.findAllBanned();
//        }
//    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = "application/json")
    public User getUser(@PathVariable("id") int userId) {
        User user;
        try {
            user = userLogic.findById(userId);
        } catch (LogicException e) {
            user = new User();
            user.setLogin(e.getMessage());
        }
        return user;
    }

    @RequestMapping(value = "/user/", method = RequestMethod.POST, produces = "application/json")
    public User createUser(@RequestBody User user) {
        return userLogic.save(user);
    }

    @RequestMapping(value = "/user/", method = RequestMethod.PUT, produces = "application/json")
    public User updateUser(@RequestBody User user) {
        return userLogic.save(user);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public void deleteUser(@PathVariable("id") int userId) {
        userLogic.deleteById(userId);
    }

    @RequestMapping(value = {"/user"}, method = RequestMethod.DELETE, produces = "application/json")
    public void deleteAllUsers() {
        userLogic.deleteAll();
    }

}
