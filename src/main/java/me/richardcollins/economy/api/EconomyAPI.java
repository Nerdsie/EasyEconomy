package me.richardcollins.economy.api;

import me.richardcollins.economy.objects.Profile;
import org.bukkit.entity.Player;

public interface EconomyAPI {
    public abstract Profile getProfile(String name);

    public Profile getProfile(Player player);

    public abstract boolean profileExists(String name);

    public boolean profileExists(Player player);
}