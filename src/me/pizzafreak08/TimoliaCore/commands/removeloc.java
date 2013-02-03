package me.pizzafreak08.TimoliaCore.commands;

import java.io.File;

import me.pizzafreak08.TimoliaCore.TimoliaCore;

import org.bukkit.command.CommandSender;

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