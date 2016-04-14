package by.romanovich.it.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.log4j.Logger;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * This class creates a pool of connections to the database
 * @version     1.0 01 April 2016
 * @author      Andrey Romanovich
 */
public class PullDataSourceConnection {

    /**Class object ComboPoolledDataSource package com.mchange.v2.c3p0 */
    private static ComboPooledDataSource comboPool;

    private Properties prop;

    private InputStream inputStream;

    private static PullDataSourceConnection pull;

    private static final String WAY = "mySql.properties";

    private static final Logger log = Logger.getLogger(PullDataSourceConnection.class);

    private PullDataSourceConnection() {
        comboPool = new ComboPooledDataSource();
        prop = new Properties();
        try {
            inputStream = PullDataSourceConnection.class.getClassLoader().getResourceAsStream(WAY);
            prop.load(inputStream);
            try {
                /**Loads the jdbc driver */
                comboPool.setDriverClass(prop.getProperty("db.driver"));

                /**Establishes a database connection */
                comboPool.setJdbcUrl(prop.getProperty("db.host"));
                comboPool.setUser(prop.getProperty("db.username"));
                comboPool.setPassword(prop.getProperty("db.password"));

                /**Minimum number of Connections a pool will maintain at any given time */
                comboPool.setMinPoolSize(5);

                /**Defines how many times c3p0 will try to acquire a new Connection from the database before giving up*/
                comboPool.setAcquireRetryAttempts(5);

                /**Determines how many connections at a time c3p0 will try to acquire when the pool is exhausted */
                comboPool.setAcquireIncrement(5);

                /**Maximum number of Connections a pool will maintain at any given time */
                comboPool.setMaxPoolSize(20);

                /**The size of c3p0's global PreparedStatement cache */
                comboPool.setMaxStatements(60);

            } catch (PropertyVetoException e) {
                log.error("Property represents an unacceptable value " + e);
            }
        } catch (IOException e) {
            log.error(e);
        }
    }

    public static PullDataSourceConnection getPull() {
        if(pull == null)
            return new PullDataSourceConnection();
        return pull;
    }

    /**
     * This method pulls out class object ComboPoolledDataSource class object Connection in the connection database
     * @return      Connection
     */
    public Connection readConnection() {
        try {
            return comboPool.getConnection();
        }catch (SQLException e) {
            log.error(e);
        }
        return null;
    }

}
