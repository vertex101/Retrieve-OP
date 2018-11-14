package zone.norskas.utils.config;

import java.io.IOException;

import org.bukkit.Bukkit;

import zone.norskas.RetrieveOP;

public class SaveMsg {

	public RetrieveOP plugin;
	public SaveMsg(RetrieveOP plugin) {
	this.plugin = plugin;
	}
	
	
	public void saveMessages() {
		try {
			plugin.messagesC.save(plugin.messages);
			
		} catch (IOException e) {
			Bukkit.getConsoleSender().sendMessage(" ");
			Bukkit.getConsoleSender().sendMessage("§e§lRetrieveOP: §fYAML Save §7> §f[§cmessages.yml§f] failed to save!");
			Bukkit.getConsoleSender().sendMessage(" ");
			if (plugin.getConfig().getBoolean("Logging") == true) {
				plugin.logLogins(plugin.format.format(plugin.now) + " /// YAML Save: Failed to save messages.yml!");
            }
			
			e.printStackTrace();
		}
	}
	
	
}
