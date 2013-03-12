/*
 *  Copyright:
 *  2013 Darius Mewes
 */

package de.timolia.core.cmds;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cc extends TCommand {

	public cc(String name) {
		super(name);
		setIngame();
		setMaxArgs(1);
		setUsage("/cc [all/-a]");
		setDesc("Clear your or everyones chat");
	}

	public void perform(CommandSender sender, String[] args) {

		Player p = (Player) sender;

		if (args.length == 0)
			for (int i = 0; i < 20; i++)
				p.sendMessage("");

		else if (args.length == 1 && args[0].equalsIgnoreCase("all")) {
			if (!p.hasPermission("tcore.cc.all")) {
				p.sendMessage(_("noperm"));
				return;
			}

			int count = 0;
			for (Player onlinep : Bukkit.getOnlinePlayers())
				if (!onlinep.isOp()) {
					for (int i = 0; i < 20; i++)
						onlinep.sendMessage("");

					count++;
				}

			p.sendMessage(_("ccAll", String.valueOf(count)));

		} else if (args.length == 1 && args[0].equalsIgnoreCase("-a"))
			for (int i = 0; i < 120; i++)
				p.sendMessage("");

		else
			p.sendMessage(getUsage());
	}

}