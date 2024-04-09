package me.sfclog.voteplus.papi;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.sfclog.voteplus.config.PluginConfig;
import me.sfclog.voteplus.playerdata.PlayerData;
import me.sfclog.voteplus.playerdata.PlayerDataManage;
import me.sfclog.voteplus.votemanage.Status;
import org.bukkit.entity.Player;

public class PAPI extends PlaceholderExpansion {
    public String onPlaceholderRequest(Player p, String identifier) {
        if (identifier != null) {
            if(identifier.equals("status")) {
                PlayerData data = PlayerDataManage.getData(p);
                if (data != null) {
                    return tran(data);
                }
            }
        }
        return "...";
    }

    public static String tran(PlayerData data) {
        Status status = data.getStatus();
        if(status == Status.IS_CLAIM) {
            return PluginConfig.getlang("Lang.Status.IsVote");
        } else if(status == Status.VOTE_NOT_CLAIM) {
            return PluginConfig.getlang("Lang.Status.ClaimVote");
        } else {
            return PluginConfig.getlang("Lang.Status.NotVote");
        }
    }
    public String getIdentifier() {
        return "vote";
    }

    public String getAuthor() {
        return "SFC_Log";
    }

    public String getVersion() {
        return "1.0";
    }

}