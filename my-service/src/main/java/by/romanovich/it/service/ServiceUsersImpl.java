package by.romanovich.it.service;

import by.romanovich.it.dao.*;
import by.romanovich.it.pojos.users.Citys;
import by.romanovich.it.pojos.users.Readers;
import by.romanovich.it.pojos.users.Streets;
import by.romanovich.it.pojos.users.Users;
import by.romanovich.it.util.PullDataSourceConnection;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ServiceUsersImpl implements ServiceUser {

    private PullDataSourceConnection pull = PullDataSourceConnection.getPull();

    private Connection con = pull.readConnection();

    private Dao<Citys> daoCitys;

    private Dao<Streets> daoStreets;

    private Dao<Readers> daoReaders;

    private DaoUsers daoUsers;

    private static ServiceUsersImpl serviceUser;

    private static final Logger log = Logger.getLogger(ServiceUsersImpl.class);

    private ServiceUsersImpl() {
        daoCitys = DaoCitysImpl.getDaoCitys(con);
        daoStreets = DaoStreetsImpl.getDaoStreets(con);
        daoReaders = DaoReadersImpl.getDaoReaders(con);
        daoUsers = DaoUsersImpl.getDaoUsers(con);
    }

    public synchronized static ServiceUsersImpl getServiceUser() {
        if(serviceUser == null)
            return new ServiceUsersImpl();
        return serviceUser;
    }

    @Override
    public Users create(Citys citys, Streets streets, Readers readers, Users users) throws SQLException {
        try {
            con.setAutoCommit(false);
            daoCitys.create(citys);
            daoStreets.create(streets);
            daoReaders.create(readers);
            users = daoUsers.create(users);
            con.commit();
            log.info("addUser " + users);
            return users;
        } catch (SQLException e) {
            log.error(e);
        } finally {
            con.close();
        }
        con.rollback();
        return null;
    }

    @Override
    public List<Users> readAll() {
        List<Users> usersList = daoUsers.readAll();
        return usersList;
    }

    @Override
    public void update(Users users, Citys citys, Streets streets, Readers readers) throws SQLException{
        try {
            con.setAutoCommit(false);
            daoCitys.update(citys);
            daoStreets.update(streets);
            daoReaders.update(readers);
            daoUsers.update(users);
            con.commit();
        } catch (SQLException e) {
            log.error(e);
        } finally {
            con.close();
        }
        con.rollback();
    }

    @Override
    public void delete(Users users) throws SQLException{
        if(users != null) {
            try {
                con.setAutoCommit(false);
                daoUsers.delete(users);
                con.commit();
            } catch (SQLException e) {
                log.error(e);
            } finally {
                con.close();
            }
            con.rollback();
        }

    }

    @Override
    public Users getUsersByEmailAndPassword(String login, String password) {
        Users user = daoUsers.getUsersByLoginAndPassword(login, password);
        log.info("userByLoginAndPassword " + user);
        return user;
    }

    @Override
    public Boolean isCheckAuthUser(String login, String password) {
        Users user = daoUsers.getUsersByLoginAndPassword(login, password);
        log.info("userByLoginAndPassword " + user);
        Boolean isCheck = user != null ? true : false;
        return isCheck;
    }
}
