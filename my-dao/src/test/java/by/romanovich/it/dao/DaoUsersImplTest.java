package by.romanovich.it.dao;

import by.romanovich.it.util.PullDataSourceConnection;
import junit.framework.Assert;
import org.junit.Test;

import java.sql.Connection;

public class DaoUsersImplTest extends Assert {

    private PullDataSourceConnection pull = PullDataSourceConnection.getPull();

    private Connection con = pull.readConnection();

    private DaoUsersImpl daoUsers = DaoUsersImpl.getDaoUsers(con);

    private String login = null;

    private String password = null;

    @Test
    public void testgetUsersByLoginAndPassword() throws Exception {
        assertNull(daoUsers.getUsersByLoginAndPassword(login, password));
    }

    @Test
    public void testReadAll() throws Exception {
        assertNotNull(daoUsers.readAll());
    }

}