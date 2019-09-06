package com.gmail.justbru00.epic.bingo.playerdata.objectives;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.gmail.justbru00.epic.bingo.game.BingoObjectivesManager;
import com.gmail.justbru00.epic.bingo.playerdata.BingoObjective;
import com.gmail.justbru00.epic.bingo.playerdata.BingoObjectiveType;
import com.gmail.justbru00.epic.bingo.playerdata.ItemDetectionType;

public class GatherItemObjective extends BingoObjective {
	
	private Material material;
	private int amount;

	public GatherItemObjective(Material _material, int _amount) {
		super(BingoObjectiveType.GATHER_ITEMS);
		material = _material;
		amount = _amount;		
	}

	@Override
	public void check(Player player) {
		if (BingoObjectivesManager.isTrackingObjectives() || isComplete() || isFailed()) { 
			return;
		}
		
		// Check this player's inventory for the item.
		
		Inventory inventory = player.getInventory();
		
		for (ItemStack item : inventory.getContents()) {
			if (item.getType().equals(material)) {
				if (item.getAmount() >= amount) {
					setComplete(true);
					break;
				}
			}
		}

	}

	@Override
	public void notifyOfItemDetection(ItemDetectionType type, ItemStack item, Player player) {		
		if (type.equals(ItemDetectionType.PICKUP_ITEM)) {
			if (item.getType().equals(material)) {
				if (!isComplete() && !isFailed()) {
					check(player);
				}
			}			
		}
		

	}

}
