package de.dariusmewes.TimoliaCore.commands;

import java.io.File;


import org.bukkit.command.CommandSender;

import de.dariusmewes.TimoliaCore.TimoliaCore;

public class removeloc extends TCommand {

	public removeloc() {
		setPermission("timolia.removeloc");
		setMinArgs(1);
		setMaxArgs(1);
		setUsage("/removeloc <Location>");
	}

	public void perform(CommandSender sender, String[] args) {
		String name = args[0];
		File file = new File(TimoliaCore.dataFolder + File.separator + "locations" + File.separator + name + ".location");

		if (!file.exists()) {
			sender.sendMessage(_("locNotFound"));
			return;
		}

		file.delete();
		sender.sendMessage(_("locRemoved"));
	}
}