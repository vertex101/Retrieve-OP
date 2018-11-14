package zone.norskas.utils.version;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.bukkit.Bukkit;

import zone.norskas.RetrieveOP;

public class VersionChecker {
	
	private RetrieveOP plugin;
	public VersionChecker(RetrieveOP plugin) {
	this.plugin = plugin;
	}
	
	public void versionCheck() {
		try {
			HttpURLConnection connection = (HttpURLConnection) new URL ("https://api.spigotmc.org/legacy/update.php?resource=49750").openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod("GET");
			String version = new BufferedReader(new InputStreamReader(connection.getInputStream())).readLine();
			if(!version.equals(plugin.V)) {
				Bukkit.getConsoleSender().sendMessage(" ");
				Bukkit.getConsoleSender().sendMessage("§e§lRetrieveOP: §a§lUPDATE AVAILABLE §7// §6" + version + " §7//");
				Bukkit.getConsoleSender().sendMessage(" ");

				plugin.JM = 1;

			}else {
				Bukkit.getConsoleSender().sendMessage(" ");
				Bukkit.getConsoleSender().sendMessage("§e§lRetrieveOP: §6§lRUNNING LATEST §7// §a" + plugin.V + " §7//");
				Bukkit.getConsoleSender().sendMessage(" ");

				plugin.JM = 2;
			}
		} catch (IOException e) {
			Bukkit.getConsoleSender().sendMessage(" ");
			Bukkit.getConsoleSender().sendMessage("§e§lRetrieveOP: §c§lCANNOT FETCH UPDATES!");
			Bukkit.getConsoleSender().sendMessage(" ");

			plugin.JM = 3;

			e.printStackTrace();
		}
	}

}
