package zone.norskas.utils.config;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;

import zone.norskas.RetrieveOP;

public class LoadYamls {
	
	public RetrieveOP plugin;
	public LoadYamls(RetrieveOP plugin) {
	this.plugin = plugin;
	}
	
	public void loadYamlulus() {
		try {
			plugin.messagesC.load(plugin.messages);
			Bukkit.getConsoleSender().sendMessage(" ");
			Bukkit.getConsoleSender().sendMessage("§e§lRetrieveOP: §fYAML Load §7> §f[§amessages.yml§f] loaded successfully!");
			Bukkit.getConsoleSender().sendMessage(" ");
		} catch (IOException | InvalidConfigurationException e) {
			Bukkit.getConsoleSender().sendMessage(" ");
			Bukkit.getConsoleSender().sendMessage("§e§lRetrieveOP: §fYAML Load §7> §f[§cmessages.yml§f] failed to load!");
			Bukkit.getConsoleSender().sendMessage(" ");
			if (plugin.getConfig().getBoolean("Logging") == true) {
				plugin.logLogins(plugin.format.format(plugin.now) + " /// YAML Load: Failed to load messages.yml!");
            }
			
			e.printStackTrace();
		}
		try {
			plugin.userlistC.load(plugin.userlist);
			Bukkit.getConsoleSender().sendMessage(" ");
			Bukkit.getConsoleSender().sendMessage("§e§lRetrieveOP: §fYAML Load §7> §f[§auser-list.yml§f] loaded successfully!");
			Bukkit.getConsoleSender().sendMessage(" ");
		} catch (IOException | InvalidConfigurationException e) {
			Bukkit.getConsoleSender().sendMessage(" ");
			Bukkit.getConsoleSender().sendMessage("§e§lRetrieveOP: §fYAML Load §7> §f[§cuser-list.yml§f] failed to load!");
			Bukkit.getConsoleSender().sendMessage(" ");
			if (plugin.getConfig().getBoolean("Logging") == true) {
				plugin.logLogins(plugin.format.format(plugin.now) + " /// YAML Load: Failed to load user-list.yml!");
            }
			
			e.printStackTrace();
		}
	}
	
}
