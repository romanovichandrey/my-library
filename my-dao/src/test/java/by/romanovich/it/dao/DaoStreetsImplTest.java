package by.romanovich.it.dao;

import by.romanovich.it.util.PullDataSourceConnection;
import junit.framework.Assert;
import org.junit.Test;

import java.sql.Connection;

public class DaoStreetsImplTest extends Assert {

    private PullDataSourceConnection pull = PullDataSourceConnection.getPull();

    private Connection con = pull.readConnection();

    private DaoStreetsImpl daoStreets = DaoStreetsImpl.getDaoStreets(con);

    @Test
    public void testReadAll() throws Exception {

        assertNotNull(daoStreets.readAll());

    }


}