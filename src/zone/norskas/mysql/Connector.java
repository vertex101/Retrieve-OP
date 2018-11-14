package zone.norskas.mysql;

import java.sql.DriverManager;
import java.sql.SQLException;

import org.bukkit.Bukkit;

import zone.norskas.RetrieveOP;

public class Connector {
	
	public RetrieveOP plugin;
	public Connector(RetrieveOP plugin) {
	this.plugin = plugin;
	}
	
    public void openConnection()  throws SQLException, ClassNotFoundException {
    if (plugin.connection != null && !plugin.connection.isClosed()) {
        return;
    }
 
    synchronized (this) {
        if (plugin.connection != null && !plugin.connection.isClosed()) {
            return;
        }
        Class.forName("com.mysql.jdbc.Driver");
        plugin.connection = DriverManager.getConnection("jdbc:mysql://" + plugin.host + ":" + plugin.port + "/" + plugin.database, plugin.username, plugin.password);
    }
}

}
