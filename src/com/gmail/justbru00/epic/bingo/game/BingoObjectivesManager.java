package com.gmail.justbru00.epic.bingo.game;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.gmail.justbru00.epic.bingo.playerdata.BingoObjective;
import com.gmail.justbru00.epic.bingo.playerdata.ItemDetectionType;
import com.gmail.justbru00.epic.bingo.playerdata.PlayerBingoData;

public class BingoObjectivesManager {

	private static ArrayList<PlayerBingoData> bingoPlayerData = new ArrayList<PlayerBingoData>();	
	private static ArrayList<UUID> bingoPlayers = new ArrayList<UUID>();
	private static ArrayList<UUID> finishedBingoPlayers = new ArrayList<UUID>();
	private static HashMap<UUID, Instant> bingoCompletedAt = new HashMap<UUID, Instant>();
	
	public static void notifyAllOfItemDetection(ItemDetectionType type, ItemStack item, Player player) {
		for (PlayerBingoData pbd : bingoPlayerData) {
			for (BingoObjective objective : pbd.getObjectives()) {
				objective.notifyOfItemDetection(type, item, player);
			}
		}
	}
	
	/**
	 * This variable stores the list of the objectives for this current game.
	 */
	private static ArrayList<BingoObjective> bingoObjectives = new ArrayList<BingoObjective>();
	
	private static boolean trackingObjectives = false;
	
	public static void startTrackingObjectives() {
		trackingObjectives = true;
	}
	
	public static void stopTrackingObjectives() {
		trackingObjectives = false;
	}
	
	/**
	 * This method clears all stored data from all players.
	 * This method also clears the list of objectives for this game.
	 */
	public static void resetAllData() {
		stopTrackingObjectives();
		bingoPlayerData.clear();
		bingoPlayers.clear();
		finishedBingoPlayers.clear();
		bingoCompletedAt.clear();
		bingoObjectives.clear();
	}
	
	/**
	 * This method generates brand new objectives for a new game.
	 * This method does not add any players to be tracked with these objectives.
	 */
	public static void generateNewObjectives() {
		// TODO Read from list in the config.
		// TODO Randomize based on difficulty of the board
		
		// BINGO BOARD DESIGN
		// 2 1 1 1 2
		// 1 1 2 1 1
		// 1 2 3 2 1
		// 1 1 2 1 1
		// 2 1 1 1 2
		// 1 = easy, 2 = medium, 3 = hard
		
	}

	public static void updateFinishedBingoPlayers() {		
		for (PlayerBingoData pbd : bingoPlayerData) {
			if (finishedBingoPlayers.contains(pbd.getPlayerUuid())) {
				continue;
			}
			
			ArrayList<Boolean> completedSuccessfully = new ArrayList<Boolean>();
			
			for (BingoObjective bo : pbd.getObjectives()) {
				completedSuccessfully.add((bo.isComplete() && !bo.isFailed()));
			}
			
			// The most inefficient way to do the following, but I can't think of a better way right now.
			// 00 01 02 03 04 
			// 05 06 07 08 09
			// 10 11 12 13 14
			// 15 16 17 18 19
			// 20 21 22 23 24			
			
			Boolean[] bingoCard = (Boolean[]) completedSuccessfully.toArray();
			
			// ROWS
			Integer[] rowNumbers = {0, 1, 2, 3, 4};
			boolean rowTracker = false;
			
			for (int i = 0; i < 21; i = i+5) {				
				for (Integer slotNum : rowNumbers) {
					rowTracker = bingoCard[slotNum + i];
				}
			
				if (rowTracker) {
					// Row is totally TRUE
					finishedBingoPlayers.add(pbd.getPlayerUuid());
					continue;
				}
			
			}
			
			// COLUMNS
			Integer[] columnNumbers = {0, 5, 10, 15, 20};
			boolean columnTracker = false;
			
			for (int i = 0; i < 25; i = i++) {				
				for (Integer slotNum : columnNumbers) {
					columnTracker = bingoCard[slotNum + i];
				}
			
				if (columnTracker) {
					// Column is totally TRUE
					finishedBingoPlayers.add(pbd.getPlayerUuid());
					continue;
				}
			
			}
			
			// DIAGONAL
			Integer[] diagonalOne = {0, 6, 12, 18, 24};
			Integer[] diagonalTwo = {4, 8, 12, 16, 20};
			boolean diagonalTracker = false;
			
			for (Integer slotNum : diagonalOne) {
				diagonalTracker = bingoCard[slotNum];
			}
			
			if (diagonalTracker) {
				// Diagonal Row DONE
				finishedBingoPlayers.add(pbd.getPlayerUuid());
				continue;
			}
			
			for (Integer slotNum : diagonalTwo) {
				diagonalTracker = bingoCard[slotNum];
			}
			
			if (diagonalTracker) {
				// Diagonal Row DONE
				finishedBingoPlayers.add(pbd.getPlayerUuid());
				continue;
			}
			
		}		
		
	}
	
	/**
	 * This method will attempt to add a new player to the objective tracking.
	 * @param p
	 * @return Returns true if successfully. False if the we are already tracking objectives.
	 */
	public static boolean addPlayer(Player p) {
		if (trackingObjectives) {
			return false;
		}
		bingoPlayers.add(p.getUniqueId());
		bingoPlayerData.add(new PlayerBingoData(p.getUniqueId(), bingoObjectives));
		return true;
	}
	
	/**
	 * This method should be run on a timer every 10 seconds or so.
	 */
	public static void runAllObjectiveChecks() {
		if (BingoObjectivesManager.isTrackingObjectives()) { 
			return;
		}
		
		for (PlayerBingoData pbg : bingoPlayerData) {
			for (BingoObjective bingoObjective : pbg.getObjectives()) {
				bingoObjective.check(Bukkit.getPlayer(pbg.getPlayerUuid()));
			}
		}
	}

	public static ArrayList<PlayerBingoData> getBingoPlayerData() {
		return bingoPlayerData;
	}

	public static ArrayList<UUID> getBingoPlayers() {
		return bingoPlayers;
	}

	public static ArrayList<UUID> getFinishedBingoPlayers() {
		return finishedBingoPlayers;
	}

	public static HashMap<UUID, Instant> getBingoCompletedAt() {
		return bingoCompletedAt;
	}

	public static boolean isTrackingObjectives() {
		return trackingObjectives;
	}
	
	
	
	
	
	
	
}
