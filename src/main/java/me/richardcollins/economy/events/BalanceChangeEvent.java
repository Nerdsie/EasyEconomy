package me.richardcollins.economy.events;

import me.richardcollins.economy.objects.Profile;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class BalanceChangeEvent extends Event implements Cancellable {
	private static final HandlerList handlers = new HandlerList();
	private Profile profile;
	private double before, after;
	private boolean cancelled = false;

	public BalanceChangeEvent(Profile profile, double b, double a) {
		this.profile = profile;
		before = b;
		after = a;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

	public double getBefore() {
		return before;
	}

	public double getAfter() {
		return after;
	}

	public void setAfter(double after) {
		this.after = after;
	}

	public boolean isCancelled() {
		return cancelled;
	}

	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}
}
