package me.richardcollins.economy;

import me.richardcollins.universal.Universal;
import org.bukkit.ChatColor;

public class Settings {

    public static final String PREFIX = " " + ChatColor.GREEN;

    // ------- MySQL info --------- //

    public static String database = "economy";

    public static void load(Economy plugin) {
        database = Universal.getUniversalConfig().getString("database.economy");
    }
}
