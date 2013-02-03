package me.pizzafreak08.TimoliaCore.events;

import me.pizzafreak08.TimoliaCore.TimoliaCore;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {

	public static String joinMsg;
	public static String quitMsg;
	private TimoliaCore plugin;

	public PlayerListener(TimoliaCore plugin) {
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
	public void onPlayerChat(AsyncPlayerChatEvent event) {
		String[] parts = event.getMessage().split(" ");

		for (int i = 0; i < parts.length; i++) {
			// Usernamen hervorheben
			if (event.getPlayer().hasPermission("timolia.chat.names")) {
				for (Player p : Bukkit.getOnlinePlayers()) {
					if (parts[i].contains(p.getName())) {
						parts[i] = ChatColor.AQUA + parts[i] + ChatColor.WHITE;
						break;
					}
				}
			}

			// Weiteres
		}

		String output = "";
		for (String s : parts) {
			output += s + " ";
		}

		event.setMessage(output);
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		if (!joinMsg.equalsIgnoreCase(""))
			event.setJoinMessage(joinMsg.replaceAll("%p", event.getPlayer().getName()));

		if (TimoliaCore.updateAvailable && (event.getPlayer().isOp() || event.getPlayer().hasPermission("headdrops.update"))) {
			event.getPlayer().sendMessage(TimoliaCore.PREFIX + "A new version is available!");
			event.getPlayer().sendMessage(TimoliaCore.PREFIX + "Get it at http://dev.bukkit.org/server-mods/timolia-core");
		}
	}

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		if (!quitMsg.equalsIgnoreCase(""))
			event.setQuitMessage(quitMsg.replaceAll("%p", event.getPlayer().getName()));
	}

	// Wartung
	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void onPlayerLogin(PlayerLoginEvent event) {
		if (plugin.getConfig().getBoolean("wartungstatus") && !(event.getPlayer().hasPermission("timolia.identify.mod") || event.getPlayer().hasPermission("timolia.identify.admin"))) {
			event.setKickMessage(ChatColor.DARK_RED + "[Timolia]\n\n" + ChatColor.WHITE + plugin.getConfig().getString("wartungmsg"));
			event.setResult(Result.KICK_OTHER);
		}
	}
}