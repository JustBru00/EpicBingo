package com.gmail.justbru00.epic.bingo.playerdata;

import java.util.ArrayList;
import java.util.UUID;

public class PlayerBingoData {

	private ArrayList<BingoObjective> objectives;
	private UUID playerUuid;
	private boolean bingoComplete = false;
	
	public PlayerBingoData(UUID uuid, ArrayList<BingoObjective> _objectives) {
		objectives = _objectives;
		playerUuid = uuid;
	}
	
	public ArrayList<BingoObjective> getObjectives() {
		return objectives;
	}
	public void setObjectives(ArrayList<BingoObjective> objectives) {
		this.objectives = objectives;
	}
	public UUID getPlayerUuid() {
		return playerUuid;
	}
	public void setPlayerUuid(UUID playerUuid) {
		this.playerUuid = playerUuid;
	}
	public boolean isBingoComplete() {
		return bingoComplete;
	}
	public void setBingoComplete(boolean bingoComplete) {
		this.bingoComplete = bingoComplete;
	}
	
	
	
}
