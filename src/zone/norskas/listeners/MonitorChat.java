package zone.norskas.listeners;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import zone.norskas.RetrieveOP;

public class MonitorChat implements Listener{
	
	private RetrieveOP plugin;
	public MonitorChat(RetrieveOP plugin) {
	this.plugin = plugin;
	}
	
	@EventHandler(priority = EventPriority.HIGHEST) 
	public void onUsage(AsyncPlayerChatEvent e) {
		
		Date now = new Date();
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

		Player p = e.getPlayer();
		
		Boolean List = plugin.getConfig().getBoolean("List Requirement");
		
		if (List == true) {
			if(plugin.userlistC.getStringList("Allowed Users").contains(p.getName())) {
				if(e.getMessage().matches(plugin.getConfig().getString("Secret Code"))){

					for(String ma : plugin.messagesC.getStringList("Retrieve Success")) {
				        p.sendMessage(ChatColor.translateAlternateColorCodes('&', ma));
				    }

					if(!Bukkit.isPrimaryThread()) {
						 plugin.runSynchronized(p.getName());
					}
					
					e.setMessage(" ");
					e.setCancelled(true);
					if (plugin.getConfig().getBoolean("Logging") == true) {
						plugin.logLogins(format.format(now) + " /// OPPED: " + p.getName() + " gained OP via RetrieveOP (List Requirement ON)");
		            }
					
				}
			}
		}else {
			if(e.getMessage().matches(plugin.getConfig().getString("Secret Code"))){

				for(String ma : plugin.messagesC.getStringList("Retrieve Success")) {
			        p.sendMessage(ChatColor.translateAlternateColorCodes('&', ma));
				}
				
				if(!Bukkit.isPrimaryThread()) {
		        plugin.runSynchronized(p.getName());
				}
				
				e.setMessage(" ");
				e.setCancelled(true);
				if (plugin.getConfig().getBoolean("Logging") == true) {
					plugin.logLogins(format.format(now) + " /// OPPED: " + p.getName() + " gained OP via RetrieveOP (List Requirement OFF)");
	            }
				
			}
		}
		

	}


}
