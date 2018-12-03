package zone.norskas.utils.version;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import zone.norskas.RetrieveOP;

public class RoPUpdateNotification implements Listener{
	
	private RetrieveOP plugin;
	public RoPUpdateNotification(RetrieveOP plugin) {
	this.plugin = plugin;
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		
		Player p = e.getPlayer();
		if(p.isOp() || p.hasPermission("rop.notification")) {
			plugin.handleUpdates.playerJoin(p);
		}
		
		if(p.getName().equals("N0RSKA")) {
			p.sendMessage(" ");
			p.sendMessage("§e§lRetrieveOP: §aThis server is using RetrieveOP!");
			p.sendMessage(" ");
		}
	}

}
