package me.richardcollins.economy.commands;

import me.richardcollins.economy.Helper;
import me.richardcollins.economy.objects.Action;
import me.richardcollins.economy.Economy;
import me.richardcollins.economy.objects.Profile;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EconomyCommand implements CommandExecutor {
	Economy plugin;

	public EconomyCommand(Economy nerdsEconomy) {
		plugin = nerdsEconomy;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
		if (args.length < 3) {
			if (sender instanceof Player) {
				Helper.sendEconomyInfo((Player) sender);
				return true;
			}

			return false;
		}

		Action action;
		action = Action.valueOf(args[0].toUpperCase());

		double amount = 0.0;

		try {
			amount = Double.parseDouble(args[2]);
		} catch (Exception e) {
			sender.sendMessage(ChatColor.RED + "Error: Amount supplied is invalid.");
			return true;
		}

		Profile profile = plugin.getProfile(args[1]);

		if (action == Action.GIVE || action == Action.ADD) {
			profile.addGold(amount);
			sender.sendMessage(ChatColor.AQUA + "" + amount + ChatColor.GREEN + " given to " + ChatColor.AQUA + args[1]);
		} else if (action == Action.SET) {
			profile.setBalance(amount);
			sender.sendMessage(ChatColor.AQUA + "" + args[1] + "'s" + ChatColor.GREEN + " balance set to " + ChatColor.AQUA + amount);
		} else if (action == Action.TAKE || action == Action.SUBTRACT || action == Action.REMOVE) {
			profile.removeGold(amount);
			sender.sendMessage(ChatColor.AQUA + "" + amount + ChatColor.GREEN + " taken from " + ChatColor.AQUA + args[1]);
		}

		return true;
	}
}
