package ru.ilka.apartments.dao;

import org.springframework.data.repository.CrudRepository;
import ru.ilka.apartments.entity.User;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Number> {

    @Override
    User findOne(Number integer);

    @Override
    List<User> findAll();

    User findByLogin(String login);

    User findByLoginIgnoreCase(String login);

    @Override
    <S extends User> S save(S s);

    @Override
    void deleteAll();

    @Override
    void delete(Number integer);
}
