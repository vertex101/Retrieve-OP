package zone.norskas.utils.version;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import zone.norskas.RetrieveOP;

public class ActualVersionGet {

	private RetrieveOP plugin;
	public ActualVersionGet(RetrieveOP plugin) {
	this.plugin = plugin;
	}
	
	public void actualVersionGet() {
		try {
			HttpURLConnection connection = (HttpURLConnection) new URL ("https://api.spigotmc.org/legacy/update.php?resource=49750").openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod("GET");
			String version = new BufferedReader(new InputStreamReader(connection.getInputStream())).readLine();
			plugin.actualVersionSave = version;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
