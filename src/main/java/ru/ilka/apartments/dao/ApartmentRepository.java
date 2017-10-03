package ru.ilka.apartments.dao;

import org.springframework.data.repository.CrudRepository;
import ru.ilka.apartments.entity.Apartment;

import java.util.List;

public interface ApartmentRepository extends CrudRepository<Apartment, Integer> {

    @Override
    Apartment findOne(Integer id);

    List<Apartment> findAll();

    List<Apartment> findByCostBetween(int costLow, int costHigh);

    List<Apartment> findByCostLessThanEqual(int maxCost);

    @Override
    <S extends Apartment> S save(S s);

    @Override
    void deleteAll();

    @Override
    void delete(Integer id);
}
