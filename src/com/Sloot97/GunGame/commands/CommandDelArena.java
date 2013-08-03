package com.Sloot97.GunGame.commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.Sloot97.GunGame.GameManager;

public class CommandDelArena implements SubCommand {

	private GameManager gm;

	public CommandDelArena(GameManager gm) {
		this.gm = gm;
	}
	
	@Override
	public boolean onCommand(Player p, String[] args) {
		if(!p.hasPermission("gungame.delarena")) {
			p.sendMessage(ChatColor.DARK_RED + "§l[GunGame] Du hats keine Rechte dies zu tun!");
			return true;
		} else {
			if(args.length == 0) {
				p.sendMessage(ChatColor.DARK_RED + "§l[GunGame] Zu wenige Argumente");
				p.sendMessage(ChatColor.DARK_RED + "§l[GunGame] /gungame delarena [ArenaID]");
			} else if(args.length != 1) {
				p.sendMessage(ChatColor.DARK_RED + "§L[GunGame] Zu viele Argumente");
				p.sendMessage(ChatColor.DARK_RED + "§l[GunGame] /gungame delarena [ArenaID]");
			} else {
				try {
					ArenaID = Integer.parseInt(args[0]);
				} catch(Exception e) {
					p.sendMessage(ChatColor.DARK_RED + "§l[GunGame] Die ArenaID muss eine Zahl sein!");
				}
				gm.removeArena(ArenaID, p);
			}
		}
		return false;
	}
    private int ArenaID;
}
