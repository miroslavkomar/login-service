package komarm.impl;

import org.json.simple.JSONObject;

import java.util.Comparator;

public class RecordComparator implements Comparator<JSONObject> {
    @Override
    public int compare(JSONObject o1, JSONObject o2) {
        if ((int) o1.get("logins") == (int) o2.get("logins"))  {
            return 0;
        }
        else if ((int) o1.get("logins") < (int) o2.get("logins")){
            return 1;
        }
        else {
            return -1;
        }
    }
}
