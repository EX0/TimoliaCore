/*
 *  Copyright:
 *  2013 Darius Mewes
 */

package de.timolia.core.cmds;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.timolia.core.Message;

public class st extends TCommand {

	protected void prepare() {
		permission();
		minArgs(1);
	}

	public void perform(CommandSender sender, String[] args) {
		String name = (sender instanceof Player) ? ((Player) sender).getDisplayName() : sender.getName();
		String msg = "";
		for (int i = 0; i < args.length; i++)
			msg += args[i] + " ";

		Message.certain(ChatColor.GOLD + "St - " + ChatColor.GRAY + name + ": " + ChatColor.WHITE + ChatColor.translateAlternateColorCodes('&', msg), "tcore.st");
	}

}
