package Daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class BaseDao {

    public Connection getConnectionMysql() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        String user = "root";
        String pass = "root";
        String url = "jdbc:mysql://localhost:3306/hr?serverTimezone=America/Lima";

        return DriverManager.getConnection(url, user, pass);
    }

    public Connection getConnectionOracle() throws SQLException {

        String user = "root";
        String pass = "root";
        String url = "jdbc:oracle://localhost:3306/hr?serverTimezone=America/Lima";

        return DriverManager.getConnection(url, user, pass);
    }

}


