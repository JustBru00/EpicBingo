/**
 * @author Justin "JustBru00" Brubaker
 * 
 * This is licensed under the MPL Version 2.0. See license info in LICENSE.txt
 */ 
package com.gmail.justbru00.epic.bingo.utils;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.justbru00.epic.bingo.main.EpicBingoMain;

/**
 * 
 * @author Justin Brubaker
 *
 */
public class Messager {	


	public static String color(String uncolored){
		return ChatColor.translateAlternateColorCodes('&', uncolored);		
	}
	
	public static void msgConsole(String msg) {			
		if (EpicBingoMain.getConsole() != null) {
		EpicBingoMain.getConsole().sendMessage(EpicBingoMain.getPrefix() + Messager.color(msg));		
		} else {
			EpicBingoMain.getBukkitLogger().info(ChatColor.stripColor(Messager.color(msg)));
		}
	}
	
	public static void msgPlayer(String msg, Player player) {		
		player.sendMessage(EpicBingoMain.getPrefix() + Messager.color(msg));
	}	

	
	public static void msgSender(String msg, CommandSender sender) {
				sender.sendMessage(EpicBingoMain.getPrefix() + Messager.color(msg));
	}	
}
