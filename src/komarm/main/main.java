package komarm.main;

import komarm.impl.LoginLogsAggService;
import komarm.impl.LoginService;
import komarm.sources.SQLite;
import org.json.simple.JSONObject;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

import static komarm.impl.Statistics.requestRecords;

/**
     * Class for running and simulating implementation
     *
     * @author Miroslav Komar
     * */

public class main {
    public static void main(String[] args) throws SQLException {
        ArrayList<JSONObject> logs = new LoginService().generateLogins();

        Connection connection = SQLite.connect();

        LoginLogsAggService loginLogsAggService = new LoginLogsAggService();

        ArrayList<JSONObject> records = loginLogsAggService.createRecords(connection, logs);
        System.out.println(records);
        System.out.println("Number of records: " + records.size());

        System.out.println(requestRecords(loginLogsAggService));

        connection.close();
    }
}
