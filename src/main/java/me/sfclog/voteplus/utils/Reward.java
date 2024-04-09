package me.sfclog.voteplus.utils;

import me.sfclog.voteplus.config.PluginConfig;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import java.util.List;

public class Reward {

    public static void send_reward(Player p) {
        p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE,100,1);
        List<String> command = PluginConfig.getarray("Vote.Commands");
        if(command != null) {
            for(String cmd : command) {
                if(cmd != null) {
                    Command.dispatchCommand(p,cmd);
                }
            }
        }
    }
}
