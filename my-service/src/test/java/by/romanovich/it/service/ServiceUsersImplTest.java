package by.romanovich.it.service;

import by.romanovich.it.dao.DaoUsers;
import by.romanovich.it.dao.DaoUsersImpl;
import by.romanovich.it.pojos.users.Users;
import by.romanovich.it.util.PullDataSourceConnection;
import junit.framework.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.util.List;

public class ServiceUsersImplTest extends Assert{

    private PullDataSourceConnection pull = PullDataSourceConnection.getPull();

    private Connection con = pull.readConnection();

    private DaoUsers daoUsers = DaoUsersImpl.getDaoUsers(con);

    @Test
    public void testGetServiceUser() throws Exception {
        assertNotNull(daoUsers);
    }

    @Test
    public void testReadAll() throws Exception {
        List<Users> list = daoUsers.readAll();
        for(Users users : list)
            assertNotNull(users);
    }

    @Test
    public void testGetUsersByEmailAndPassword() throws Exception {

    }

    @Test
    public void testIsCheckAuthUser() throws Exception {

    }
}