package by.romanovich.it.dao;

import java.util.List;

/**
 * Interface for DaoImpl class.
 * @see by.romanovich.it.dao
 * @author Romanovich Andrei
 * @version 1.0
 * @param <T>
 */
public interface Dao<T> {

    T create(T t);

    List<T> readAll();

    void update(T t);

    void delete(T t);


}
