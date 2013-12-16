package me.ksbdude.main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener{
	@Override
	public void onEnable(){
		//do this stuff when plugin is enabled
		getLogger().info("IPVP Plugin enabled");
	}
	@Override
	public void onDisable(){
		//do this stuff when plugin is disabled
		getLogger().info("IPVP Plugin disabled");
	}
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		Player p = (Player) sender;
		if (!(sender instanceof Player)){
	    	sender.sendMessage("You must be a player!");
		}
		else if (cmd.getName().equalsIgnoreCase("CreateArena")) {
				if(sender.hasPermission("plugin.sethub")) {
					 ArenaManager.getManager().createArena(p.getLocation());//sets the arena spawn to the current player location 
					 p.sendMessage(ChatColor.GREEN + "Arena Created!");
				 }
			}
			else if(cmd.getName().equalsIgnoreCase("Join")) {
				int num = 0;
				try{
				    num = Integer.parseInt(args[0]);
				}catch(NumberFormatException e){
				    p.sendMessage(ChatColor.RED + "Invalid arena ID");
				}
				ArenaManager.getManager().addPlayer(p, num);
			}
			else if(cmd.getName().equalsIgnoreCase("Join")) {
				ArenaManager.getManager().removePlayer(p);
			}

		    else if (cmd.getName().equalsIgnoreCase("setlobby")) {
		    	if(sender.hasPermission("plugin.setlobby")) {
		    		getConfig().set("lobby.world", p.getLocation().getWorld().getName());
		            getConfig().set("lobby.x", Double.valueOf(p.getLocation().getX()));
		            getConfig().set("lobby.y", Double.valueOf(p.getLocation().getY()));
		            getConfig().set("lobby.z", Double.valueOf(p.getLocation().getZ()));
		            getConfig().set("lobby.yaw", Float.valueOf(p.getLocation().getYaw()));
		            getConfig().set("lobby.pitch", Float.valueOf(p.getLocation().getPitch()));
		            saveConfig();
		            p.sendMessage(ChatColor.GREEN + "Lobby location Set!");
		            return true;
		    	}else{
		    		p.sendMessage(ChatColor.RED + "You do not have permmison to do that!");
		    	}
		        
		    }
		    if (cmd.getName().equalsIgnoreCase("pvp")) {
		        if (getConfig().getConfigurationSection("lobby") == null) {
		          p.sendMessage(ChatColor.RED + "The PVP lobby location not yet been set!");
		          return true;
		        }
		        World w = Bukkit.getServer().getWorld(getConfig().getString("lobby.world"));
		        double x = getConfig().getDouble("lobby.x");
		        double y = getConfig().getDouble("lobby.y");
		        double z = getConfig().getDouble("lobby.z");
		        float pitch = (float) getConfig().getDouble("lobby.pitch");
		        float yaw = (float) getConfig().getDouble("lobby.yaw");
		        Location l = new Location(w, x, y, z);
		        l.setPitch(pitch);
		        l.setYaw(yaw);
		        p.teleport(l);
		        p.sendMessage(ChatColor.GREEN + "Welcome to the PVP lobby!");
		    }
		return false;
	}
}