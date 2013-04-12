/*
 *  Copyright:
 *  2013 Darius Mewes
 */

package de.timolia.core.cmds;

import org.bukkit.command.CommandSender;

public class tcore extends TCommand {

	protected void prepare() {
		permission();
		minArgs(1);
		maxArgs(1);
	}

	public void perform(CommandSender sender, String[] args) {
		if (args[0].equalsIgnoreCase("reload")) {
			instance.reloadConfig();
			instance.loadConfig();
			sender.sendMessage(_("configReloaded"));
		} else
			sender.sendMessage(usage());
	}

}