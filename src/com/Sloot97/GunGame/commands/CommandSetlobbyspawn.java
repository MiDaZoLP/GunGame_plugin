package com.Sloot97.GunGame.commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.Sloot97.GunGame.GameManager;

public class CommandSetlobbyspawn implements SubCommand {

	private GameManager gm;

	public CommandSetlobbyspawn(GameManager gm) {
		this.gm = gm;
	}
	
	@Override
	public boolean onCommand(Player p, String[] args) {
		if(args.length == 0) {
			gm.setLobby(p.getLocation(), p);
		} else {
			p.sendMessage(ChatColor.DARK_RED + "§l[GunGame] Zu viele Argumente!");
		}
		return false;
	}

	
}
