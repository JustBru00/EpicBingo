package com.gmail.justbru00.epic.bingo.playerdata;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public abstract class BingoObjective {

	private BingoObjectiveType type;
	private boolean complete = false;
	private boolean failed = false;
	
	public BingoObjective(BingoObjectiveType type) {
		super();
	}
	
	public boolean isComplete() {
		return complete;
	}
	
	public boolean isFailed() {
		return failed;
	}	
	
	public BingoObjectiveType getType() {
		return type;
	}

	public void setComplete(boolean complete) {
		this.complete = complete;
	}

	public void setFailed(boolean failed) {
		this.failed = failed;
	}

	/**
	 * Used to ask this objective to check all a certain user.
	 * This method should not take very long to complete.
	 */
	public abstract void check(Player player);
	
	/**
	 * This method notifies the objective of some item action that was detected by a listener.
	 * Eg. Right clicking an item.
	 */
	public abstract void notifyOfItemDetection(ItemDetectionType type, ItemStack item, Player player);
	
}
