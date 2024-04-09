package me.sfclog.voteplus.votemanage;

import me.sfclog.voteplus.Main;
import me.sfclog.voteplus.http.HTTP_Client;
import me.sfclog.voteplus.utils.JsonRead;
import org.apache.http.client.utils.URIBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MineMP {


    public static String key;


    public static String SERVER_STAT_API = "https://minecraft-mp.com/api/?object=servers&element=detail&key=<api_key>";
    //Data output map
    //Error: server key not found //Key không hợp lệ



    public static String USER_VOTE_API = "https://minecraft-mp.com/api/?object=votes&element=claim&key=<api_key>&username=<user_name>";
    // 0 Chưa bỏ phiếu
    // 1 bỏ phiếu nhưng chưa claim
    // 2 bỏ phiếu và đã claim

    public static String USER_CLAIM_API = "https://minecraft-mp.com/api/?action=post&object=votes&element=claim&key=<api_key>&username=<user_name>";
    //claim quà



    public MineMP(String key) {
        this.key = key;
        //replace key
        this.SERVER_STAT_API = this.SERVER_STAT_API.replaceAll("<api_key>",key);
        this.USER_VOTE_API = this.USER_VOTE_API.replaceAll("<api_key>",key);
        this.USER_CLAIM_API = this.USER_CLAIM_API.replaceAll("<api_key>",key);
    }

    public Status get_status_vote(String name) {
        String api = HTTP_Client.get_query(this.USER_VOTE_API.replace("<user_name>",name));
        if(api != null ) {
            if(api.contains("1")) {
                return Status.VOTE_NOT_CLAIM;
            } else if(api.contains("2")) {
                return Status.IS_CLAIM;
            } else if(api.contains("0")) {
                return Status.NOT_VOTE;
            }
        }
        return Status.IS_CLAIM;
    }
    public boolean claim(String name) {
        String api = HTTP_Client.get_query( this.USER_CLAIM_API.replace("<user_name>",name));
        return api.contains("1");
    }

    public boolean ping_api() {
        String api = HTTP_Client.get_query(SERVER_STAT_API);
        if(api != null && !api.contains("Error:")) {
            JSONObject json = JsonRead.parse(api);
            if (json != null) {
                Main.sendlog(" ");
                Main.sendlog("&fID: &e" + json.get("id"));
                Main.sendlog("&fServer: &e" + json.get("name"));
                Main.sendlog("&fRank: &e" + json.get("rank"));
                Main.sendlog("&fVotes: &e" + json.get("votes"));
                Main.sendlog(" ");
                return true;
            }
        }
        return false;
    }


}
