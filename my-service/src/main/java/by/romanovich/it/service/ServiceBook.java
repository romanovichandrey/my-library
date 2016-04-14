package by.romanovich.it.service;

import by.romanovich.it.pojos.books.Books;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface for ServiceImpl class.
 * @see by.romanovich.it.service
 * @author Romanovich Andrei
 * @version 1.0
 */
public interface ServiceBook {

    Books create(Books books) throws SQLException;

    List<Books> readAll();

    void update(Books books) throws SQLException;

    void delete(Books books) throws SQLException;
}
