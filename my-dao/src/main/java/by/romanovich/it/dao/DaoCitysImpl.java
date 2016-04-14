package by.romanovich.it.dao;

import by.romanovich.it.pojos.users.Citys;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This is DaoCitysImpl for Cytes
 * @see by.romanovich.it.pojos.users.Citys
 * @author Romanovich Andrei
 * @version 1.0
 */
public class DaoCitysImpl implements Dao<Citys> {

    /**@serial Connection for database*/
    private Connection con;

    /**@serial Singleton*/
    private static DaoCitysImpl daoCitys;

    /**@serial Request training for database*/
    private PreparedStatement ps;

    private static final Logger log = Logger.getLogger(DaoCitysImpl.class);

    private static final String SELECT = "SELECT * FROM citys;";

    private static final String INSERT = "INSERT INTO citys (c_name) VALUES (?);";

    private static final String UPDATE = "UPDATE citys SET c_name=? WHERE id_city=?;";

    private static final String DELETE = "DELETE FROM citys WHERE id_city=?;";

    private DaoCitysImpl(Connection con) {
        this.con = con;
    }

    public synchronized static DaoCitysImpl getDaoCitys(Connection con) {
        if(daoCitys == null)
            return new DaoCitysImpl(con);
        return daoCitys;
    }

    @Override
    public Citys create(Citys citys) {
        try {
            ps = con.prepareStatement(INSERT);
            ps.setString(1, citys.getName());
            ps.executeUpdate();

            log.info("addCity " + citys);
            return citys;
        } catch (SQLException e) {
            log.error(e);
        }
        return null;
    }

    @Override
    public List<Citys> readAll() {
        try {
            ps = con.prepareStatement(SELECT);
            ResultSet rs = ps.executeQuery();
            List<Citys> citysList = new ArrayList<Citys>();
            while (rs.next()) {
                citysList.add(new Citys(rs.getInt("id_city"), rs.getString("c_name")));
            }

            log.info("citysList " + citysList);
            return citysList;
        } catch (SQLException e) {
            log.error(e);
        }
        return null;
    }

    @Override
    public void update(Citys citys) {
        try {
            ps = con.prepareStatement(UPDATE);
            ps.setString(1, citys.getName());
            ps.setInt(2, citys.getIdCity());
            ps.executeUpdate();

            log.info("updateCity " + citys);
        } catch (SQLException e) {
            log.error(e);
        }
    }

    @Override
    public void delete(Citys citys) {
        try {
            ps = con.prepareStatement(DELETE);
            ps.setInt(1, citys.getIdCity());
            ps.executeUpdate();

            log.info("deleteCity " + citys);
        } catch (SQLException e) {
            log.error(e);
        }
    }
}
