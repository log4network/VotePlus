package me.sfclog.voteplus.gui;


import me.sfclog.voteplus.Main;
import me.sfclog.voteplus.config.PluginConfig;
import me.sfclog.voteplus.playerdata.PlayerData;
import me.sfclog.voteplus.playerdata.PlayerDataManage;
import me.sfclog.voteplus.utils.Color;
import me.sfclog.voteplus.utils.Reward;
import me.sfclog.voteplus.utils.Send;
import me.sfclog.voteplus.votemanage.Status;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class VoteGui implements Listener {

    @EventHandler
    public void onEvent(InventoryCloseEvent e) {
        if(e.getView() != null && e.getView().getTitle() != null && e.getView().getTitle().equals(PluginConfig.getlang("Gui.VoteGui.Title"))) {
            Player p = (Player) e.getPlayer();
            p.playSound(p.getLocation(), Sound.BLOCK_ENDER_CHEST_CLOSE,100,1);
        }
    }
    @EventHandler
    public void onEvent(InventoryClickEvent e) {
        if(e.getView() != null && e.getView().getTitle() != null && e.getView().getTitle().equals(PluginConfig.getlang("Gui.VoteGui.Title"))) {
            e.setCancelled(true);
            Player p = (Player) e.getWhoClicked();
            int slot = e.getSlot();
            if(slot == 2) {
                PlayerData data = PlayerDataManage.getData(p);
                if(data != null) {
                    data.claim(p);
                } else {
                    p.closeInventory();
                }
            }
            p.closeInventory();
        }
    }
    public static void open(Player p) {

        PlayerData data = PlayerDataManage.getData(p);
        p.playSound(p.getLocation(), Sound.BLOCK_ENDER_CHEST_OPEN,100,1);

        Inventory inv = Bukkit.createInventory(null, InventoryType.HOPPER, PluginConfig.getlang("Gui.VoteGui.Title"));

        ItemStack black = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta blackmeta = black.getItemMeta();
        blackmeta.setDisplayName(" ");
        blackmeta.addItemFlags(ItemFlag.values());
        black.setItemMeta(blackmeta);

        inv.setItem(0,black);
        inv.setItem(1,black);
        inv.setItem(3,black);
        inv.setItem(4,black);

        if(data != null ) {

            Status status = data.getStatus();

            if(status == Status.NOT_VOTE) {
                ItemStack notvote = new ItemStack(Material.CHEST_MINECART);
                ItemMeta notvote_meta = black.getItemMeta();
                notvote_meta.setDisplayName(Color.tran("&a&lBỎ PHIẾU"));
                List<String> lore = new ArrayList<>();
                lore.add(" ");
                lore.add(Color.tran("&fThông tin:"));
                lore.add(" ");
                lore.add(Color.tran(" &7Bỏ phiếu máy chủ là một hình"));
                lore.add(Color.tran(" &7thức phổ biến máy chủ rộng rãi"));
                lore.add(Color.tran(" &7trên các trang quảng cáo máy chủ"));
                lore.add(Color.tran(" &7và bạn sẽ nhận được phần quà khi"));
                lore.add(Color.tran(" &7bỏ phiếu thành công."));
                lore.add(" ");
                lore.add(Color.tran(" &fTrạng Thái&f: &aChưa Bỏ Phiếu"));
                lore.add(" ");
                lore.add(Color.tran(" &eClick Để Vote"));
                lore.add(" ");
                notvote_meta.setLore(lore);
                notvote.setItemMeta(notvote_meta);
                inv.setItem(2, notvote);

            } else   if(status == Status.VOTE_NOT_CLAIM) {

                ItemStack isvote = new ItemStack(Material.TNT_MINECART);
                ItemMeta isvote_meta = black.getItemMeta();
                isvote_meta.setDisplayName(Color.tran("&e&lNHẬN THƯỞNG"));
                List<String> lore1 = new ArrayList<>();
                lore1.add(" ");
                lore1.add(Color.tran("&fThông tin:"));
                lore1.add(" ");
                lore1.add(Color.tran(" &7Bỏ phiếu máy chủ là một hình"));
                lore1.add(Color.tran(" &7thức phổ biến máy chủ rộng rãi"));
                lore1.add(Color.tran(" &7trên các trang quảng cáo máy chủ"));
                lore1.add(Color.tran(" &7và bạn sẽ nhận được phần quà khi"));
                lore1.add(Color.tran(" &7bỏ phiếu thành công."));
                lore1.add(" ");
                lore1.add(Color.tran(" &fTrạng Thái&f: &aNhận Thưởng"));
                lore1.add(" ");
                lore1.add(Color.tran(" &eClick Để Nhận Thưởng"));
                lore1.add(" ");
                isvote_meta.setLore(lore1);
                isvote.setItemMeta(isvote_meta);
                inv.setItem(2,isvote);

            } else if(status == Status.IS_CLAIM) {
                ItemStack isvote = new ItemStack(Material.HOPPER_MINECART);
                ItemMeta isvote_meta = black.getItemMeta();
                isvote_meta.setDisplayName(Color.tran("&c&lĐÃ BỎ PHIẾU"));
                List<String> lore1 = new ArrayList<>();
                lore1.add(" ");
                lore1.add(Color.tran("&fThông tin:"));
                lore1.add(" ");
                lore1.add(Color.tran(" &7Bỏ phiếu máy chủ là một hình"));
                lore1.add(Color.tran(" &7thức phổ biến máy chủ rộng rãi"));
                lore1.add(Color.tran(" &7trên các trang quảng cáo máy chủ"));
                lore1.add(Color.tran(" &7và bạn sẽ nhận được phần quà khi"));
                lore1.add(Color.tran(" &7bỏ phiếu thành công."));
                lore1.add(" ");
                lore1.add(Color.tran(" &fTrạng Thái&f: &aĐã Bỏ Phiếu"));
                lore1.add(" ");
                lore1.add(Color.tran(" &4Bạn đã bỏ phiếu rồi"));
                lore1.add(Color.tran(" &7(Có thể bỏ phiếu lại trong 24h)"));
                lore1.add(" ");
                isvote_meta.setLore(lore1);
                isvote.setItemMeta(isvote_meta);
                inv.setItem(2,isvote);
            }

        }


        p.openInventory(inv);
    }
}
