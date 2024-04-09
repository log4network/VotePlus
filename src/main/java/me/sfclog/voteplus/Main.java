package me.sfclog.voteplus;

import me.sfclog.voteplus.command.VoteCommand;
import me.sfclog.voteplus.config.PluginConfig;
import me.sfclog.voteplus.event.PlayerEvent;
import me.sfclog.voteplus.gui.VoteGui;
import me.sfclog.voteplus.http.HTTP_Client;
import me.sfclog.voteplus.papi.PAPI;
import me.sfclog.voteplus.playerdata.PlayerDataManage;
import me.sfclog.voteplus.utils.Color;
import me.sfclog.voteplus.votemanage.MineMP;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    public static HTTP_Client http;
    public static MineMP minemp;

    public static Plugin pl;

    @Override
    public void onEnable() {
        pl = this;
        PluginConfig.loadlang();
        sendlog(" ");
        sendlog("&e&lVotePlus &f| Vote Server Plugin");
        sendlog("&fAuthor: &bSFC_Log");
        sendlog(" ");
        sendlog("&aEnable the plugin");
        sendlog(" ");
        http = new HTTP_Client();
        minemp = new MineMP(PluginConfig.getlang("VoteServer.Mine-MP.KeyAPI"));

         if (minemp.ping_api()) {
                sendlog("&8(&aMine-MP&8) &aĐã kết nối thành công với máy chủ Mine-MP");
              } else {
                sendlog("&8(&aMine-MP&8) &cLỗi &eAPI Key không hợp lệ hoặc máy chủ đã bị tắt");
         }


         for(Player p : Bukkit.getOnlinePlayers()) {
             if(p != null) {
                 PlayerDataManage.add(p);
             }
         }
        new PAPI().register();
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerEvent(),this);
        Bukkit.getServer().getPluginManager().registerEvents(new VoteGui(),this);

        Bukkit.getServer().getPluginCommand("vote").setExecutor(new VoteCommand());



    }


    public static MineMP getMineMP() {
        return minemp;
    }
    public static HTTP_Client getHTTP() {
        return http;
    }

    @Override
    public void onDisable() {
        new PAPI().unregister();
        sendlog(" ");
        sendlog("&e&lVotePlus &f| Vote Server Plugin");
        sendlog("&fAuthor: &bSFC_Log");
        sendlog(" ");
        sendlog("&cDisable the plugin");
        sendlog(" ");
        pl = null;
    }

    public static void sendlog(String msg) {
        Bukkit.getConsoleSender().sendMessage(Color.tran(msg));
    }
    public static void sendlog2(String api) {
        Bukkit.getConsoleSender().sendMessage(api);
    }
}
