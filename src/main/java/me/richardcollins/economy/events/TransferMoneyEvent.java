package me.richardcollins.economy.events;

import me.richardcollins.economy.objects.Profile;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class TransferMoneyEvent extends Event implements Cancellable {
	private static final HandlerList handlers = new HandlerList();
	private Profile from, to;
	private double amount;
	private boolean cancelled = false;

	public TransferMoneyEvent(Profile f, Profile t, double a) {
		from = f;
		to = t;
		amount = a;
	}

	public Profile getFrom() {
		return from;
	}

	public void setFrom(Profile from) {
		this.from = from;
	}

	public Profile getTo() {
		return to;
	}

	public void setTo(Profile to) {
		this.to = to;
	}

	public boolean isCancelled() {
		return cancelled;
	}

	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
}
