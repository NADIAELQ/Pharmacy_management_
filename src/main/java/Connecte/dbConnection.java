package Connecte;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class dbConnection {
    public static Connection connect() {
        Connection conn = null;
        try {
            final String url = "jdbc:mysql://localhost:3306/pharmacy";
            final String user = "root" ;
            final String passwd = "";
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, passwd);
        } catch (ClassNotFoundException e) {
            Logger.getLogger(dbConnection.class.getName()).log(Level.SEVERE, null, e);
        } catch (SQLException e) {
            Logger.getLogger(dbConnection.class.getName()).log(Level.SEVERE, null, e);
        }
        return conn;
    }
}
