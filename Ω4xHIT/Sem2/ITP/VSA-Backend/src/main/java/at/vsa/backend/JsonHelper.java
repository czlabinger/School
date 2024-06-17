package at.vsa.backend;

import org.json.JSONObject;

import java.util.HashMap;

public class JsonHelper {

    public static <K, V> String toJSON(HashMap<K,V> map) {
        return new JSONObject(map).toString();
    }

}
