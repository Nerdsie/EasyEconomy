package me.richardcollins.economy.commands;

import me.richardcollins.economy.Helper;
import me.richardcollins.economy.Economy;
import me.richardcollins.economy.Settings;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PayCommand implements CommandExecutor {
    Economy plugin;

    public PayCommand(Economy nerdsEconomy) {
        plugin = nerdsEconomy;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String args[]) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Error: You must be a player to do this.");
            return true;
        }

        Player player = (Player) sender;
        String neededPerms = "myeconomy.command.pay";

        if (!sender.hasPermission(neededPerms)) {
            sender.sendMessage(ChatColor.RED + "Error: You need the '" + neededPerms + "' to do this.");
            return true;
        }

        if (args.length >= 2) {
            String sendTo = args[0];

            if (sendTo.equalsIgnoreCase(player.getName())) {
                player.sendMessage(ChatColor.RED + "Error: Cannot send money to yourself.");
                return true;
            }

            double amount = 0;

            try {
                amount = Double.parseDouble(args[1]);
            } catch (Exception e) {
                player.sendMessage(ChatColor.RED + "Error: '" + args[1] + "' is not a valid amount.");
                return true;
            }

            amount = Helper.round(amount);

            if (amount <= 0.0) {
                player.sendMessage(ChatColor.RED + "Error: Price must be positive.");
                return true;
            }

            if (amount > plugin.getDataManager().getBalance(player.getName())) {
                player.sendMessage(ChatColor.RED + "Error: " + amount + " gold exceeds the " + plugin.getDataManager().getBalance(player.getName()) + " gold you have.");
                return true;
            }

            if (plugin.getCore().playerExists(sendTo)) {
                plugin.getProfile(player.getName()).transfer(sendTo, amount);
            } else {
                player.sendMessage(ChatColor.RED + "Error: This player does not exist.");
                return true;
            }

            player.sendMessage(Settings.PREFIX + "You have paid " + ChatColor.RED + amount + ChatColor.GREEN + " to " + ChatColor.RED + sendTo);

            try {
                Player to = Bukkit.getPlayerExact(sendTo);

                if (to != null && to.isOnline()) {
                    to.sendMessage(Settings.PREFIX + "You were paid " + ChatColor.RED + amount + ChatColor.GREEN + " by " + ChatColor.RED + player.getName());
                }
            } catch (Exception ex) {

            }

            return true;
        }

        player.sendMessage(ChatColor.RED + "You can use this to pay someone else.");
        player.sendMessage(ChatColor.RED + "/pay <name> <amount>");

        return true;
    }
}
