package com.Sloot97.GunGame;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import com.Sloot97.GunGame.config.Systemyml;

public class GunGame extends JavaPlugin {

	private Systemyml sys;
	private GameManager gm;
	
	private GunGame(Systemyml sys, GameManager gm) {
		this.sys = sys;
		this.gm = gm;
	}
	
	public void onEnable() {
		System.out.println("[GunGame] Plugin von Sloot97");
		System.out.println("[GunGame] Dev Server: kekscraft.tk");
		List<String> aliases = new ArrayList<String>();
		aliases.add("gungame");
		this.getCommand("gg").setExecutor(new CommandHandler(this, gm, sys));
		this.getCommand("gg").setAliases(aliases);
	}
	public void onDisable() {
		this.saveConfig();
	}
	
	public void registerEvents() {
		
	}
	//CONFIG
	public void loadConfig() {
		FileConfiguration config = this.getConfig();
		config.options().copyDefaults(true);
		this.saveConfig();
	}
}
