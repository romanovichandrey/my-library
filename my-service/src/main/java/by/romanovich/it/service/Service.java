package by.romanovich.it.service;

import by.romanovich.it.pojos.users.Citys;
import by.romanovich.it.pojos.users.Readers;
import by.romanovich.it.pojos.users.Streets;
import by.romanovich.it.pojos.users.Users;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface for ServiceImpl class.
 * @see by.romanovich.it.service
 * @author Romanovich Andrei
 * @version 1.0
 */
public interface Service {

    Users create(Users users, Citys citys, Streets streets, Readers readers) throws SQLException;

    List<Users> readAll();

    void update(Users users, Citys citys, Streets streets, Readers readers) throws SQLException;

    void delete(Users users);

    Users getUsersByEmailAndPassword(String login, String password);

    Boolean isCheckAuthUser(String login, String password);

}
