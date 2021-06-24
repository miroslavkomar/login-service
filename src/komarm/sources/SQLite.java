package komarm.sources;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLite{
    public static Connection connect() {

        Connection connection = null;
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:IP2Location.db");
        } catch (ClassNotFoundException | SQLException ex){
            System.out.println("Could not connect to DB "+ ex.getMessage());
        }
        return connection;
    }
}
