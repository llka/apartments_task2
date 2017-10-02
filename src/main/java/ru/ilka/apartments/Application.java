package ru.ilka.apartments;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.ilka.apartments.entity.Apartment;
import ru.ilka.apartments.logic.ApartmentLogic;
import ru.ilka.apartments.logic.UserLogic;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private static Logger logger = LogManager.getLogger(Application.class);

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserLogic userLogic;

    @Autowired
    private ApartmentLogic apartmentLogic;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {

        logger.debug("users - findAll");
        userLogic.findAll().forEach(user -> logger.info(user.toString()));

        logger.debug("apartments - findAll");
        apartmentLogic.findAll().forEach(apartment -> logger.info(apartment.toString()));

        logger.debug("apartments - findApartmentsBetween(15,40)");
        apartmentLogic.findByCostBetween(15, 40).forEach(apartment -> logger.info(apartment.toString()));

        logger.debug("apartments - findApartmentsByCostLessThen(15)");
        apartmentLogic.findByCostLessThen(15).forEach(apartment -> logger.info(apartment.toString()));

        logger.debug("apartments - findById(2)");
        logger.info(apartmentLogic.findById(2));

        logger.debug("apartments - update(2)");
        apartmentLogic.save(new Apartment(2,Timestamp.valueOf(LocalDateTime.now()),Timestamp.valueOf(LocalDateTime.now()),55));
        logger.debug("apartments - findAll");
        apartmentLogic.findAll().forEach(apartment -> logger.info(apartment.toString()));

        logger.debug("apartments - deleteAll)");
        apartmentLogic.deleteAll();

        logger.debug("apartments - findAll");
        apartmentLogic.findAll().forEach(apartment -> logger.info(apartment.toString()));

        logger.debug("apartments - save)");
        apartmentLogic.save(new Apartment(2,Timestamp.valueOf(LocalDateTime.now()),Timestamp.valueOf(LocalDateTime.now()),55));
        apartmentLogic.save(new Apartment(5,Timestamp.valueOf(LocalDateTime.now()),Timestamp.valueOf(LocalDateTime.now()),55));
        apartmentLogic.save(new Apartment());
        apartmentLogic.save(new Apartment());

        logger.debug("apartments - findAll");
        apartmentLogic.findAll().forEach(apartment -> logger.info(apartment.toString()));


        logger.debug("Done!");
    }
}
