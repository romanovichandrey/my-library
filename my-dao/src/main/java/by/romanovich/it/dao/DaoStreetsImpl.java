package by.romanovich.it.dao;

import by.romanovich.it.pojos.users.Streets;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This is DaoStreetsImpl for Streets
 * @see by.romanovich.it.pojos.users.Streets
 * @author Romanovich Andrei
 * @version 1.0
 */
public class DaoStreetsImpl implements Dao<Streets> {

    /**@serial Connection for database*/
    private Connection con;

    /**@serial Singleton*/
    private static DaoStreetsImpl daoStreets;

    /**@serial Request training for database*/
    private PreparedStatement ps;

    private static final Logger log = Logger.getLogger(DaoCitysImpl.class);

    private static final String SELECT = "SELECT * FROM streets;";

    private static final String INSERT = "INSERT INTO streets (s_name) VALUES (?);";

    private static final String UPDATE = "UPDATE streets SET s_name=? WHERE id_street=?;";

    private static final String DELETE = "DELETE FROM streets WHERE id_street=?;";

    private DaoStreetsImpl(Connection con) {
        this.con = con;
    }

    public synchronized static DaoStreetsImpl getDaoStreets(Connection con) {
        if(daoStreets == null)
            return new DaoStreetsImpl(con);
        return daoStreets;
    }

    @Override
    public Streets create(Streets streets) {
        try {
            ps = con.prepareStatement(INSERT);
            ps.setString(1, streets.getName());
            ps.executeUpdate();

            log.info("addStreet " + streets);
            return streets;
        } catch (SQLException e) {
            log.error(e);
        }
        return null;
    }

    @Override
    public List<Streets> readAll() {
        try {
            ps = con.prepareStatement(SELECT);
            ResultSet rs = ps.executeQuery();
            List<Streets> streetsList = new ArrayList<Streets>();
            while (rs.next()) {
                streetsList.add(new Streets(rs.getInt("id_street"), rs.getString("s_name")));
            }

            log.info("streetsList " + streetsList);
            return streetsList;
        } catch (SQLException e) {
            log.error(e);
        }
        return null;
    }

    @Override
    public void update(Streets streets) {
        try {
            ps = con.prepareStatement(UPDATE);
            ps.setString(1, streets.getName());
            ps.setInt(2, streets.getIdStreet());
            ps.executeUpdate();

            log.info("updateStreet " + streets);
        } catch (SQLException e) {
            log.error(e);
        }
    }

    @Override
    public void delete(Streets streets) {
        try {
            ps = con.prepareStatement(DELETE);
            ps.setInt(1, streets.getIdStreet());
            ps.executeUpdate();

            log.info("deleteStreet " + streets);
        } catch (SQLException e) {
            log.error(e);
        }
    }
}
