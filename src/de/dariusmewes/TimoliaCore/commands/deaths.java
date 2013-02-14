/*
 *  Copyright:
 *  2013 Darius Mewes
 */

package de.dariusmewes.TimoliaCore.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class deaths extends TCommand {

	public static List<String> shuttedOff = new ArrayList<String>();
	public static boolean hidingEnabled = false;

	public deaths(String name) {
		super(name);
		setIngame();
		setMaxArgs(1);
		setUsage("/deaths <on/off/clear>");
		setDesc("Hide deathmessages");
	}

	public void perform(CommandSender sender, String[] args) {
		if (args.length == 1 && args[0].equalsIgnoreCase("clear")) {
			if (!sender.hasPermission("tcore.deaths.clear")) {
				sender.sendMessage(_("noperm"));
				return;
			}

			shuttedOff.clear();
			sender.sendMessage(_("deathsForAll"));
			return;
		}

		Player p = (Player) sender;
		if (shuttedOff.contains(p.getName())) {
			shuttedOff.remove(p.getName());
			sender.sendMessage(_("deathsActivated"));
		} else {
			shuttedOff.add(p.getName());
			sender.sendMessage(_("deathsDeactivated"));
		}
	}

}