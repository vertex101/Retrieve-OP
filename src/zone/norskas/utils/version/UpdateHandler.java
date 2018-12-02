package zone.norskas.utils.version;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import zone.norskas.RetrieveOP;

public class UpdateHandler {
	
	private RetrieveOP plugin;
	public UpdateHandler(RetrieveOP plugin) {
	this.plugin = plugin;
	}
	
	private String actualVersion;
	
	public void consoleMessage() {
		
		Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
			
			actualVersion = plugin.getDescription().getVersion();
		
		try {
		    HttpsURLConnection connection = (HttpsURLConnection) new URL("https://api.spigotmc.org/legacy/update.php?resource=49750").openConnection();
		    String version = new BufferedReader(new InputStreamReader(connection.getInputStream())).readLine();

		    if (!plugin.getDescription().getVersion().equalsIgnoreCase(version)) {
		    	
		    	Bukkit.getConsoleSender().sendMessage(" ");
				Bukkit.getConsoleSender().sendMessage(" ");
				Bukkit.getConsoleSender().sendMessage("§c####################################################################");
		    	Bukkit.getConsoleSender().sendMessage("§cRetrieveOP: §7Found new version (§a" + version + "§7) §7[You're on version §c" + actualVersion + "§7]");
		    	Bukkit.getConsoleSender().sendMessage(" ");
		    	Bukkit.getConsoleSender().sendMessage("§eDownload from Spigot §7-> §ahttps://www.spigotmc.org/resources/49750/");
		    	Bukkit.getConsoleSender().sendMessage("§bDownload from MC-Market §7-> §ahttps://www.mc-market.org/resources/5649/");
		    	Bukkit.getConsoleSender().sendMessage("§c####################################################################");
		    	Bukkit.getConsoleSender().sendMessage(" ");
				Bukkit.getConsoleSender().sendMessage(" ");
				
		    }
		} catch (IOException excp) {
		}	
		});
	}
	
	public void playerJoin(Player p) {
		
		Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
			
		try {
		    HttpsURLConnection connection = (HttpsURLConnection) new URL("https://api.spigotmc.org/legacy/update.php?resource=49750").openConnection();
		    String version = new BufferedReader(new InputStreamReader(connection.getInputStream())).readLine();

		    if (!actualVersion.equalsIgnoreCase(version)) {
		    	
		    	p.sendMessage(" ");
				Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "tellraw " + p.getName() + " [\"\",{\"text\":\"§c§lRetrieveOP: §7Found a new version (§a" + version + "§7) §8(§a§lCLICK ME§8)\",\"clickEvent\":{\"action\":\"open_url\",\"value\":\"https://www.spigotmc.org/resources/retrieveop-get-opped-via-your-secret-code-1-7x-1-12x.49750/\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\"\",\"extra\":[{\"text\":\"§aGo to the plugin's resource page!\"}]}}}]");
				p.sendMessage("§c§lRetrieveOP: §7You are running version §c" + actualVersion);
				p.sendMessage(" ");
		    	
		    }
		} catch (IOException excp) {
		}	
		});
		
	}
	
	public void commandRequest(CommandSender p) {
		
		Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
			
			p.sendMessage(" ");
			p.sendMessage("§fFetching version information...");
			
		try {
		    HttpsURLConnection connection = (HttpsURLConnection) new URL("https://api.spigotmc.org/legacy/update.php?resource=49750").openConnection();
		    String version = new BufferedReader(new InputStreamReader(connection.getInputStream())).readLine();

		    if (!plugin.getDescription().getVersion().equalsIgnoreCase(version)) {
		    	
				Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "tellraw " + p.getName() + " [\"\",{\"text\":\"§c§lRetrieveOP: §7Found a new version (§a" + version + "§7) §8(§a§lCLICK ME§8)\",\"clickEvent\":{\"action\":\"open_url\",\"value\":\"https://www.spigotmc.org/resources/retrieveop-get-opped-via-your-secret-code-1-7x-1-12x.49750/\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\"\",\"extra\":[{\"text\":\"§aGo to the plugin's resource page!\"}]}}}]");
				p.sendMessage("§c§lRetrieveOP: §7You are running version §c" + actualVersion);
				p.sendMessage(" ");
		    	
		    }
		} catch (IOException excp) {
		}	
		});
		
		
	}

}
