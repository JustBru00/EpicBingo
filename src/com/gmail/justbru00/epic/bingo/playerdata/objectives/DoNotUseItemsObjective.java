package com.gmail.justbru00.epic.bingo.playerdata.objectives;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.gmail.justbru00.epic.bingo.playerdata.BingoObjective;
import com.gmail.justbru00.epic.bingo.playerdata.BingoObjectiveType;
import com.gmail.justbru00.epic.bingo.playerdata.ItemDetectionType;

public class DoNotUseItemsObjective extends BingoObjective {
	
	private ArrayList<Material> materials = new ArrayList<Material>();

	public DoNotUseItemsObjective(Material... _materials) {
		super(BingoObjectiveType.DO_NOT_USE_ITEMS);
		materials = (ArrayList<Material>) Arrays.asList(_materials);
		setComplete(true);
	}

	@Override
	public void check(Player player) {
		
		// Soooo this method doesn't apply to this type of objective.
		
	}

	@Override
	public void notifyOfItemDetection(ItemDetectionType type, ItemStack item, Player player) {
		if (type.equals(ItemDetectionType.INTERACTED_WITH_ITEM)) {
			for (Material m : materials) {
				if (item.getType().equals(m)) {
					setFailed(true);
					setComplete(false);
				}
			}
		}
		
	}
	
	

}
