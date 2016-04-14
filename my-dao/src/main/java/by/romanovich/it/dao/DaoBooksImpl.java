package by.romanovich.it.dao;

import by.romanovich.it.pojos.books.Books;
import by.romanovich.it.pojos.books.Categories;
import by.romanovich.it.pojos.users.Users;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This is DaoBooksImpl for Books
 * @see by.romanovich.it.pojos.books.Books
 * @author Romanovich Andrei
 * @version 1.0
 */
public class DaoBooksImpl implements Dao<Books> {
    /**@serial Connection for database*/
    private Connection con;

    /**@serial Singleton*/
    private static DaoBooksImpl daoBooks;

    /**@serial Request training for database*/
    private PreparedStatement ps;

    private static final Logger log = Logger.getLogger(DaoCitysImpl.class);

    private static final String SELECT = "SELECT * FROM books JOIN categories ON categories.id_cat=books.id_cat JOIN " +
            "users on users.id_user=books.id_user;";

    private static final String INSERT = "INSERT INTO books (book_name, book_desk, book_autor, book_year, book_price," +
            "id_user, id_cat) VALUES (?, ?, ?, ?, ?, ?, ?);";

    private static final String UPDATE = "UPDATE books SET book_name=?, book_desk=?, book_autor=?, book_year=?," +
            "book_price=?, id_user=?, id_cat=? WHERE id_book=?;";

    private static final String DELETE = "DELETE FROM books WHERE id_book=?;";


    private DaoBooksImpl(Connection con) {
        this.con = con;
    }

    public static DaoBooksImpl getDaoBooks(Connection con) {
        if(daoBooks == null)
            return new DaoBooksImpl(con);
        return daoBooks;
    }

    @Override
    public Books create(Books books) {
        try {
            ps = con.prepareStatement(INSERT);
            ps.setString(1, books.getName());
            ps.setString(2, books.getDescription());
            ps.setString(3, books.getAuthor());
            ps.setString(4, books.getBook_date());
            ps.setFloat(5, books.getPrice());
            ps.setObject(6, books.getUser());
            ps.setObject(7, books.getCategory());
            ps.executeUpdate();

            log.info("addBook " + books);
            return books;
        } catch (SQLException e) {
            log.error(e);
        }
        return null;
    }

    @Override
    public List<Books> readAll() {
        try {
            ps = con.prepareStatement(SELECT);
            List<Books> booksList = new ArrayList<Books>();
            ResultSet rs = ps.executeQuery();
            Users user;
            Categories category;
            Books book;
            while (rs.next()) {
                user = new Users(rs.getInt("id_user"), rs.getString("u_login"), rs.getString("u_password"));
                category = new Categories(rs.getInt("id_cat"), rs.getString("cat_name"));
                book = new Books(rs.getInt("id_book"), rs.getString("book_name"), rs.getString("book_desk"),
                        rs.getString("book_autor"), rs.getString("book_year"), rs.getFloat("book_price"), user, category);
                booksList.add(book);
            }

            log.info("booksList " + booksList);
            return booksList;
        }catch (SQLException e) {
            log.error(e);
        }
        return null;
    }

    @Override
    public void update(Books books) {
        try {
            ps = con.prepareStatement(UPDATE);
            ps.setString(1, books.getName());
            ps.setString(2, books.getDescription());
            ps.setString(3, books.getAuthor());
            ps.setString(4, books.getBook_date());
            ps.setFloat(5, books.getPrice());
            ps.setObject(6, books.getUser());
            ps.setObject(7, books.getCategory());
            ps.setInt(8, books.getId_book());
            ps.executeUpdate();

            log.info("updateBook " + books);
        } catch (SQLException e) {
            log.error(e);
        }
    }

    @Override
    public void delete(Books books) {
        try {
            ps = con.prepareStatement(DELETE);
            ps.setInt(1, books.getId_book());
            ps.executeUpdate();

            log.info("deleteBook " + books);
        } catch (SQLException e) {
            log.error(e);
        }
    }
}
