package ru.ilka.apartments.logic;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ilka.apartments.dao.UserRepository;
import ru.ilka.apartments.entity.User;

import java.util.List;

@Service
public class UserLogic {

    @Autowired
    private UserRepository userRepository;

    public User findUserById(int id) {
        return userRepository.findOne(id);
    }

    public User findUserByLogin(String login) {
        return userRepository.findByLoginIgnoreCase(login);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

//    public List<User> findAllOrderByLogin() {
//        return userRepository.findAllOrderByLogin();
//    }
//
//    public List<User> findAllOrderById() {
//        return userRepository.findAllOrderById();
//    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public void deleteAll() {
        userRepository.deleteAll();
    }

    public void deleteById(int id) {
        userRepository.delete(id);
    }

    public void delete(User user) {
        userRepository.delete(user);
    }
}
