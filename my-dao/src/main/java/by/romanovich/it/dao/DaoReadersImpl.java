package by.romanovich.it.dao;

import by.romanovich.it.pojos.users.Citys;
import by.romanovich.it.pojos.users.Readers;
import by.romanovich.it.pojos.users.Streets;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This is DaoReadersImpl for Readers
 * @see by.romanovich.it.pojos.users.Readers
 * @author Romanovich Andrei
 * @version 1.0
 */
public class DaoReadersImpl implements Dao<Readers> {

    /**@serial Connection for database*/
    private Connection con;

    /**@serial Singleton*/
    private static DaoReadersImpl daoReaders;

    /**@serial Request training for database*/
    private PreparedStatement ps;

    private static final Logger log = Logger.getLogger(DaoCitysImpl.class);

    private static final String SELECT = "SELECT * FROM readers;";

    private static final String INSERT = "INSERT INTO readers (r_name, r_surname, r_tel, id_city, id_street) " +
            "VALUES (?, ?, ?, ?, ?);";

    private static final String UPDATE = "UPDATE readers SET r_name=?, r_surname=?, r_tel=?, id_city=?, id_street=? " +
            "WHERE id_reader=?;";

    private static final String DELETE = "DELETE FROM readers WHERE id_reader=?;";

    private DaoReadersImpl(Connection con) {
        this.con = con;
    }

    public synchronized static DaoReadersImpl getDaoReaders(Connection con) {
        if(daoReaders == null)
            return new DaoReadersImpl(con);
        return daoReaders;
    }

    @Override
    public Readers create(Readers readers) {
        try {
            ps = con.prepareStatement(INSERT);
            ps.setString(1, readers.getName());
            ps.setString(2, readers.getSurname());
            ps.setString(3, readers.getUserTel());
            ps.setObject(4, readers.getCity());
            ps.setObject(5, readers.getStreet());
            ps.executeUpdate();

            log.info("addReader " + readers);
            return readers;
        } catch (SQLException e) {
            log.error(e);
        }
        return null;
    }

    @Override
    public List<Readers> readAll() {
        try {
            ps = con.prepareStatement(SELECT);
            ResultSet rs = ps.executeQuery();
            List<Readers> readersList = new ArrayList<Readers>();
            Readers reader;
            Citys city;
            Streets street;
            while (rs.next()) {
                city = new Citys(rs.getInt("id_city"), rs.getString("c_name"));
                street = new Streets(rs.getInt("id_street"), rs.getString("s_name"));
                reader = new Readers(rs.getInt("id_reader"), rs.getString("r_name"), rs.getString("r_surname"),
                        rs.getString("r_tel"), city, street);
                readersList.add(reader);
            }

            log.info("readersList " + readersList);
            return readersList;
        } catch (SQLException e) {
            log.error(e);
        }
        return null;
    }

    @Override
    public void update(Readers readers) {
        try {
            ps = con.prepareStatement(UPDATE);
            ps.setString(1, readers.getName());
            ps.setString(2, readers.getSurname());
            ps.setString(3, readers.getUserTel());
            ps.setObject(4, readers.getCity());
            ps.setObject(5, readers.getStreet());
            ps.setInt(6, readers.getIdReader());
            ps.executeUpdate();

            log.info("updateReader " + readers);
        } catch (SQLException e) {
            log.error(e);
        }
    }

    @Override
    public void delete(Readers readers) {
        try {
            ps = con.prepareStatement(DELETE);
            ps.setInt(1, readers.getIdReader());
            ps.executeUpdate();

            log.info("deleteReader " + readers);
        } catch (SQLException e) {
            log.error(e);
        }
    }
}
