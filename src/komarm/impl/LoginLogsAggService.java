package komarm.impl;

import org.json.simple.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class LoginLogsAggService {
    private ArrayList<JSONObject> records;

    public LoginLogsAggService(){
    }

    public ArrayList<JSONObject> createRecords(Connection connection, ArrayList<JSONObject> logs) throws SQLException {
        PreparedStatement ps = null; ResultSet rs = null;
        Set<String> countryCodes = new HashSet<>();
        ArrayList<JSONObject> records = new ArrayList<>();

        for (JSONObject log : logs) {
            Long ipNumber = (Long) log.get("ts");
            String countryRequest = "SELECT country_code FROM locations WHERE ip_from <=" + log.get("ts") +
                    " AND ip_to >=" + log.get("ts");
            ps = connection.prepareStatement(countryRequest);
            rs = ps.executeQuery();
            String countryCode;
            if (rs.next() && !(countryCode = rs.getString("country_code")).equals("-")) {
                addRecord(countryCode, countryCodes, records);
            }
        }
        setRecords(records);
        return records;
    }

    private void addRecord(String countryCode, Set<String> countryCodes ,ArrayList<JSONObject> records){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        if (countryCodes.contains(countryCode)){
            records.stream()
                    .filter(rec -> rec.containsValue(countryCode))
                    .map(rec -> rec.replace("logins", (int) rec.get("logins") + 1))
                    .collect(Collectors.toList());
        }
        else {
            JSONObject countryRecord = new JSONObject();
            countryRecord.put("date", dateFormat.format(date));
            countryRecord.put("order", 0);
            countryRecord.put("country", countryCode);
            countryRecord.put("logins", 1);
            countryCodes.add(countryCode);
            records.add(countryRecord);
        }
    }

    public ArrayList<JSONObject> getNumOfRecords(int number) {
        records.sort(new RecordComparator());
        ArrayList<JSONObject> topRecords = new ArrayList<>();

        for (int i = 0; i < number; i++) {
            JSONObject record = records.get(i);
            record.replace("order", (i + 1));
            topRecords.add(record);
        }
        return topRecords;
    }

    public void setRecords(ArrayList<JSONObject> records) {
        this.records = records;
    }

    public ArrayList<JSONObject> getRecords() {
        return records;
    }
}
