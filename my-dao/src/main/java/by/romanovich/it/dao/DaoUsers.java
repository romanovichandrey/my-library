package by.romanovich.it.dao;

import by.romanovich.it.pojos.users.Users;

/**
 * Interface for DaoImpl class.
 * @see by.romanovich.it.dao
 * @author Romanovich Andrei
 * @version 1.0
 */
public interface DaoUsers extends Dao<Users> {

    Users getUsersByLoginAndPassword(String login, String password);
}
