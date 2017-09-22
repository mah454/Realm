package ir.moke.realm.basic.model;

import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Mahdi Sheikh Hosseini (mah454)
 * DataBase Connection types :
 * 1) JDBC
 * 2) JNDI
 * 3) JNDI Annotation type
 */
public class DatabaseUtils {

    protected Connection connection;
    protected PreparedStatement preparedStatement;

    /**
     * Annotation base datasource connection .
     * @see DataSource
     * @see Resource
     * This method need dependency injection for get database connection .
     * Usage Solution :
     * 1) Use Spring IOC
     * 2) Use CDI
     * 3) Use EJB
     * @see @Injection
     * */
    /*@Resource(name = "jdbc/realm")
    private DataSource dataSource;
    protected DatabaseUtils() {
        try {
            dataSource.getConnection() ;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/

    /**
     * Classic Database Conenction (JDBC)
     * @see java.sql.DriverManager
     */

    /*protected DatabaseUtils() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection("jdbc:mysql://mysql-database/realm?useSSL=false", "root", "rootpass");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }*/


    /**
     * Datasource Connection with lookup context .
     * @see javax.naming.Context
     * @see javax.sql.DataSource
     */
    protected DatabaseUtils() {
        try {
            Context context = new InitialContext();
            DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc/realm");
            connection = dataSource.getConnection();
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
