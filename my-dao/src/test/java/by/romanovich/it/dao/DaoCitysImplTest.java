package by.romanovich.it.dao;

import by.romanovich.it.util.PullDataSourceConnection;
import junit.framework.Assert;

import java.sql.Connection;

/**
 * Test the DaoCitysImpl
 * @author Romanovich Andrey
 * @version 1.0
 */
public class DaoCitysImplTest extends Assert {

    private PullDataSourceConnection pull = PullDataSourceConnection.getPull();

    private Connection con = pull.readConnection();

    private DaoCitysImpl daoCitys = DaoCitysImpl.getDaoCitys(con);


    public void testReadAll() throws Exception {
        assertNotNull(daoCitys.readAll());
    }
}