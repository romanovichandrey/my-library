package by.romanovich.it.service;

import by.romanovich.it.dao.*;
import by.romanovich.it.pojos.books.Books;
import by.romanovich.it.pojos.books.Categories;
import by.romanovich.it.util.PullDataSourceConnection;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ServiceBooksImpl implements ServiceBook {

    private PullDataSourceConnection pull = PullDataSourceConnection.getPull();

    private Connection con = pull.readConnection();

    private DaoUsers daoUsers;

    private Dao<Categories> daoCategories;

    private Dao<Books> daoBooks;

    private static ServiceBooksImpl serviceBooks;

    private static final Logger log = Logger.getLogger(ServiceUsersImpl.class);

    private ServiceBooksImpl() {
        daoCategories = DaoCategoriesImpl.getDaoCategories(con);
        daoUsers = DaoUsersImpl.getDaoUsers(con);
        daoBooks = DaoBooksImpl.getDaoBooks(con);
    }

    public synchronized static ServiceBooksImpl getServiceBooks() {
        if(serviceBooks == null)
            return new ServiceBooksImpl();
        return serviceBooks;
    }

    @Override
    public Books create(Books books) throws SQLException {
            try {
                con.setAutoCommit(false);
                books = daoBooks.create(books);
                con.commit();
                log.info("addBook " + books);
                return books;
            } catch (SQLException e) {
                log.error(e);
            }finally {
                con.close();
            }
        con.rollback();
        return null;
    }


    @Override
    public List<Books> readAll() {
        List<Books> booksList = daoBooks.readAll();
        return booksList;
    }

    @Override
    public void update(Books books) throws SQLException{
        if(books != null) {
            try {
                con.setAutoCommit(false);
                daoBooks.update(books);
                con.commit();
            } catch (SQLException e) {
                log.error(e);
            } finally {
                con.close();
            }
            con.rollback();
        }
    }

    @Override
    public void delete(Books books) throws SQLException{
        if(books != null) {
            try {
                con.setAutoCommit(false);
                daoBooks.delete(books);
                con.commit();
            } catch (SQLException e) {
                log.error(e);
            } finally {
                con.close();
            }
            con.rollback();
        }

    }
}
