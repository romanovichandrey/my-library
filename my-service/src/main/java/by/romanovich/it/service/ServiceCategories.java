package by.romanovich.it.service;

import by.romanovich.it.pojos.books.Categories;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface for ServiceImpl class.
 * @see by.romanovich.it.service
 * @author Romanovich Andrei
 * @version 1.0
 */
public interface ServiceCategories {

    Categories create(Categories categories) throws SQLException;

    List<Categories> readAll();

    void update(Categories categories) throws SQLException;

    void delete(Categories categories) throws SQLException;
}
