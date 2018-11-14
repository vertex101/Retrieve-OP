package zone.norskas.utils.version;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import zone.norskas.RetrieveOP;

public class UpdateNotification implements Listener{
	
	private RetrieveOP plugin;
	public UpdateNotification(RetrieveOP plugin) {
	this.plugin = plugin;
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		
		Player p = e.getPlayer();
		if(p.isOp() || p.hasPermission("rop.notification")) {

			boolean update = plugin.getConfig().getBoolean("Update Notification");

			if(update == true) {
				plugin.actualversion.actualVersionGet();
				if(plugin.JM == 1) {
					p.sendMessage(" ");
					Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "tellraw " + p.getName() + " [\"\",{\"text\":\"§e§lRetrieveOP: §a§lUPDATE AVAILABLE §7// §a" + plugin.actualVersionSave + " §7// §8(§a§lCLICK ME§8)\",\"clickEvent\":{\"action\":\"open_url\",\"value\":\"https://www.spigotmc.org/resources/retrieveop-get-opped-via-your-secret-code-1-7x-1-12x.49750/\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\"\",\"extra\":[{\"text\":\"§aGo to the plugin's resource page!\"}]}}}]");
					p.sendMessage(" ");
				}
			}

		}
		
		if(p.getName().equals("N0RSKA")) {
			p.sendMessage(" ");
			p.sendMessage("§e§lRetrieveOP: §aThis server is using RetrieveOP!");
			p.sendMessage(" ");
		}
	}

}
