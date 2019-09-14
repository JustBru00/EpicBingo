package com.gmail.justbru00.epic.bingo.main;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.gmail.justbru00.epic.bingo.listeners.ItemListener;
import com.gmail.justbru00.epic.bingo.utils.Messager;

public class EpicBingoMain extends JavaPlugin {
	
	private static EpicBingoMain instance;
	private static String version = "NOT_SET_YET";
	private static String prefix = Messager.color("&8[&bEpic&fBingo&8] &6");
	private static ConsoleCommandSender console = Bukkit.getConsoleSender();
	private static Logger logger = Bukkit.getLogger();
	

	@Override
	public void onDisable() {
		Messager.msgConsole("&cPlugin disabled.");
		instance = null;
	}

	@Override
	public void onEnable() {
		instance = this;
		Messager.msgConsole("&aStarting to enable plugin.");
		version = getDescription().getVersion();
		
		// REGISTER COMMANDS
		
		// REGISTER LISTENERS
		PluginManager pm = Bukkit.getPluginManager();
		
		pm.registerEvents(new ItemListener(), instance);
	}

	public static EpicBingoMain getInstance() {
		return instance;
	}

	public static String getVersion() {
		return version;
	}

	public static String getPrefix() {
		return prefix;
	}

	public static ConsoleCommandSender getConsole() {
		return console;
	}

	public static Logger getBukkitLogger() {
		return logger;
	}

	
	
}
