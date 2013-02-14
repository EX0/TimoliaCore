/*
 *  Copyright:
 *  2013 Darius Mewes
 */

package de.dariusmewes.TimoliaCore.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import de.dariusmewes.TimoliaCore.TimoliaCore;

public class ServerListener implements Listener {

	private TimoliaCore instance;

	public ServerListener(TimoliaCore instance) {
		this.instance = instance;
	}

	@EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
	public void onServerListPing(ServerListPingEvent event) {
		String motd = instance.getConfig().getString("motd");
		if (!motd.equalsIgnoreCase(""))
			event.setMotd(motd);
	}

}