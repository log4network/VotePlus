package me.sfclog.voteplus.playerdata;

import me.sfclog.voteplus.Main;
import me.sfclog.voteplus.votemanage.MineMP;
import me.sfclog.voteplus.votemanage.Status;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class PlayerDataManage {

    public static HashMap<String,PlayerData> map = new HashMap<>();

    public static void add(Player p) {
        Bukkit.getScheduler().runTaskAsynchronously(Main.pl, new Runnable() {
        @Override
        public void run() {
            Status status = Main.getMineMP().get_status_vote(p.getName());
            if(status != null) {
                PlayerData data = new PlayerData(status);
                data.send(p);
                map.put(p.getName(),data);
            }
        }
      });
    }

    public static void remove(Player p) {
        if (map.containsKey(p.getName())) {
            map.remove(p.getName());
        }
    }

    public static PlayerData getData(Player p) {
        if (map.containsKey(p.getName())) {
            return map.get(p.getName());
        }
        return null;
    }


}
