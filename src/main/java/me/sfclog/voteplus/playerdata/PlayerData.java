package me.sfclog.voteplus.playerdata;

import me.sfclog.voteplus.Main;
import me.sfclog.voteplus.config.PluginConfig;
import me.sfclog.voteplus.utils.Reward;
import me.sfclog.voteplus.utils.Send;
import me.sfclog.voteplus.votemanage.MineMP;
import me.sfclog.voteplus.votemanage.Status;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.io.FileNotFoundException;

public class PlayerData {

    public Status status;

    public PlayerData(Status status) {
        this.status = status;
    }

    public void send(Player p) {
        //send tin nhắn kêu player vote khi người chơi chưa vote
        if(this.status == Status.NOT_VOTE) {
            Send.send(p,PluginConfig.getlang("Lang.SendVote"),Sound.ENTITY_VILLAGER_TRADE);
        } else if(this.status == Status.VOTE_NOT_CLAIM) {
            Send.send(p,PluginConfig.getlang("Lang.ClaimVote"),Sound.ENTITY_VILLAGER_TRADE);
        }
    }
    public void claim(Player p) {

        //update status
        Bukkit.getScheduler().runTaskAsynchronously(Main.pl, new Runnable() {
            @Override
            public void run() {
                status = Main.getMineMP().get_status_vote(p.getName());

                if (!(status == Status.IS_CLAIM)) {
                    if (status == Status.VOTE_NOT_CLAIM) {
                        //claim
                        if (Main.getMineMP().claim(p.getName())) {
                            status = Status.IS_CLAIM;
                            Send.send(p, PluginConfig.getlang("Lang.VoteClaim"), Sound.ENTITY_VILLAGER_TRADE);
                            Bukkit.getScheduler().runTask(Main.pl, () -> {
                                Reward.send_reward(p);
                            });
                        } else {
                            Send.send(p, PluginConfig.getlang("Lang.ClaimError"), Sound.ENTITY_VILLAGER_NO);
                        }
                    } else {
                        Send.send(p, PluginConfig.getlang("Lang.VoteStart"), Sound.ENTITY_VILLAGER_YES);
                    }

                } else {
                    Send.send(p, PluginConfig.getlang("Lang.VoteStart"), Sound.ENTITY_VILLAGER_YES);
                }

            }
      });
    }





    public Status getStatus() {
        return this.status;
    }


}
