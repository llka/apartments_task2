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
import java.util.HashSet;
import java.util.Set;

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
        userLogic.findAll().forEach(user -> logger.info(user));

        logger.debug("apartments - findAll");
        apartmentLogic.findAll().forEach(apartment -> logger.info(apartment.toString()));

        logger.debug("Done!");
    }
}
