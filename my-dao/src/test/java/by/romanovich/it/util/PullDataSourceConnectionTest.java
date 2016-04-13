package by.romanovich.it.util;

import junit.framework.Assert;
import org.junit.Test;

import java.sql.Connection;

/**
 * Test the c3po
 * @author Romanovich Andrey
 * @version 1.0
 */
public class PullDataSourceConnectionTest extends Assert {

    private Connection con;
    PullDataSourceConnection pull = new PullDataSourceConnection();

    @Test
    public void testReadConnection() throws Exception {
        con = pull.readConnection();
        assertNotNull(con);
    }

    public static void main(String[] args) throws Exception{
        PullDataSourceConnectionTest test = new PullDataSourceConnectionTest();
        test.testReadConnection();
    }
}