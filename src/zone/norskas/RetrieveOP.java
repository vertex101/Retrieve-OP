package zone.norskas;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import zone.norskas.commands.RetrieveOPCommand;
import zone.norskas.datacollection.Metrics;
import zone.norskas.listeners.MonitorChat;
import zone.norskas.mysql.Connector;
import zone.norskas.utils.config.LoadYamls;
import zone.norskas.utils.config.MkDir;
import zone.norskas.utils.config.SaveList;
import zone.norskas.utils.config.SaveMsg;
import zone.norskas.utils.version.RoPUpdateHandler;
import zone.norskas.utils.version.RoPUpdateNotification;

public class RetrieveOP extends JavaPlugin implements Listener {
	
	public void onEnable(){		
		generateFiles();
		//this.startMySQL();
		sendStartupMessage();
		registerCommands();
		registerEvents();
		
		handleUpdates.consoleMessage();
		Metrics metrics = new Metrics(this);
	  }
	
    private String V = this.getDescription().getVersion();
    private String PluginName = this.getDescription().getName();
    private String ColorCode = "§c";
    private String start = "1.7x";
    private String end = "1.13x";
    private List<String> Author = this.getDescription().getAuthors();
	
    public Connection connection;
    public String host, database, username, password;
    public int port;

	public File messages;
	public File userlist;
	
	public YamlConfiguration messagesC = new YamlConfiguration();
	public YamlConfiguration userlistC = new YamlConfiguration();
	
	public Date now = new Date();
	public SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	
	public String actualVersionSave;
	
	Connector connector = new Connector(this);
	public MkDir mkdir = new MkDir(this);
	public LoadYamls loadYamlulus = new LoadYamls(this);
	public SaveMsg saveMessages = new SaveMsg(this);
	public SaveList saveUserlist = new SaveList(this);
	public RoPUpdateHandler handleUpdates = new RoPUpdateHandler(this);
	
	private void generateFiles() {
		messages = new File(getDataFolder(), "messages.yml");
		userlist = new File(getDataFolder(), "user-list.yml");
		   	mkdir.mkdir();
		   	loadYamlulus.loadYamlulus();
		   	loadConfig();
	}
 
	/*private void startMySQL() {
	    host = this.getConfig().getString("MySQL.Host");
        port = this.getConfig().getInt("MySQL.Port");
        database = this.getConfig().getString("MySQL.Database");
        username = this.getConfig().getString("MySQL.Username");
        password = this.getConfig().getString("MySQL.Password");
        
        if(this.getConfig().getBoolean("MySQL.Enabled")) {
        	try {     
        		connector.openConnection();
                Statement statement = connection.createStatement();          
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
	}*/
	
    private void sendStartupMessage() {
		Bukkit.getConsoleSender().sendMessage(" ");
		Bukkit.getConsoleSender().sendMessage(" ");
		Bukkit.getConsoleSender().sendMessage(ColorCode + "##########################################");
		Bukkit.getConsoleSender().sendMessage(" ");
		Bukkit.getConsoleSender().sendMessage(ColorCode + PluginName + " §7// §aVERSION " + V + ColorCode + " by " + Author);
		Bukkit.getConsoleSender().sendMessage(ColorCode + "This plugin supports versions from §f" + start + ColorCode + " to §f" + end);
		Bukkit.getConsoleSender().sendMessage(" ");
		Bukkit.getConsoleSender().sendMessage(ColorCode + "If you enjoy this plugin make sure to leave a rating!");
		Bukkit.getConsoleSender().sendMessage(" ");
		Bukkit.getConsoleSender().sendMessage("§6You can get in contact with the developer via the following:");
		Bukkit.getConsoleSender().sendMessage("§eSPIGOT §7-> https://spigot.norskas.zone");
		Bukkit.getConsoleSender().sendMessage("§bMC-MARKET §7-> https://mcm.norskas.zone");
		Bukkit.getConsoleSender().sendMessage("§9DISCORD §7-> Norska#1933");
		Bukkit.getConsoleSender().sendMessage(" ");
		Bukkit.getConsoleSender().sendMessage(ColorCode + "##########################################");
		Bukkit.getConsoleSender().sendMessage(" ");
		Bukkit.getConsoleSender().sendMessage(" ");
	}
	
	private void registerEvents() {
		Bukkit.getPluginManager().registerEvents(new MonitorChat(this), this);
		Bukkit.getPluginManager().registerEvents(new RoPUpdateNotification(this), this);
	}
	
	private void registerCommands() {
		this.getCommand("rop").setExecutor(new RetrieveOPCommand(this));
	}
	
	public void logLogins(String message){
        try {

                File logins = new File(getDataFolder(), "usage.txt");
                if (!logins.exists())
                {
                	logins.createNewFile();
                }
     
                FileWriter fw = new FileWriter(logins, true);
     
                PrintWriter pw = new PrintWriter(fw);
     
                pw.println(message);
                pw.flush();
                pw.close();
 
        } catch (IOException e){
            e.printStackTrace();
        }
 
    }
	
	public void loadConfig() {
		saveDefaultConfig();
	}
	
	public void runSynchronized(String p) {
		new BukkitRunnable() {
            
            @Override
            public void run() {
            	for(String CMDs : getConfig().getStringList("Commands")) {
				Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), CMDs.replace("%user", p));
				}
            }
            
        }.runTaskLater(this, 10);
	}
	
	public YamlConfiguration getMessages(){
		return messagesC;
	}
	
	public YamlConfiguration getUserlist(){
		return userlistC;
	}
	

	public void reloadMessages() {
		messagesC = YamlConfiguration.loadConfiguration(messages);
	}
	
	public void reloadUserlist() {
		userlistC = YamlConfiguration.loadConfiguration(userlist);
	}
}