package com.Sloot97.GunGame.config;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.Sloot97.GunGame.GunGame;

public class Systemyml {

	public File f;
	public FileConfiguration fc;
	private GunGame plugin;
	
	public Systemyml(GunGame plugin) {
		this.plugin = plugin;
		manage();
	}
	@SuppressWarnings("static-access")
	private void manage() {
		f = new File(plugin.getDataFolder() + File.separator + "System.yml");
		fc = new YamlConfiguration().loadConfiguration(f);
	}
	public Object get(String path) {
		return fc.get(path);
	}
	public void set(String path, Object value) {
		fc.addDefault(path, value);
	}
	public boolean isSet(String path) {
		return fc.isSet(path);
	}
	
}
