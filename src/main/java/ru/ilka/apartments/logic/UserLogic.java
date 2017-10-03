package ru.ilka.apartments.logic;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ilka.apartments.dao.UserRepository;
import ru.ilka.apartments.entity.Apartment;
import ru.ilka.apartments.entity.User;
import ru.ilka.apartments.exception.DaoException;
import ru.ilka.apartments.exception.LogicException;

import java.util.List;
import java.util.Set;

@Service
public class UserLogic {

    @Autowired
    private UserRepository userRepository;

    public User findById(int id) throws LogicException {
        User user = userRepository.findOne(id);
        if(user == null){
            throw new LogicException("No user with id = " + id);
        }
        return userRepository.findOne(id);
    }

    public User findByLogin(String login) {
        return userRepository.findByLoginIgnoreCase(login);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public List<User> findAllNotBanned() {
        return userRepository.findByBanFalse();
    }

    public List<User> findAllBanned() {
        return userRepository.findByBanTrue();
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public void deleteAll() {
        userRepository.deleteAll();
    }

    public void deleteById(int id) {
        userRepository.delete(id);
    }

    public void bookApartments(int userId, Set<Apartment> bookedApartments) throws LogicException {
        User user = findById(userId);
        Set<Apartment> userApartments = user.getApartments();

        userApartments.addAll(bookedApartments);
        user.setApartments(userApartments);
        save(user);
    }

    public void bookApartment(int userId, Apartment apartment) throws LogicException {
        User user = findById(userId);
        Set<Apartment> userApartments = user.getApartments();
        userApartments.add(apartment);
        user.setApartments(userApartments);
        save(user);
    }

    public void freeApartment(int userId, Apartment apartment) throws LogicException {
        User user = findById(userId);
        Set<Apartment> userApartments = user.getApartments();
        userApartments.remove(apartment);
        user.setApartments(userApartments);
        save(user);
    }

}
