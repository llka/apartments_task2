package ru.ilka.apartments.logic;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ilka.apartments.dao.ApartmentRepository;
import ru.ilka.apartments.entity.Apartment;

import java.util.List;

@Service
public class ApartmentLogic {

    @Autowired
    private ApartmentRepository apartmentRepository;

    public Apartment findById(int id) {
        return apartmentRepository.findOne(id);
    }

    public List<Apartment> findAll() {
        return apartmentRepository.findAll();
    }

    public List<Apartment> findByCostBetween(int costLow, int costHigh){
        return  apartmentRepository.findByCostBetween(costLow,costHigh);
    }

    public void deleteAll() {
        apartmentRepository.deleteAll();
    }

    public void delete(int id) {
        apartmentRepository.delete(id);
    }

    public Apartment save(Apartment apartment) {
        return apartmentRepository.save(apartment);
    }
}
