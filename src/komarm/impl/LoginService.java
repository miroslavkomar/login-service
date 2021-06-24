package komarm.impl;

import org.json.simple.JSONObject;
import java.util.ArrayList;
import java.util.Scanner;

public class LoginService{
    private ArrayList<JSONObject> logs = new ArrayList<>();

    public ArrayList<JSONObject> generateLogins(){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter login rate (num of logins)");
        int rate = input.nextInt();
        for (int i = 0; i < rate; i++){
            String ipAddress = getRandomNumber(1,223) + "." +
                                getRandomNumber(0,256) + "." +
                                getRandomNumber(0,256) + "." +
                                getRandomNumber(0,256);

            JSONObject log = new JSONObject();
            log.put("ts", getIpNumber(ipAddress));
            log.put("ip", ipAddress);
            logs.add(log);
        }
        return logs;
    }

    private int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    private long getIpNumber(String ipAddress){
        String[] octets = ipAddress.split("\\.");
        return (16777216 * Long.parseLong(octets[0])) +
                (65536* Long.parseLong(octets[1])) +
                (256* Long.parseLong(octets[2])) + Long.parseLong(octets[3]);
    }
}
