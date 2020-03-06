package me.richardcollins.economy.commands;

import me.richardcollins.economy.Helper;
import me.richardcollins.economy.Economy;
import me.richardcollins.economy.Settings;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BalanceCommand implements CommandExecutor {
    Economy plugin;

    public BalanceCommand(Economy nerdsEconomy) {
        plugin = nerdsEconomy;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String args[]) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Error: You must be a player to do this.");
            return true;
        }

        Player player = (Player) sender;
        String neededPerms = "myeconomy.command.balance";

        if (!sender.hasPermission(neededPerms)) {
            sender.sendMessage(ChatColor.RED + "Error: You need the '" + neededPerms + "' to do this.");
            return true;
        }

        player.sendMessage("");
        player.sendMessage(Settings.PREFIX + "Current Balance: " + ChatColor.AQUA + "" + Helper.round(plugin.getDataManager().getBalance(player.getName())) + " gold.");
        player.sendMessage("");

        return true;
    }
}
