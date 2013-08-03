package com.Sloot97.GunGame.Events;

import java.util.ArrayList;
import java.util.Random;

import com.Sloot97.GunGame.GameManager;
import com.Sloot97.GunGame.GunGame;
import com.Sloot97.GunGame.config.Systemyml;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerDeathListener implements Listener {

	private GameManager gm;
	private Systemyml sys;

	
	private PlayerDeathListener(GameManager gm, Systemyml sys, GunGame plugin) {
		this.gm = gm;
		this.sys = sys;
		this.plugin = plugin;
	}
	@EventHandler
	public void PlayerDead(PlayerDeathEvent event) {
		Player p = event.getEntity();
		if(gm.isPlayerIngame(p)) {
			ArrayList<Player> ingameplayers = gm.PIAA.get(gm.getArenaofPlayer(p));
        	Player[] igp = (Player[]) ingameplayers.toArray();
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
	        int lp = gm.PlayerLevel.get(p);
	        int lk = gm.PlayerLevel.get(killer);
	        int nlp = lp--;
	        int nlk = lk++;
	        if(nlp == 0) {
	        	nlp = 1;
	        }
	        for(int i = 0; i < igp.length; i++) {
        		Player sp = igp[i];
        		sp.sendMessage(ChatColor.GREEN + killer.getDisplayName() + ChatColor.GOLD + " hat " + ChatColor.DARK_RED + p.getDisplayName() + ChatColor.GOLD + "getötet!");
        	}
	        if(nlk == 9) {
	        	plugin.getServer().broadcastMessage(ChatColor.DARK_RED + killer.getName() + " hat GunGame in Arena " + gm.getArenaofPlayer(killer) + " gewonnen! - Glückwunsch!");
	        	killer.getInventory().addItem(new ItemStack(Material.CAKE));
	        } else if(nlk == 8) {
	        	for(int i = 0; i < igp.length; i++) {
	        		Player sp = igp[0];
	        		sp.sendMessage(ChatColor.DARK_RED + "§l[GunGame] " + killer.getDisplayName() + ChatColor.DARK_RED + "§l muss aufgehalten werden!");
	        	}
	        } else {
	        	killer.sendMessage(ChatColor.GREEN + "§l[GunGame] Du bist ein Level hochgestiegen!");
	        	p.sendMessage(ChatColor.GREEN + "§l[GunGame] Du bist ein Level abgestiegen!");
	        	p.getInventory().clear();
	        	if(plugin.getConfig().getInt("Items." + "level" + nlp + ".Item1") != 0) {
	        		p.getInventory().addItem(new ItemStack(plugin.getConfig().getInt("Items." + "level" + nlp + ".Item1")));
	        	}
	        	if(plugin.getConfig().getInt("Items." + "level" + nlp + ".Item2") != 0) {
	        		p.getInventory().addItem(new ItemStack(plugin.getConfig().getInt("Items." + "level" + nlp + ".Item2")));
	        	}
	        	if(plugin.getConfig().getInt("Items." + "level" + nlk + ".Item1") != 0) {
	        		p.getInventory().addItem(new ItemStack(plugin.getConfig().getInt("Items." + "level" + nlk + ".Item1")));
	        	}
	        	if(plugin.getConfig().getInt("Items." + "level" + nlk + ".Item2") != 0) {
	        		p.getInventory().addItem(new ItemStack(plugin.getConfig().getInt("Items." + "level" + nlk + ".Item2")));
	        	}
	        }
		}   
	}
	private GunGame plugin;
}	

