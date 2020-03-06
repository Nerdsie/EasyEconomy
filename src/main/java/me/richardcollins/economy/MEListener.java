package me.richardcollins.economy;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class MEListener implements Listener {
	Economy plugin;

	public MEListener(Economy p) {
		plugin = p;
	}

	@EventHandler
	public void onLogin(PlayerLoginEvent e) {
		Player player = e.getPlayer();

		if(e.getResult() == PlayerLoginEvent.Result.ALLOWED && player != null && player.getName() != null){
			plugin.createProfile(player.getName());
		}
	}
}