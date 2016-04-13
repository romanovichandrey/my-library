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

public class ServiceUsersImpl implements Service {

    private PullDataSourceConnection pull = new PullDataSourceConnection();

    private Connection con = pull.readConnection();

    private DaoCitysImpl daoCitys;

    private DaoStreetsImpl daoStreets;

    private DaoReadersImpl daoReaders;

    private DaoUsersImpl daoUsers;

    private static ServiceUsersImpl serviceUser;

    private static final Logger log = Logger.getLogger(ServiceUsersImpl.class);

    private ServiceUsersImpl() {
        daoCitys = DaoCitysImpl.getDaoCitys(con);
        daoStreets = DaoStreetsImpl.getDaoStreets(con);
        daoReaders = DaoReadersImpl.getDaoReaders(con);
        daoUsers = DaoUsersImpl.getDaoUsers(con);
    }

    public static ServiceUsersImpl getServiceUser() {
        if(serviceUser == null)
            return new ServiceUsersImpl();
        return serviceUser;
    }

    @Override
    public Users create(Users users, Citys citys, Streets streets, Readers readers) throws SQLException {
        try {
            con.setAutoCommit(false);
            daoCitys.create(citys);
            daoStreets.create(streets);
            daoReaders.create(readers);
            daoUsers.create(users);
            con.commit();
            return users;
        } catch (SQLException e) {
            log.error(e);
        }
        con.rollback();
        return null;
    }

    @Override
    public List<Users> readAll() {
        return daoUsers.readAll();
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
        }
        con.rollback();
    }

    @Override
    public void delete(Users users) {
        if(users != null)
            daoUsers.delete(users);
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
