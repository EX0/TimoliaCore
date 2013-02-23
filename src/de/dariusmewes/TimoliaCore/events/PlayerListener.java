/*
 *  Copyright:
 *  2013 Darius Mewes
 */

package de.dariusmewes.TimoliaCore.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.bukkit.event.player.PlayerQuitEvent;

import de.dariusmewes.TimoliaCore.TimoliaCore;
import de.dariusmewes.TimoliaCore.commands.access;
import de.dariusmewes.TimoliaCore.commands.deaths;
import de.dariusmewes.TimoliaCore.commands.listname;

public class PlayerListener implements Listener {

	public static String joinMsg;
	public static String quitMsg;
	private TimoliaCore instance;

	public PlayerListener(TimoliaCore instance) {
		this.instance = instance;
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		// custom quit-msg
		if (!quitMsg.equalsIgnoreCase("")) {
			String tjoinMsg = joinMsg.replaceAll("@p", event.getPlayer().getName());
			tjoinMsg = tjoinMsg.replaceAll("@a", String.valueOf(Bukkit.getOnlinePlayers().length));
			event.setJoinMessage(tjoinMsg);
		}

		// update-checking
		if (TimoliaCore.updateAvailable && (event.getPlayer().isOp() || event.getPlayer().hasPermission("headdrops.update"))) {
			event.getPlayer().sendMessage(TimoliaCore.PREFIX + "A new version is available!");
			event.getPlayer().sendMessage(TimoliaCore.PREFIX + "Get it at http://dev.bukkit.org/server-mods/timolia-core");
		}

		// colored listname
		if (event.getPlayer().hasPermission("tcore.listname.join")) {
			String listName;
			String name = event.getPlayer().getName();
			if (event.getPlayer().hasPermission("tcore.listname.red"))
				listName = "�c" + name;
			else if (event.getPlayer().hasPermission("tcore.listname.blue"))
				listName = "�9" + name;
			else if (event.getPlayer().hasPermission("tcore.listname.green"))
				listName = "�a" + name;
			else if (event.getPlayer().hasPermission("tcore.listname.orange"))
				listName = "�6" + name;
			else
				return;

			if (listName.length() > 12)
				listName = listName.substring(0, 11);

			listName += "�r";
			for (int i = 0; i < listName.length(); i++)
				if (!listname.allowed.contains(String.valueOf(listName.toLowerCase().charAt(i))))
					return;

			try {
				event.getPlayer().setPlayerListName(listName);
			} catch (Exception e) {

			}
		}
	}

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		if (!quitMsg.equalsIgnoreCase("")) {
			String tquitMsg = quitMsg.replaceAll("@p", event.getPlayer().getName());
			tquitMsg = tquitMsg.replaceAll("@a", String.valueOf(Bukkit.getOnlinePlayers().length - 1));
			event.setQuitMessage(tquitMsg);
		}
	}

	// Wartung
	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void onPlayerLogin(PlayerLoginEvent event) {
		if (instance.getConfig().getBoolean("maintenance") && !(access.isAllowed(event.getPlayer()))) {
			event.setKickMessage(ChatColor.translateAlternateColorCodes('&', instance.getConfig().getString("servername")) + ChatColor.WHITE + " " + instance.getConfig().getString("maintenancemsg"));
			event.setResult(Result.KICK_OTHER);
		}
	}

	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void onPlayerDeath(PlayerDeathEvent event) {
		if (deaths.hidingEnabled) {
			String vanillaMsg = event.getDeathMessage();
			event.setDeathMessage("");
			for (Player p : Bukkit.getOnlinePlayers()) {
				if (deaths.shuttedOff.contains(p.getName()))
					continue;

				if (instance.getConfig().getBoolean("darkerDeathMessages"))
					p.sendMessage(ChatColor.DARK_GRAY + vanillaMsg);
				else
					p.sendMessage(vanillaMsg);
			}
		}
	}

}