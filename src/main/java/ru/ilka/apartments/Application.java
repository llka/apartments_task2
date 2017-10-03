package ru.ilka.apartments;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.ilka.apartments.entity.Apartment;
import ru.ilka.apartments.entity.User;
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

        logger.debug("users - findById(2)");
        logger.info(userLogic.findById(2));

        logger.debug("users - findByLogin(A)");
        logger.info(userLogic.findByLogin("a"));

        logger.debug("users - findAll");
        userLogic.findAll().forEach(user -> logger.info(user.toString()));

        logger.debug("users - findAllNotBanned");
        userLogic.findAllNotBanned().forEach(user -> logger.info(user.toString()));

        logger.debug("users - save");
        User userToSave = new User();
        userToSave.setLogin("Just_saved_2");
        userLogic.save(userToSave);
        userLogic.findAll().forEach(u -> logger.info(u.toString()));

        logger.debug("update(2)");
        userToSave = userLogic.findById(2);
        userToSave.setLogin("A");
        userLogic.save(userToSave);
        userLogic.findAll().forEach(u -> logger.info(u.toString()));

        logger.debug("Done!");
    }
}
