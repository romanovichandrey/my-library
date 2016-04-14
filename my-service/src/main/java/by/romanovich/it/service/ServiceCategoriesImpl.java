package by.romanovich.it.service;

import by.romanovich.it.dao.Dao;
import by.romanovich.it.dao.DaoCategoriesImpl;
import by.romanovich.it.pojos.books.Categories;
import by.romanovich.it.util.PullDataSourceConnection;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ServiceCategoriesImpl implements ServiceCategories {

    private PullDataSourceConnection pull = PullDataSourceConnection.getPull();

    private Connection con = pull.readConnection();

    private Dao<Categories> daoCategories;

    private static ServiceCategoriesImpl serviceCategories;

    private static final Logger log = Logger.getLogger(ServiceCategoriesImpl.class);

    private ServiceCategoriesImpl() {
        daoCategories = DaoCategoriesImpl.getDaoCategories(con);
    }

    public static ServiceCategoriesImpl getServiceCategories() {
        if(serviceCategories == null)
            return new ServiceCategoriesImpl();
        return serviceCategories;
    }

    @Override
    public Categories create(Categories categories) throws SQLException {
        try {
            con.setAutoCommit(false);
            categories = daoCategories.create(categories);
            con.commit();
        } catch (SQLException e) {
            log.error(e);
        }
        con.rollback();
        return null;
    }

    @Override
    public List<Categories> readAll() {
        return daoCategories.readAll();
    }

    @Override
    public void update(Categories categories) throws SQLException {
        try {
            con.setAutoCommit(false);
            daoCategories.update(categories);
            con.commit();
        } catch (SQLException e) {
            log.error(e);
        }
        con.rollback();

    }

    @Override
    public void delete(Categories categories) throws SQLException{
        if(categories != null) {
            try {
                con.setAutoCommit(false);
                daoCategories.delete(categories);
                con.commit();
            } catch (SQLException e) {
                log.error(e);
            }
            con.rollback();
        }

    }
}
