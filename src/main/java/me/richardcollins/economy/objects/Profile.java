package me.richardcollins.economy.objects;

import me.richardcollins.economy.Helper;
import me.richardcollins.economy.Economy;
import me.richardcollins.economy.events.AddMoneyEvent;
import me.richardcollins.economy.events.BalanceChangeEvent;
import me.richardcollins.economy.events.RemoveMoneyEvent;
import me.richardcollins.economy.events.TransferMoneyEvent;
import org.bukkit.Bukkit;

public class Profile {
    private Economy plugin;

    private String name = "";
    public double balance = 0.0;

    public Profile(String n, Economy pl) {
        name = n;

        plugin = pl;
    }

    public void setBalance(double b) {
        b = Helper.round(b);

        if (b < 0.0) {
            b = 0.0;
        }

        BalanceChangeEvent event = new BalanceChangeEvent(this, getBalance(), b);
        Bukkit.getServer().getPluginManager().callEvent(event);

        if (!event.isCancelled()) {
            balance = event.getAfter();

            plugin.toMySQL(this);
        }
    }

    public double getBalance() {
        plugin.fromMySQL(this);

        return balance;
    }

    public String getName() {
        return name;
    }

    public double addGold(double add) {
        add = Helper.round(add);

        AddMoneyEvent event = new AddMoneyEvent(this, add);
        Bukkit.getServer().getPluginManager().callEvent(event);

        if (!event.isCancelled()) {
            event.getProfile().setBalance(event.getProfile().getBalance() + event.getAmount());
        }

        return balance;
    }

    public double removeGold(double rem) {
        rem = Helper.round(rem);

        RemoveMoneyEvent event = new RemoveMoneyEvent(this, rem);
        Bukkit.getServer().getPluginManager().callEvent(event);

        if (!event.isCancelled()) {
            event.getProfile().setBalance(event.getProfile().getBalance() - event.getAmount());
        }

        return balance;
    }

    public void transfer(Profile to, double move) {
        move = Helper.round(move);

        TransferMoneyEvent event = new TransferMoneyEvent(this, to, move);
        Bukkit.getServer().getPluginManager().callEvent(event);

        if (!event.isCancelled()) {
            event.getFrom().removeGold(event.getAmount());
            event.getTo().addGold(event.getAmount());
        }
        if (to != null) {
            removeGold(move);

            to.addGold(move);
        }
    }

    public void transfer(String goTo, double move) {
        move = Helper.round(move);

        Profile to = plugin.getProfile(goTo);

        if (to != null) {
            removeGold(move);

            to.addGold(move);
        }
    }
}
