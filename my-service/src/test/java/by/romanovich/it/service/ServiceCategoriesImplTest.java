package by.romanovich.it.service;

import by.romanovich.it.dao.DaoCategoriesImpl;
import by.romanovich.it.pojos.books.Categories;
import by.romanovich.it.util.PullDataSourceConnection;
import junit.framework.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.util.List;

public class ServiceCategoriesImplTest extends Assert{

    private PullDataSourceConnection pull = PullDataSourceConnection.getPull();

    private Connection con = pull.readConnection();

    private DaoCategoriesImpl daoCategories = DaoCategoriesImpl.getDaoCategories(con);

    @Test
    public void testGetServiceCategories() throws Exception {
        assertNotNull(daoCategories);
    }

    @Test
    public void testReadAll() throws Exception {
        List<Categories> list = daoCategories.readAll();
        for(Categories categories : list)
            assertNotNull(categories);
    }
}