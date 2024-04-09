package me.sfclog.voteplus.command;

import me.sfclog.voteplus.gui.VoteGui;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VoteCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if(p != null) {
                VoteGui.open(p);
            }
        }
        return false;
    }
}
