package ru.ilka.apartments.dao;

import org.springframework.data.repository.CrudRepository;
import ru.ilka.apartments.entity.User;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {

    @Override
    User findOne(Integer id);

    @Override
    List<User> findAll();

    User findByLoginIgnoreCase(String login);

    List<User> findByBanFalse();

    List<User> findByBanTrue();

    @Override
    <S extends User> S save(S s);

    @Override
    void deleteAll();

    @Override
    void delete(Integer integer);
}
