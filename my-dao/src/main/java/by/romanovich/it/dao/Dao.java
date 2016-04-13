package by.romanovich.it.dao;

import java.util.List;

public interface Dao<T> {

    int create(T t);

    List<T> readAll();

    int update(T t);

    int delete(T t);


}
