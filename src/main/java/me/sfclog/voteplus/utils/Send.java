package me.sfclog.voteplus.utils;



import me.sfclog.voteplus.config.PluginConfig;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class Send {


	public static void send(Player p,String s ,Sound so) {
		p.sendMessage(" ");
		p.sendMessage(PluginConfig.getlang("Lang.Prefix") + s);
		p.sendMessage(" ");
		p.playSound(p.getLocation(), so, 50, 1);

	}
}
