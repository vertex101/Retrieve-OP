package zone.norskas;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import zone.norskas.commands.PluginCommand;
import zone.norskas.listeners.MonitorChat;
import zone.norskas.mysql.Connector;
import zone.norskas.utils.config.LoadYamls;
import zone.norskas.utils.config.MkDir;
import zone.norskas.utils.config.SaveList;
import zone.norskas.utils.config.SaveMsg;
import zone.norskas.utils.version.ActualVersionGet;
import zone.norskas.utils.version.UpdateNotification;
import zone.norskas.utils.version.VersionChecker;

public class RetrieveOP extends JavaPlugin implements Listener {
	
	public void onEnable(){		
		this.generateFiles();
		//this.startMySQL();
		this.sendStartupMessage();
		this.registerCommands();
		this.registerEvents();
	  }
	
	public String V = "2.2.0";
	public int JM = 0;
	
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
	public VersionChecker versionCheck = new VersionChecker(this);
	public ActualVersionGet actualversion = new ActualVersionGet(this);
	
	private void generateFiles() {
		messages = new File(getDataFolder(), "messages.yml");
		userlist = new File(getDataFolder(), "user-list.yml");
		   	mkdir.mkdir();
		   	loadYamlulus.loadYamlulus();
		   	versionCheck.versionCheck();
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
		   Bukkit.getConsoleSender().sendMessage("§7===================================================================");
		   Bukkit.getConsoleSender().sendMessage(" ");
		   Bukkit.getConsoleSender().sendMessage("             §eRETRIEVE OP §7// §eVERSION 2.2.0 (1.7x - 1.13x) §7// §eSTATUS: §fRUNNING!§r");
		   Bukkit.getConsoleSender().sendMessage("                  §aThank you for downloading this plugin.§r");
		   Bukkit.getConsoleSender().sendMessage("                      §aTo get started use §f/rop §ain-game!§r");
		   Bukkit.getConsoleSender().sendMessage("    §fPlease report bugs and suggest features @ §dhttps://goo.gl/DeM9Mc§r");
		   Bukkit.getConsoleSender().sendMessage("   §fIF YOU LIKE THIS PLUGIN PLEASE SUPPORT IT BY RATING 5 STARS ON SPIGOT!");
		   Bukkit.getConsoleSender().sendMessage("§7====================================================================");
	}
	
	private void registerEvents() {
		Bukkit.getPluginManager().registerEvents(new MonitorChat(this), this);
		Bukkit.getPluginManager().registerEvents(new UpdateNotification(this), this);
	}
	
	private void registerCommands() {
		this.getCommand("rop").setExecutor(new PluginCommand(this));
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