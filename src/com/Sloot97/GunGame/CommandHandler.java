package com.Sloot97.GunGame;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Vector;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.Sloot97.GunGame.commands.CommandAddArena;
import com.Sloot97.GunGame.commands.CommandDelArena;
import com.Sloot97.GunGame.commands.CommandJoin;
import com.Sloot97.GunGame.commands.CommandSetspawn;
import com.Sloot97.GunGame.commands.SubCommand;
import com.Sloot97.GunGame.config.Systemyml;


public class CommandHandler implements CommandExecutor {

	private GameManager gm;
	private Systemyml sys;
	
	public CommandHandler(GunGame plugin, GameManager gm, Systemyml sys) {
		this.plugin = plugin;
		this.gm = gm;
		this.sys = sys;
		commands = new HashMap<Object, Object>();
		loadCommands();
	}
	
	private void loadCommands() {
		commands.put("addarena", new CommandAddArena(gm));
		commands.put("join", new CommandJoin(sys, gm));
		commands.put("setspawn", new CommandSetspawn(gm));
		commands.put("delarena", new CommandDelArena(gm));
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String cmdlabel, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage("[GunGame] Only ingameplayers can use GunGame commands");
			return true;
		}
		Player p = (Player)sender;
		if(args.length == 0) {
			p.sendMessage(new StringBuilder().append(ChatColor.GOLD).append("§l").append("[GunGame] Version:") + plugin.getDescription().getVersion().toString());
			return true;
		} else {
			String sub = args[0].toLowerCase();
			Vector<String> l = new Vector<String>();
			l.addAll(Arrays.asList(args));
			l.remove(0);
			args = (String[])l.toArray(new String[0]);
			try {
				((SubCommand)commands.get(sub)).onCommand(p, args);
			} catch(Exception e) {
				
			}
		}
		return false;
	}
	private GunGame plugin;
	private HashMap<Object, Object> commands;
}
