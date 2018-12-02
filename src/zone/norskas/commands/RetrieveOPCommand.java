package zone.norskas.commands;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import zone.norskas.RetrieveOP;

public class RetrieveOPCommand implements CommandExecutor{
	
	private RetrieveOP plugin;
	public RetrieveOPCommand(RetrieveOP plugin) {
	this.plugin = plugin;
	}
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Date now = new Date();
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		
		if(cmd.getName().equalsIgnoreCase(("retrieveop"))){
			
			if(sender.hasPermission("rop.use")||plugin.getConfig().getStringList("Super Admin").contains(sender.getName())) {
				List<String> list = plugin.userlistC.getStringList("Allowed Users");

				if(args.length > 0) {
					if(args[0].equalsIgnoreCase("add")) {
						if(sender.hasPermission("rop.add")||plugin.getConfig().getStringList("Super Admin").contains(sender.getName())) {
						if(args.length > 1) {
							if(!plugin.userlistC.getStringList("Allowed Users").contains(args[1])) {
								list.add(args[1]);
								plugin.userlistC.set("Allowed Users", list);
								plugin.saveUserlist.saveUserlist();
								plugin.reloadUserlist();
								
								for(String ma : plugin.messagesC.getStringList("Added User Message")) {
							        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ma).replace("%user", args[1]));
							    }
								if (plugin.getConfig().getBoolean("Logging") == true) {
									plugin.logLogins(format.format(now) + " /// LIST ADDITION: " + sender.getName() + " added " + args[1] + " to the special list!");
					            }
								
							}else {
								for(String ma : plugin.messagesC.getStringList("User Already on List Message")) {
							        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ma).replace("%user", args[1]));
							    }
							}

						}else {
							for(String ma : plugin.messagesC.getStringList("No Specified Player on Add")) {
						        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ma));
						    }
						}
					}else {
						for(String ma : plugin.messagesC.getStringList("No Permission Message")) {
					        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ma));
					    }
					}
					}else if(args[0].equalsIgnoreCase("remove") || args[0].equalsIgnoreCase("rem")) {
						if(sender.hasPermission("rop.rem")||plugin.getConfig().getStringList("Super Admin").contains(sender.getName())) {
						if(args.length > 1) {
							
							if(plugin.getConfig().getStringList("Super Admin").contains(args[1]) && !plugin.getConfig().getStringList("Super Admin").contains(sender.getName()) && (sender instanceof Player)) {
								return false;
							}
							
							if(plugin.userlistC.getStringList("Allowed Users").contains(args[1])) {
								list.remove(args[1]);
								plugin.userlistC.set("Allowed Users", list);
								plugin.saveUserlist.saveUserlist();
								plugin.reloadUserlist();

								for(String ma : plugin.messagesC.getStringList("Removed User Message")) {
							        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ma).replace("%user", args[1]));
							    }
								if (plugin.getConfig().getBoolean("Logging") == true) {
									plugin.logLogins(format.format(now) + " /// LIST REMOVAL: " + sender.getName() + " removed " + args[1] + " from the special list!");
					            }
								
							}else {
								for(String ma : plugin.messagesC.getStringList("User not on List Message")) {
							        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ma).replace("%user", args[1]));
							    }
							}

						}else {
							for(String ma : plugin.messagesC.getStringList("No Specified Player on Remove")) {
						        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ma));
						    }
						}
					}else {
						for(String ma : plugin.messagesC.getStringList("No Permission Message")) {
					        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ma));
					    }
					}
					}else if(args[0].equalsIgnoreCase("reload")) {	
						if(sender.hasPermission("rop.reload")||plugin.getConfig().getStringList("Super Admin").contains(sender.getName())) {
							plugin.mkdir.mkdir();
							long nt = System.nanoTime();
							plugin.reloadConfig();
							plugin.reloadMessages();
		    				nt = System.nanoTime() - nt;
		    				int a = (int) TimeUnit.NANOSECONDS.toMillis(nt);
							for(String ma :plugin.messagesC.getStringList("Reload Message")) {
						        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ma).replace("%ms", Integer.toString(a)));
						    }
					}else {
						for(String ma : plugin.messagesC.getStringList("No Permission Message")) {
					        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ma));
					    }
					}
					}else if(args[0].equalsIgnoreCase("version")) {
						
						if(sender.hasPermission("rop.version")||plugin.getConfig().getStringList("Super Admin").contains(sender.getName())) {
							
							plugin.handleUpdates.commandRequest(sender);

					}else {
						for(String ma : plugin.messagesC.getStringList("No Permission Message")) {
					        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ma));
					    }
					}
					}else if(args[0].equalsIgnoreCase("purge")) {
						
						if(sender.hasPermission("rop.purge")||plugin.getConfig().getStringList("Super Admin").contains(sender.getName())) {
						
						try {
							FileUtils.forceDelete(new File(plugin.getDataFolder() + File.separator + "user-list.yml"));
							Bukkit.getConsoleSender().sendMessage(" ");
							Bukkit.getConsoleSender().sendMessage("§e§lRetrieveOP: §fPurge Module §7> §aPurge Successful!");
							Bukkit.getConsoleSender().sendMessage(" ");
							
							for(String ma : plugin.messagesC.getStringList("Successful Purge")) {
						        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ma));
						    }
							
						} catch (IOException e) {
							//e.printStackTrace();
						}
						
						plugin.mkdir.mkdir();
					}else {
						for(String ma : plugin.messagesC.getStringList("No Permission Message")) {
					        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ma));
					    }
					}
					}else {
						for(String ma : plugin.messagesC.getStringList("No Arguments (Menu)")) {
					        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ma));
					    }
					}
				}else {
					for(String ma : plugin.messagesC.getStringList("No Arguments (Menu)")) {
				        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ma));
				    }
				}
			}else {
				for(String ma : plugin.messagesC.getStringList("No Permission Message")) {
			        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ma));
			    }
			}
		}
		return false;
	}
}
