package by.romanovich.it.dao;

import by.romanovich.it.pojos.users.Citys;
import by.romanovich.it.pojos.users.Readers;
import by.romanovich.it.pojos.users.Streets;
import by.romanovich.it.pojos.users.Users;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This is DaoUsersImpl for Users
 * @see by.romanovich.it.pojos.users.Users
 * @author Romanovich Andrei
 * @version 1.0
 */
public class DaoUsersImpl implements Dao<Users> {

    /**@serial Connection for database*/
    private Connection con;

    /**@serial Singleton*/
    private static DaoUsersImpl daoUsers;

    /**@serial Request training for database*/
    private PreparedStatement ps;

    private static final Logger log = Logger.getLogger(DaoCitysImpl.class);

    private static final String SELECT = "SELECT * FROM users JOIN readers ON readers.id_reader=users.id_reader JOIN " +
            "citys on citys.id_city=readers.id_city JOIN streets on streets.id_street=readers.id_street;";

    private static final String INSERT = "INSERT INTO users (u_login, u_password, id_reader) VALUES (?, ?, ?);";

    private static final String UPDATE = "UPDATE users SET u_login=?, u_password=?, id_reader=? WHERE id_user=?;";

    private static final String DELETE = "DELETE FROM users WHERE id_user=?;";

    private static final String SELECT_USERS = "SELECT * FROM users WHERE u_login=? AND u_password=?;";

    private DaoUsersImpl(Connection con) {
        this.con = con;
    }

    public static DaoUsersImpl getDaoUsers(Connection con) {
        if(daoUsers == null)
            return new DaoUsersImpl(con);
        return daoUsers;
    }

    @Override
    public Users create(Users users) {
        try {
            ps = con.prepareStatement(INSERT);
            ps.setString(1, users.getLogin());
            ps.setString(2, users.getPassword());
            ps.setObject(3, users.getReader());
            ps.executeUpdate();
            con.close();
            log.info("addUser " + users);
            return users;
        } catch (SQLException e) {
            log.error(e);
        }
        return null;
    }

    @Override
    public List<Users> readAll() {
        try {
            ps = con.prepareStatement(SELECT);
            List<Users> usersList = new ArrayList<Users>();
            ResultSet rs = ps.executeQuery();
            Users user;
            Readers reader;
            Citys city;
            Streets street;
            while (rs.next()) {
                city = new Citys(rs.getInt("id_city"), rs.getString("c_name"));
                street = new Streets(rs.getInt("id_street"), rs.getString("s_name"));
                reader = new Readers(rs.getInt("id_reader"), rs.getString("r_name"), rs.getString("r_surname"),
                        rs.getString("r_tel"), city, street);
                user = new Users(rs.getInt("id_user"), rs.getString("u_login"), rs.getString("u_password"), reader);
                usersList.add(user);
            }
            con.close();
            log.info("usersList " + usersList);
            return usersList;
        }catch (SQLException e) {
            log.error(e);
        }
        return null;
    }

    @Override
    public void update(Users users) {
        try {
            ps = con.prepareStatement(UPDATE);
            ps.setString(1, users.getLogin());
            ps.setString(2, users.getPassword());
            ps.setObject(3, users.getReader());
            ps.setInt(4, users.getIdUser());
            ps.executeUpdate();
            con.close();
            log.info("updateUser " + users);
        } catch (SQLException e) {
            log.error(e);
        }
    }

    @Override
    public void delete(Users users) {
        try {
            ps = con.prepareStatement(DELETE);
            ps.setInt(1, users.getIdUser());
            ps.executeUpdate();
            con.close();
            log.info("deleteUser " + users);
        } catch (SQLException e) {
            log.error(e);
        }
    }

    public Users getUsersByLoginAndPassword(String login, String password) {
        Users user = null;
        try {
            ps = con.prepareStatement(SELECT_USERS);
            ps.setString(1, login);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = new Users(rs.getInt("id_user"), rs.getString("u_login"), rs.getString("u_password"));
            }
            con.close();
            log.info("user " + user);
            return user;
        } catch (SQLException e) {
            log.error(e);
        }
        return user;
    }
}
