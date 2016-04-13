package by.romanovich.it.dao;

import by.romanovich.it.pojos.users.Citys;
import by.romanovich.it.util.PullDataSourceConnection;
import junit.framework.Assert;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * Test the DaoCitysImpl
 * @author Romanovich Andrey
 * @version 1.0
 */
public class DaoCitysImplTest extends Assert {

    private PullDataSourceConnection pull = new PullDataSourceConnection();

    private Connection con = pull.readConnection();

    private DaoCitysImpl daoCitys = DaoCitysImpl.getDaoCitys(con);

    List<Citys> citysList = new ArrayList<Citys>();


    public void testReadAll() throws Exception {
        assertNotNull(daoCitys.readAll());
    }


    public static void main(String[] args) throws Exception {
        DaoCitysImplTest daoTest = new DaoCitysImplTest();
        daoTest.testReadAll();
    }
}