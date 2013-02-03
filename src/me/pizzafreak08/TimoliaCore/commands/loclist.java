package me.pizzafreak08.TimoliaCore.commands;

import java.io.File;

import me.pizzafreak08.TimoliaCore.TimoliaCore;

import org.bukkit.command.CommandSender;

public class loclist extends TCommand {

	public loclist() {
		setPermission("timolia.loclist");
		setMaxArgs(0);
		setUsage("/loclist");
	}

	public void perform(CommandSender sender, String[] args) {
		File dir = new File(TimoliaCore.dataFolder + File.separator + "locations" + File.separator);
		File[] list = dir.listFiles();

		if (list.length == 0) {
			sender.sendMessage(_("noLocsFound"));
			return;
		}

		String output = "";

		int i = 0;

		while (i < (list.length - 1)) {
			String name = list[i].getName();
			name = name.substring(0, name.length() - 9);
			output += name + ", ";
			i++;
		}

		String name = list[i].getName();
		name = name.substring(0, name.length() - 9);
		output += name;

		sender.sendMessage(_("locs", output));
	}
}