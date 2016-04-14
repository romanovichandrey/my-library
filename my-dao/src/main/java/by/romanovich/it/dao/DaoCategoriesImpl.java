package by.romanovich.it.dao;

import by.romanovich.it.pojos.books.Categories;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This is DaoCategoriesImpl for Categories
 * @see by.romanovich.it.pojos.books.Categories
 * @author Romanovich Andrei
 * @version 1.0
 */
public class DaoCategoriesImpl implements Dao<Categories> {

    /**@serial Connection for database*/
    private Connection con;

    /**@serial Singleton*/
    private static DaoCategoriesImpl daoCategories;

    /**@serial Request training for database*/
    private PreparedStatement ps;

    private static final Logger log = Logger.getLogger(DaoCitysImpl.class);

    private static final String SELECT = "SELECT * FROM categories;";

    private static final String INSERT = "INSERT INTO categories (cat_name) VALUES (?);";

    private static final String UPDATE = "UPDATE categories SET cat_name=? WHERE id_cat=?;";

    private static final String DELETE = "DELETE FROM categories WHERE id_cat=?;";

    private DaoCategoriesImpl(Connection con) {
        this.con = con;
    }

    public synchronized static DaoCategoriesImpl getDaoCategories(Connection con) {
        if(daoCategories == null)
            return new DaoCategoriesImpl(con);
        return daoCategories;
    }

    @Override
    public Categories create(Categories categories) {
        try {
            ps = con.prepareStatement(INSERT);
            ps.setString(1, categories.getName_cat());
            ps.executeUpdate();

            log.info("addCategoty " + categories);
            return categories;
        } catch (SQLException e) {
            log.error(e);
        }
        return null;
    }

    @Override
    public List<Categories> readAll() {
        try {
            ps = con.prepareStatement(SELECT);
            ResultSet rs = ps.executeQuery();
            List<Categories> categoriesList = new ArrayList<Categories>();
            while (rs.next()) {
                categoriesList.add(new Categories(rs.getInt("id_cat"), rs.getString("cat_name")));
            }

            log.info("categoriesList " + categoriesList);
            return categoriesList;
        } catch (SQLException e) {
            log.error(e);
        }
        return null;
    }

    @Override
    public void update(Categories categories) {
        try {
            ps = con.prepareStatement(UPDATE);
            ps.setString(1, categories.getName_cat());
            ps.setInt(2, categories.getId_cat());
            ps.executeUpdate();

            log.info("updateCategory " + categories);
        } catch (SQLException e) {
            log.error(e);
        }
    }

    @Override
    public void delete(Categories categories) {
        try {
            ps = con.prepareStatement(DELETE);
            ps.setInt(1, categories.getId_cat());
            ps.executeUpdate();

            log.info("deleteCategory " + categories);
        } catch (SQLException e) {
            log.error(e);
        }
    }
}
