package com.Sloot97.GunGame.commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.Sloot97.GunGame.GameManager;

public class CommandAddArena implements SubCommand {

	private GameManager gm;

	public CommandAddArena(GameManager gm) {
		this.gm = gm;
	}
	
	@Override
	public boolean onCommand(Player p, String[] args) {
		if(p.hasPermission("gungame.addarena")) {
		if(args.length != 1) {
			p.sendMessage(ChatColor.DARK_RED + "§l[GunGame] Zu viele Argumente!");
			p.sendMessage(ChatColor.GOLD + "§l[GunGame] /gungame addarena [ArenaID]");
			return true;
		} else {
			try {
				ArenaID = Integer.parseInt(args[0]);
			} catch(Exception e) {
				p.sendMessage(ChatColor.DARK_RED + "§l[GunGame] Die ArenaID muss eine Zahl sein!");
				return true;
			}
			gm.addArena(ArenaID, p, p.getLocation());
			return true;
		}
		} else {
			p.sendMessage(ChatColor.DARK_RED + "§l[GunGame] DU hast keine Rechte dies zu tun!");
			return true;
		}
	}
	private Integer ArenaID;
}
