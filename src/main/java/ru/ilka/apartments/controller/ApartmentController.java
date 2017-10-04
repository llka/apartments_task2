package ru.ilka.apartments.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.ilka.apartments.entity.Apartment;
import ru.ilka.apartments.entity.User;
import ru.ilka.apartments.exception.LogicException;
import ru.ilka.apartments.logic.ApartmentLogic;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/apartment")
public class ApartmentController {

    private static Logger logger = LogManager.getLogger(ApartmentController.class);
    private static final String MEDIA_TYPE_JSON = "application/json";

    @Autowired
    private ApartmentLogic apartmentLogic;

    @GetMapping(produces = MEDIA_TYPE_JSON)
    public List<Apartment> getAllApartments() {
        return apartmentLogic.findAll();
    }

    @DeleteMapping
    public void deleteAllApartments() {
        apartmentLogic.deleteAll();
    }

    @GetMapping(value = "/{id}", produces = MEDIA_TYPE_JSON)
    public Apartment getApartmentById(@PathVariable int id) {
        try {
            return apartmentLogic.findById(id);
        } catch (LogicException e) {
            //TODO
            return new Apartment();
        }
    }

    @PostMapping(consumes = MEDIA_TYPE_JSON, produces = MEDIA_TYPE_JSON)
    public Apartment saveApartment(@RequestBody Apartment apartment) {
        return apartmentLogic.save(apartment);
    }

    @PutMapping(consumes = MEDIA_TYPE_JSON, produces = MEDIA_TYPE_JSON)
    public Apartment updateApartment(@RequestBody Apartment apartment) {
        return apartmentLogic.save(apartment);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteApartment(@PathVariable int id) {
        try {
            apartmentLogic.delete(id);
        } catch (LogicException e) {
            logger.error(e.getMessage());
        }
    }

    @GetMapping(consumes = MEDIA_TYPE_JSON, produces = MEDIA_TYPE_JSON)
    public List<Apartment> getApartmentsByCostBetween(@RequestBody int minCost, @RequestBody int maxCost) {
        try {
            return apartmentLogic.findByCostBetween(minCost, maxCost);
        } catch (LogicException e) {
            return new ArrayList<Apartment>();
        }
    }

    @GetMapping(value = "/{id}/users", produces = MEDIA_TYPE_JSON)
    public List<User> getUserApartments(@PathVariable int id) {
        try {
            return apartmentLogic.getApartmentUsers(id);
        } catch (LogicException e) {
            return new ArrayList<User>();
        }
    }

}
