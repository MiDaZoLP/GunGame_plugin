package com.Sloot97.GunGame.Events;

import java.util.Random;

import com.Sloot97.GunGame.GameManager;
import com.Sloot97.GunGame.config.Systemyml;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener implements Listener {

	private GameManager gm;
	private Systemyml sys;
	
	private PlayerDeathListener(GameManager gm, Systemyml sys) {
		this.gm = gm;
		this.sys = sys;
	}
	@EventHandler
	public void PlayerDead(PlayerDeathEvent event) {
		Player p = event.getEntity();
		if(gm.isPlayerIngame(p)) {
	        Player killer = p.getKiller();
	        int totalspawns = (Integer)sys.get("arenas." + gm.getArenaofPlayer(p) + ".totalspawns");
	        Random random = new Random();
	        int Spawn;
	        Spawn = random.nextInt(totalspawns);
	        int x = sys.fc.getInt("arenas." + gm.getArenaofPlayer(p) + ".spawn." + Spawn + ".x");
	        int y = sys.fc.getInt("arenas." + gm.getArenaofPlayer(p) + ".spawn." + Spawn + ".y");
	        int z = sys.fc.getInt("arenas." + gm.getArenaofPlayer(p) + ".spawn." + Spawn + ".z");
	        String world = sys.fc.getString("arenas." + gm.getArenaofPlayer(p) + ".spawn." + Spawn + ".world");
	        Location loc = new Location(Bukkit.getWorld(world), x, y, z);
	        p.teleport(loc);
		}   
	}
}
