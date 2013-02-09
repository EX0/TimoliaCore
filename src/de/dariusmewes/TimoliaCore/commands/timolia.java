package de.dariusmewes.TimoliaCore.commands;


import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import de.dariusmewes.TimoliaCore.TimoliaCore;

public class timolia extends TCommand {

	public timolia() {
		setPermission("timolia.admin");
		setMinArgs(1);
		setUsage("/timolia reload v motd <msg> v wartung <on/off> [msg]");
	}

	public void perform(CommandSender sender, String[] args) {
		if (args[0].equalsIgnoreCase("reload")) {
			instance.reloadConfig();
			instance.loadConfig();
			sender.sendMessage(_("configReloaded"));
		}

		else if (args[0].equalsIgnoreCase("motd")) {
			String msg = "";

			if (args.length == 1) {
				instance.getConfig().set("motd", "");
				instance.saveConfig();
				sender.sendMessage(_("motdReset"));
				return;
			}

			for (int i = 1; i < args.length; i++)
				msg += args[i] + " ";

			instance.getConfig().set("motd", ChatColor.translateAlternateColorCodes('&', msg));
			instance.saveConfig();

			sender.sendMessage(_("motdSet"));
		}

		else if (args[0].equalsIgnoreCase("wartung")) {
			if (args.length < 2) {
				sender.sendMessage(usage);
				return;
			}

			if (args[1].equalsIgnoreCase("off")) {
				instance.getConfig().set("wartungstatus", false);
				instance.saveConfig();
				sender.sendMessage(_("accessOff"));

			} else if (args[1].equalsIgnoreCase("on")) {
				String msg = "Timolia wird momentan gewartet. Besuche timolia.de fŸr genauere Infos.";
				if (args.length > 2) {
					msg = "";
					for (int i = 2; i < args.length; i++)
						msg += args[i] + " ";

					sender.sendMessage(_("msgSet"));
				}

				msg = ChatColor.translateAlternateColorCodes('&', msg);
				instance.getConfig().set("wartungstatus", true);
				instance.getConfig().set("wartungmsg", msg);
				instance.saveConfig();

				sender.sendMessage(_("accessOn"));

			} else
				sender.sendMessage(TimoliaCore.PREFIX + "/timolia wartung <on/off> [msg]");

		} else
			sender.sendMessage(usage);
	}
}