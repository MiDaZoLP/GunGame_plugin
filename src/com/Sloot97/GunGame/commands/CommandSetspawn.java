package com.Sloot97.GunGame.commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.Sloot97.GunGame.GameManager;

public class CommandSetspawn implements SubCommand {

	private GameManager gm;

	public CommandSetspawn(GameManager gm) {
		this.gm = gm;
	}
	
	@Override
	public boolean onCommand(Player p, String[] args) {
		if(!p.hasPermission("gungame.setspawn"));
		    if(args.length == 0) {
			    p.sendMessage(ChatColor.DARK_RED + "§l[GunGame] Zu wenige Argumente!");
			    p.sendMessage(ChatColor.DARK_RED + "§l[GunGame] /gungame setspawn [ArenaID]");
		    } else if(args.length == 1) {
		    	try {
		    		Integer.parseInt(args[0]);
		    	} catch(Exception e) {
		    		p.sendMessage(ChatColor.DARK_RED + "Die ArenaID muss eine Zahl sein!");
		    	}
		    	
		    	gm.AutoAddSpawn(ArenaID, p, p.getLocation());
		    }
		return false;
	}
    private int ArenaID;
}
