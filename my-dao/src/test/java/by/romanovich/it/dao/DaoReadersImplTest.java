package by.romanovich.it.dao;

import by.romanovich.it.util.PullDataSourceConnection;
import junit.framework.Assert;
import org.junit.Test;

import java.sql.Connection;

public class DaoReadersImplTest extends Assert {

    private PullDataSourceConnection pull = PullDataSourceConnection.getPull();

    private Connection con = pull.readConnection();

    private DaoReadersImpl daoReaders = DaoReadersImpl.getDaoReaders(con);

    @Test
    public void testReadAll() throws Exception {
        assertNotNull(daoReaders.readAll());
    }


}