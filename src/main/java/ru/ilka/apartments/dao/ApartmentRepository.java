package ru.ilka.apartments.dao;


import org.springframework.data.repository.CrudRepository;
import ru.ilka.apartments.entity.Apartment;

import java.util.List;

public interface ApartmentRepository extends CrudRepository<Apartment, Integer> {

    @Override
    Apartment findOne(Integer integer);

    List<Apartment> findAll();

    @Override
    <S extends Apartment> S save(S s);

    List<Apartment> findByCostBetween(int costLow, int costHigh);

    @Override
    void deleteAll();

    @Override
    void delete(Integer id);
}
