package com.gmail.justbru00.epic.bingo.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryPickupItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.InventoryHolder;

import com.gmail.justbru00.epic.bingo.game.BingoObjectivesManager;
import com.gmail.justbru00.epic.bingo.playerdata.ItemDetectionType;

public class ItemListener implements Listener {

	@EventHandler
	public void onItemPickupListener(InventoryPickupItemEvent e) {
		InventoryHolder holder = e.getInventory().getHolder();
		if (holder != null) {
			Player player = (Player) holder;
			BingoObjectivesManager.notifyAllOfItemDetection(ItemDetectionType.PICKUP_ITEM, e.getItem().getItemStack(), player);
		}		
	}
	
	@EventHandler
	public void onInteractListener(PlayerInteractEvent e) {
		if (e.getItem() != null) {
			BingoObjectivesManager.notifyAllOfItemDetection(ItemDetectionType.INTERACTED_WITH_ITEM, e.getItem(), e.getPlayer());
		}
		
		
	}
	
}
