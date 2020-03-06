package me.richardcollins.economy.events;

import me.richardcollins.economy.objects.Profile;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class AddMoneyEvent extends Event implements Cancellable {
	private static final HandlerList handlers = new HandlerList();
	private Profile profile;
	private double amount;
	private boolean cancelled = false;

	public AddMoneyEvent(Profile profile, double add) {
		this.profile = profile;
		amount = add;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public boolean isCancelled() {
		return cancelled;
	}

	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}

	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
}
