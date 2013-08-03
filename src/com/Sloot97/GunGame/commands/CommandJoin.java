package com.Sloot97.GunGame.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import com.Sloot97.GunGame.GameManager;
import com.Sloot97.GunGame.config.Systemyml;

public class CommandJoin implements SubCommand {

	private Systemyml sys;
	private GameManager gm;

	public CommandJoin(Systemyml sys, GameManager gm) {
		this.sys = sys;
		this.gm = gm;
	}

	public boolean onCommand(Player p, String[] args) {
		FileConfiguration sy = sys.fc;
		if(args.length == 0) {
			if(sy.contains("lobby")) {
				try {
					x = sy.getInt("lobby.x");
					y = sy.getInt("lobby.y");
					z = sy.getInt("lobby.z");
					world = sy.getString("lobby.world");
				} catch(Exception e) {
					p.sendMessage(ChatColor.DARK_RED + "§l[GunGame] Der Lobbypunkt ist fehlerhaft! Kontaktiere einen Admin!");
					return true;
				}
				Location loc = new Location(Bukkit.getWorld(world), x, y, z);
				p.teleport(loc);
			} else {
				p.sendMessage(ChatColor.DARK_RED + "§l[GunGame] Die Lobby wurde noch nicht gesetzt!");
			}
		} else if(args.length == 1) {
			try {
				ArenaID = Integer.parseInt(args[0]);
			} catch(Exception e) {
				p.sendMessage(ChatColor.DARK_RED + "§l[GunGame] Die ArenaID muss eine Zahl sein!");
				return true;
			}
			if(sy.contains("arenas." + ArenaID)) {
				Location loc = new Location(Bukkit.getWorld(sy.getString("arenas." + ArenaID + ".lobby.world")), sy.getInt("arenas." + ArenaID + ".lobby.x"),sy.getInt("arenas." + ArenaID + ".lobby.y"), sy.getInt("arenas." + ArenaID + ".lobby.z"));
				gm.addPlayer(loc, ArenaID, p);
				return true;
			} else {
				p.sendMessage(ChatColor.DARK_RED + "§l[GunGame] Die Arena " + ArenaID + " exestiert nicht!");
				return true;
			}
		} else {
			p.sendMessage(ChatColor.DARK_RED + "§l[GunGame] Zu viele Argumente!");
			return true;
		}
		p.sendMessage(ChatColor.DARK_RED + "§l[GunGame] Ein unbekannter Fehler ist aufgetreten!");
		return true;
	}
	private int ArenaID;
	private int x;
	private int y;
	private int z;
	private String world;
}
