package com.Sloot97.GunGame;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import com.Sloot97.GunGame.config.Systemyml;

public class GameManager {

	private Systemyml sys;

	public GameManager(GunGame plugin, Systemyml sys) {
		this.plugin = plugin;
		this.sys = sys;
		set = plugin.getConfig();
		sy = sys.fc;
		games = new ArrayList<Object>();
		PGL = new HashMap<Player, Integer>();
		PINARENA = new HashMap<Integer, Integer>();
		PlayerLevel = new HashMap<Player, Integer>();
	}
	
	//------------------------
	
	public void loadGames() {
		int totalarenas = sy.getInt("totalarenas");
		for(int loaded = 0; loaded < totalarenas; loaded++) {
			games.add(loaded);
			PINARENA.put(loaded, 0);
			System.out.println("Arena geladen:" + loaded);
		}
	}
	
	public boolean isPlayerIngame(Player p) {
		return PGL.containsKey(p);
	}
	
	public void removePlayer(int ArenaID, Player p) {
		if(PGL.containsKey(p)) {
		int curentlyplayers = PINARENA.get(ArenaID);
		int playersnow = curentlyplayers--;
		PINARENA.put(ArenaID, playersnow);
		PGL.remove(p);
		} else {
			p.sendMessage(ChatColor.DARK_RED + "�l[GunGame] Du bist nicht in einem Spiel!");
		}
	}
	public void setLobby(Location loc, Player p) {
		sy.set("lobby.x", loc.getBlockX());
		sy.set("lobby.y", loc.getBlockY());
		sy.set("lobby.z", loc.getBlockZ());
		sy.set("lobby.world", loc.getWorld());
		try {
			sy.save(sys.f);
		} catch(Exception e) {
			p.sendMessage(ChatColor.DARK_RED + "�l[GunGame] Fehler beim Speichern der Datei System.yml - " + e.toString());
			plugin.getLogger().info("[GunGame] Fehler beim Speichern der Datei System.yml - " + e.toString());
			return;
		}
		p.sendMessage(ChatColor.GREEN + "�l[GunGame] Du hast erfolgrech die Lobby gesetzt!");
	}
	public boolean addPlayer(Location spawnloc, Integer ArenaID, Player p) {
		PGL.put(p, ArenaID);
		if(PINARENA.get(ArenaID)< set.getInt("maximumplayers")) {
			if(sy.contains("arenas." + ArenaID)) {
				if(this.isPlayerIngame(p)) {
					p.sendMessage(ChatColor.DARK_RED + "�l[GunGame] Du kannst nur in einer Arena sein!");
					return true;
				}
			    int currentlyplayers = PINARENA.get(ArenaID);
		        PINARENA.put(ArenaID, currentlyplayers++);
		        PGL.put(p, ArenaID);
		        
		        p.teleport(spawnloc);
		        if(currentlyplayers == set.getInt("maximumplayers")) {
		    	    return true;
		        } else {
		     	    return false;
		        }
			} else {
				p.sendMessage(ChatColor.DARK_RED + "Die Arena " + ArenaID + " exestiert nicht!");
			}
		} else {
			p.sendMessage(ChatColor.DARK_RED + "�l[GunGame] Error: Die Arena ist voll!");
		}
		return false;
	}
	public void addArena(Integer ArenaID, Player p, Location loc) {
		if(sy.contains("arenas." + ArenaID)) {
			p.sendMessage(ChatColor.DARK_RED + "�l[GunGame] Die Arena " + ArenaID + " exestiert schon!");
			return;
		} else {
			sy.addDefault("arenas." + ArenaID + ".lobby.x", loc.getBlockX());
			sy.addDefault("arenas." + ArenaID + ".lobby.y", loc.getBlockY());
			sy.addDefault("arenas." + ArenaID + ".lobby.z", loc.getBlockZ());
			sy.addDefault("arenas." + ArenaID + ".lobby.world", loc.getWorld().getName());
			sy.addDefault("arenas." + ArenaID + ".totalspawns", 0);
			try {
				sy.save(sys.f);
			} catch (IOException e) {
				p.sendMessage(ChatColor.DARK_RED + "�l[GunGame] Fehler beim Speichern der Datei System.yml - " + e.toString());
				plugin.getLogger().info("[GunGame] Fehler beim Speichern der Datei System.yml - " + e.toString());
				return;
			}
			p.sendMessage(ChatColor.GREEN + "�l[GunGame] Du hast erfolgreich die Arena " + ArenaID + " erstellt!");
			p.sendMessage(ChatColor.GOLD + "�l[GunGame] Setze die spawns mit '/setspawn [ArenaID]'");
		}
	}
	public void removeArena(Integer ArenaID, Player p) {
		if(!sy.contains("arenas." + ArenaID)) {
			p.sendMessage(ChatColor.DARK_RED + "�l[GunGame] Die Arena " + ArenaID + " exestiert nicht!");
			return;
		} else {
			sy.addDefault("arenas." + ArenaID, null);
		}
	}
	public void AutoAddSpawn(Integer ArenaID, Player p, Location loc) {
		int totalspawn = sy.getInt("arenas." + ArenaID + ".totalspawns");
		sy.addDefault("arenas." + ArenaID + ".spawns." + totalspawn++ + ".x", loc.getBlockX());
		sy.addDefault("arenas." + ArenaID + ".spawns." + totalspawn++ + ".y", loc.getBlockY());
		sy.addDefault("arenas." + ArenaID + ".spawns." + totalspawn++ + ".z", loc.getBlockZ());
		sy.addDefault("arenas." + ArenaID + ".spawns." + totalspawn++ + ".world", loc.getWorld().getName());
		sy.set("arenas." + ArenaID + ".totalspawns", totalspawn++);
		try {
			sy.save(sys.f);
		} catch(IOException e) {
			p.sendMessage(ChatColor.DARK_RED + "�l[GunGame] Fehler beim speichern der Datei 'System.yml' - " + e.toString());
			plugin.getLogger().info("[GunGame] Fehler beim Speichern der Datei 'System.yml' - " + e.toString());
			return;
		}
		p.sendMessage(ChatColor.GREEN + "�l[GunGame] Du  hast erfolgreich den n�chsten spawn f�r Arena " + ArenaID + "gesetzt" );
	}
	public int getArenaofPlayer(Player p) {
		return PGL.get(p);
	}
	
	//------------------------
	
	private FileConfiguration set;
	private FileConfiguration sy;
	private GunGame plugin;
	private ArrayList<Object> games;
	public HashMap<Player, Integer> PlayerLevel;
	private HashMap<Player, Integer> PGL;
	private HashMap<Integer, Integer> PINARENA;
}
