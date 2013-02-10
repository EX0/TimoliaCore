package de.dariusmewes.TimoliaCore.commands;

import org.bukkit.command.CommandSender;

public class tcore extends TCommand {

	public tcore(String name) {
		super(name);
		setMinArgs(1);
		setMaxArgs(1);
		setUsage("/tcore reload");
	}

	public void perform(CommandSender sender, String[] args) {
		if (args[0].equalsIgnoreCase("reload")) {
			instance.reloadConfig();
			instance.loadConfig();
			sender.sendMessage(_("configReloaded"));
		} else
			sender.sendMessage(prefix + usage);
	}

}