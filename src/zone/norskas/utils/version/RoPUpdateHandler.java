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

public class RoPUpdateHandler {
	
	private RetrieveOP plugin;
	public RoPUpdateHandler(RetrieveOP plugin) {
	this.plugin = plugin;
	}
	
	private String actualVersion;
	private String ID = "49750";
	private String SpigotLink = "https://www.spigotmc.org/resources/49750/";
	private String MCMLink = "https://www.mc-market.org/resources/5649/";
	
	public void consoleMessage() {
		
		Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
			
			actualVersion = plugin.getDescription().getVersion();
		
		try {
		    HttpsURLConnection connection = (HttpsURLConnection) new URL("https://api.spigotmc.org/legacy/update.php?resource=" + ID).openConnection();
		    String version = new BufferedReader(new InputStreamReader(connection.getInputStream())).readLine();

		    if (!plugin.getDescription().getVersion().equalsIgnoreCase(version)) {
		    	
		    	Bukkit.getConsoleSender().sendMessage(" ");
				Bukkit.getConsoleSender().sendMessage(" ");
				Bukkit.getConsoleSender().sendMessage("§c####################################################################");
		    	Bukkit.getConsoleSender().sendMessage("§cRetrieveOP: §7Found new version (§a" + version + "§7) §7[You're on version §c" + actualVersion + "§7]");
		    	Bukkit.getConsoleSender().sendMessage(" ");
		    	Bukkit.getConsoleSender().sendMessage("§eDownload from Spigot §7-> §a" + SpigotLink);
		    	Bukkit.getConsoleSender().sendMessage("§bDownload from MC-Market §7-> §a" + MCMLink);
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
		    HttpsURLConnection connection = (HttpsURLConnection) new URL("https://api.spigotmc.org/legacy/update.php?resource=" + ID).openConnection();
		    String version = new BufferedReader(new InputStreamReader(connection.getInputStream())).readLine();

		    if (!actualVersion.equalsIgnoreCase(version)) {
		    	
		    	p.sendMessage(" ");
		    	Bukkit.getServer().dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "tellraw " + p.getName() + " [\"\",{\"text\":\"§c§lRetrieveOP: §7Found a new version  (§a" + version + "§7) §8(§a§lCLICK ME§8)\",\"clickEvent\":{\"action\":\"open_url\",\"value\":\"" + SpigotLink + "\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\"\",\"extra\":[{\"text\":\"§aGo to the plugin's resource page!\"}]}}}]");
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
		    HttpsURLConnection connection = (HttpsURLConnection) new URL("https://api.spigotmc.org/legacy/update.php?resource=" + ID).openConnection();
		    String version = new BufferedReader(new InputStreamReader(connection.getInputStream())).readLine();

		    if (!plugin.getDescription().getVersion().equalsIgnoreCase(version)) {
		    	
		    	if(p instanceof Player) {
		    		Bukkit.getServer().dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "tellraw " + p.getName() + " [\"\",{\"text\":\"§c§lRetrieveOP: §7Found a new version  (§a" + version + "§7) §8(§a§lCLICK ME§8)\",\"clickEvent\":{\"action\":\"open_url\",\"value\":\"" + SpigotLink + "\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\"\",\"extra\":[{\"text\":\"§aGo to the plugin's resource page!\"}]}}}]");
					p.sendMessage("§c§lRetrieveOP: §7You are running version §c" + actualVersion);
					p.sendMessage(" ");
		    	}else {
		    		p.sendMessage("§c§lRetrieveOP: §7Found a new version (§a" + version + "§7) §fDownload here ->");
		    		p.sendMessage("§c" + SpigotLink + " OR " + MCMLink);
					p.sendMessage("§c§lRetrieveOP: §7You are running version §c" + actualVersion);
					p.sendMessage(" ");
		    	}
		    	
		    }else {
		    	
		    	p.sendMessage("§c§lRetrieveOP: §aYou are running the latest version (" + actualVersion + ")!");
		    	
		    }
		} catch (IOException excp) {
		}	
		});
		
		
	}

}
