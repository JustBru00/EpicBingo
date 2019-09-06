package com.gmail.justbru00.epic.bingo.game;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.gmail.justbru00.epic.bingo.playerdata.BingoObjective;
import com.gmail.justbru00.epic.bingo.playerdata.PlayerBingoData;

public class BingoObjectivesManager {

	private static ArrayList<PlayerBingoData> bingoPlayerData = new ArrayList<PlayerBingoData>();	
	private static ArrayList<UUID> bingoPlayers = new ArrayList<UUID>();
	private static ArrayList<UUID> finishedBingoPlayers = new ArrayList<UUID>();
	private static HashMap<UUID, Instant> bingoCompletedAt = new HashMap<UUID, Instant>();
	
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
