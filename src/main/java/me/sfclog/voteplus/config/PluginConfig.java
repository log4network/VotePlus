package me.sfclog.voteplus.config;

import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PluginConfig {

    public static File locate = new File("plugins/VotePlus/", "config.yml");
    public static FileConfiguration DataFile = (FileConfiguration) YamlConfiguration.loadConfiguration(locate);


    public static void loadlang() {

        addlang("VoteServer.Mine-MP.KeyAPI","NzWTzjJ0WegAUo0uRlAAMXQTRtfNzyjgY");
        addlang("Gui.VoteGui.Title","&c&lBỎ PHIẾU MÁY CHỦ");

        List<String> command = new ArrayList<>();
        command.add("console:eco give {player} 1000");
        command.add("console:bc &fChúc mừng! Người chơi {player} đã nhận được quà vì đã bỏ phiếu cho Máy Chủ &a/warp vote &fđể tiến hành bỏ phiếu.");
        addlang("Vote.Commands",command);
        addlang("Lang.Prefix","&8(&eBỏ Phiếu&8) ");
        addlang("Lang.VoteStart","&aTruy cập &fhttps://minecraft-mp.com/server-s310729 &ađể bỏ phiếu cho máy chủ.");
        addlang("Lang.IsVote","&cLỗi &fBạn đã bỏ phiếu rồi, bạn chỉ có thể bỏ phiếu lại sau 24h nữa.");
        addlang("Lang.Status.NotVote","&aChưa Bỏ Phiếu");
        addlang("Lang.Status.ClaimVote","&eNhận Thưởng");
        addlang("Lang.Status.IsVote","&cĐã Bỏ Phiếu");
        addlang("Lang.SendVote","&fHình như bạn chưa Vote cho máy chủ? &e/vote &fđể nhận những phần quà hấp dẫn.");
        addlang("Lang.ClaimVote","&fHình như bạn chưa nhận quà Vote? &e/vote &fđể nhận quà ngay nào.");
        addlang("Lang.VoteClaim","&2Bạn đã Vote thành công");
        addlang("Lang.ClaimError","&cĐã xảy ra sự cố khi nhận phần thưởng. Vui lòng chờ ít phút!");

        try{
            DataFile.save(locate);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void save() {
        try {
            DataFile.save(locate);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static List<String> getarray(String s) {
        if(DataFile.contains(s)) {
            List<String> ss = new ArrayList<String>();
            for(String ok : DataFile.getStringList(s)) {
                ss.add(ChatColor.translateAlternateColorCodes('&',ok));
            }
            return ss;
        }
        return null;
    }
    public static int getint(String s) {
        if(DataFile.contains(s)) {
            return DataFile.getInt(s);
        }
        return 0;
    }
    public static double getdoubl(String s) {
        if(DataFile.contains(s)) {
            return DataFile.getDouble(s);
        }
        return 0;
    }
    public static boolean getb(String s) {
        if(DataFile.contains(s)) {
            return DataFile.getBoolean(s);
        }
        return false;
    }
    public static String getlang(String s) {
        if(DataFile.contains(s)) {
            return ChatColor.translateAlternateColorCodes('&', DataFile.getString(s));
        }
        return s;
    }
    public static void addlang(String s , double s2) {
        if(!DataFile.contains(s)) {
            DataFile.set(s, s2);
        }
    }
    public static void addlang(String s , Boolean s2) {
        if(!DataFile.contains(s)) {
            DataFile.set(s, s2);
        }
    }
    public static void reload(Player p) {
        try {
            DataFile.load(locate);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }
    public static void addlang(String s , List<String> s2) {
        if(!DataFile.contains(s)) {
            DataFile.set(s, s2);
        }
    }
    public static void setforcelang(String s , String s2) {
        DataFile.set(s, s2);
        save();
    }
    public static void setforcelang(String s, double x) {
        DataFile.set(s, x);
        save();
    }
    public static void addlang(String s , String s2) {
        if(!DataFile.contains(s)) {
            DataFile.set(s, s2);
        }
    }
    public static void addlang(String s , int s2) {
        if(!DataFile.contains(s)) {
            DataFile.set(s, s2);
        }
    }



}
