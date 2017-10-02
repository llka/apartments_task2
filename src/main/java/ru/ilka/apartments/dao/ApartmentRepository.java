package ru.ilka.apartments.dao;


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.ilka.apartments.entity.Apartment;

import java.sql.Timestamp;
import java.util.List;

public interface ApartmentRepository extends CrudRepository<Apartment, Integer> {

    @Override
    Apartment findOne(Integer integer);

    List<Apartment> findAll();

    List<Apartment> findByCostBetween(int costLow, int costHigh);

    List<Apartment> findByCostLessThanEqual(int maxCost);

    @Override
    <S extends Apartment> S save(S s);

    @Override
    void deleteAll();

    void deleteById(Integer id);
}
