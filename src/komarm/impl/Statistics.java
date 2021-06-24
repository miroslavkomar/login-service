package komarm.impl;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Scanner;

public class Statistics {

    public static ArrayList<JSONObject> requestRecords(LoginLogsAggService loginLogsAggService){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter number of top countries");
        int num = input.nextInt();
        return loginLogsAggService.getNumOfRecords(num);
    }
}


