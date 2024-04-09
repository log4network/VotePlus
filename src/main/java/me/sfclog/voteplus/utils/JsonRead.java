package me.sfclog.voteplus.utils;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonRead {

    public static JSONParser parser = new JSONParser();


    public static JSONObject parse(String json) {
        JSONObject jsonObject = null;
        try {
            jsonObject = (JSONObject) parser.parse(json);
            if(jsonObject!= null) {
                return jsonObject;
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
