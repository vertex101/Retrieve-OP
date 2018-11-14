package zone.norskas.utils.config;

import org.bukkit.Bukkit;

import zone.norskas.RetrieveOP;

public class MkDir {
	
	
	public RetrieveOP plugin;
	public MkDir(RetrieveOP plugin) {
	this.plugin = plugin;
	}
	
	public void mkdir() {
		if(!plugin.messages.exists()) {
			plugin.saveResource("messages.yml", false);
			Bukkit.getConsoleSender().sendMessage(" ");
			Bukkit.getConsoleSender().sendMessage("§e§lRetrieveOP: §fYAML Generation §7> §f[§amessages.yml§f] generated!");
			Bukkit.getConsoleSender().sendMessage(" ");
            
            if (plugin.getConfig().getBoolean("Logging") == true) {
            	plugin.logLogins(plugin.format.format(plugin.now) + " /// YAML Generation: Generated messages.yml!");
            }
            
		}
		if(!plugin.userlist.exists()) {
			plugin.saveResource("user-list.yml", false);
			Bukkit.getConsoleSender().sendMessage(" ");
			Bukkit.getConsoleSender().sendMessage("§e§lRetrieveOP: §fYAML Generation §7> §f[§auser-list.yml§f] generated!");
			Bukkit.getConsoleSender().sendMessage(" ");
			if (plugin.getConfig().getBoolean("Logging") == true) {
				plugin.logLogins(plugin.format.format(plugin.now) + " /// YAML Generation: Generated user-list.yml!");
            }
			
		}
	}

}
