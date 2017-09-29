package ru.ilka.apartments;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.ilka.apartments.logic.ApartmentLogic;
import ru.ilka.apartments.logic.UserLogic;

import javax.sql.DataSource;

@SpringBootApplication
public class Application implements CommandLineRunner {
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

//        logger.info("users - findAll");
//
//        userLogic.findAll().forEach(user -> logger.info(user.toString()));
//
//        logger.info("apartments - findApartmentsBetween(15,40");
//
//        apartmentLogic.findByCostBetween(15, 40).forEach(apartment -> logger.info(apartment.toString()));
//
//        logger.info("Done!");

        System.out.println("users - findAll");

        userLogic.findAll().forEach(user -> System.out.println(user.toString()));

        System.out.println("apartments - findApartmentsBetween(15,40");

        apartmentLogic.findByCostBetween(15, 40).forEach(apartment -> System.out.println(apartment.toString()));

        System.out.println("Done!");


    }
}
