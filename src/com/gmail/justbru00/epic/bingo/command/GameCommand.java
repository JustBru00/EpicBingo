package com.gmail.justbru00.epic.bingo.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.gmail.justbru00.epic.bingo.utils.Messager;

public class GameCommand implements CommandExecutor {
	
	private String[] help = {"/game start - Starts tracking objectives and announces it.", 
			"/game stop - Stops tracking objectives and lists the players who have completed a row successfully.", 
			"/game reset - Resets all progress and objectives.", 
			"/game player add <PlayerName> - Adds the player provided to the next game.", 
			"/game player addall - Adds all of the players online into the game.",
			"/game player remove <PlayerName> - Removes the player from the game.",
			"/game help- Shows this help message."};

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		/**
		 * This command handles starting objective tracking, Resetting objective tracking and stopping objective tracking.
		 * 
		 * game start
		 * game stop
		 * game reset
		 * game player add <PlayerName>
		 * game player addall
		 * game player remove <PlayerName>
		 * game help
		 * 
		 */
		
		if (command.getName().equalsIgnoreCase("game")) {
			if (sender.hasPermission("epicbingo.game")) {
				
				if (args.length >= 1) {
					if (args[0].equalsIgnoreCase("start")) {
						
					} else if (args[0].equalsIgnoreCase("stop")) {
						
					} else if (args[0].equalsIgnoreCase("reset")) {
						
					} else if (args[0].equalsIgnoreCase("player")) {
						
					} else if (args[0].equalsIgnoreCase("help")) {
						for (String line : help) {
							Messager.msgSender(line, sender);
						}
						return true;
					} else {
						Messager.msgSender("&cUnknown arguments. /game help", sender);
						return true;
					}					
				} else {
					Messager.msgSender("&cPlease provide some arguments. /game help", sender);
					return true;
				}				
			} else {
				Messager.msgSender("&cYou don't have permission.", sender);
				return true;
			}
		}
		
		return false;
	}

}
