package me.richardcollins.economy;

import me.richardcollins.tools.custom.chat.BorderedMessage;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Helper extends me.richardcollins.tools.Tools {
    public static void sendEconomyInfo(Player player) {
        BorderedMessage bm = new BorderedMessage("Economy/Market Info!");
        bm.add("");

        if (Bukkit.getPluginManager().isPluginEnabled("MyMarket")) {
            bm.add(ChatColor.GREEN + "/market " + ChatColor.AQUA + "- Open the market.");
            bm.add(ChatColor.GREEN + "/buy " + ChatColor.AQUA + "- Buy something from the market.");
            bm.add(ChatColor.GREEN + "/sell " + ChatColor.AQUA + "- Sell something on the market.");
            bm.add(ChatColor.GREEN + "/price " + ChatColor.AQUA + "- Check the price to buy items.");
            bm.add("");
        }

        bm.add(ChatColor.GREEN + "/money " + ChatColor.AQUA + "- Display amount of gold ingots in your bank.");
        bm.add(ChatColor.GREEN + "/deposit " + ChatColor.AQUA + "- Deposit gold ingots into your bank.");
        bm.add(ChatColor.GREEN + "/withdraw " + ChatColor.AQUA + "- Withdraw gold ingots from your bank.");
        bm.add(ChatColor.GREEN + "/pay " + ChatColor.AQUA + "- Send money to another player.");
        bm.add("");
        bm.add(ChatColor.GREEN + "For more info, goto " + ChatColor.AQUA + "economy.nerdsnetwork.net");
        bm.add("");

        bm.sendMessage(player);
    }
}
