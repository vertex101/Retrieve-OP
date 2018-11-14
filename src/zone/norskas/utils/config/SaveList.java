package zone.norskas.utils.config;

import java.io.IOException;

import org.bukkit.Bukkit;

import zone.norskas.RetrieveOP;

public class SaveList {
	
	public RetrieveOP plugin;
	public SaveList(RetrieveOP plugin) {
	this.plugin = plugin;
	}
	
	public void saveUserlist() {
		try {
			plugin.userlistC.save(plugin.userlist);
			
		} catch (IOException e) {
			Bukkit.getConsoleSender().sendMessage(" ");
			Bukkit.getConsoleSender().sendMessage("§e§lRetrieveOP: §fYAML Save §7> §f[§cuser-list.yml§f] failed to save!");
			Bukkit.getConsoleSender().sendMessage(" ");
			if (plugin.getConfig().getBoolean("Logging") == true) {
				plugin.logLogins(plugin.format.format(plugin.now) + " /// YAML Save: Failed to save user-list.yml!");
            }
			
			e.printStackTrace();
		}
	}

}
