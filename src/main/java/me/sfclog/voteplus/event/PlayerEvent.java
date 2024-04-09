package me.sfclog.voteplus.event;

import me.sfclog.voteplus.Main;
import me.sfclog.voteplus.playerdata.PlayerDataManage;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerEvent implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if(p != null) {
            PlayerDataManage.add(p);
        }
    }
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        if(p != null) {
            PlayerDataManage.remove(p);
        }
    }
}
