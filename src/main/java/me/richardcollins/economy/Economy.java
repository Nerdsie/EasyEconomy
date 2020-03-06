package me.richardcollins.economy;

import me.richardcollins.economy.api.EconomyAPI;
import me.richardcollins.economy.commands.BalanceCommand;
import me.richardcollins.economy.commands.EconomyCommand;
import me.richardcollins.economy.commands.PayCommand;
import me.richardcollins.economy.objects.Profile;
import me.richardcollins.economy.storage.DataManager;
import me.richardcollins.economy.storage.MySQL;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class Economy extends JavaPlugin implements EconomyAPI {
    public DataManager dataManager = null;

    public void onEnable() {
        getCommand("economy").setExecutor(new EconomyCommand(this));
        getCommand("pay").setExecutor(new PayCommand(this));
        getCommand("balance").setExecutor(new BalanceCommand(this));

        getServer().getPluginManager().registerEvents(new MEListener(this), this);

        Settings.load(this);

        dataManager = new DataManager(new MySQL());
    }

    public void onDisable() {
        dataManager.core.close();
    }

    private ArrayList<Profile> profiles = new ArrayList<Profile>();

    public DataManager getDataManager() {
        return dataManager;
    }

    public MySQL getCore() {
        return getDataManager().core;
    }

    public Profile getProfile(String name) {
        for (Profile p : profiles) {
            if (p.getName().equalsIgnoreCase(name)) {
                return p;
            }
        }

        return createProfile(name);
    }

    @Override
    public Profile getProfile(Player player) {
        return getProfile(player.getName());
    }

    public Profile createProfile(String name) {
        if (!profileExists(name)) {
            Profile toAdd = new Profile(name, this);

            try {
                if (!dataManager.core.playerExists(name)) {
                    dataManager.addPlayer(name);
                }
            } catch (Exception e) {

            }

            profiles.add(toAdd);

            return toAdd;
        } else {
            return getProfile(name);
        }
    }

    public boolean profileExists(String name) {
        for (Profile p : profiles) {
            if (p.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean profileExists(Player player) {
        return profileExists(player.getName());
    }

    public void toMySQL(Profile p) {
        dataManager.updateBalance(p.getName(), p.balance);
    }

    public void fromMySQL(Profile p) {
        if (dataManager.core.playerExists(p.getName()))
            p.balance = dataManager.getBalance(p.getName());
    }

    public static EconomyAPI getAPI() {
        return (EconomyAPI) Bukkit.getPluginManager().getPlugin("Economy");
    }
}
