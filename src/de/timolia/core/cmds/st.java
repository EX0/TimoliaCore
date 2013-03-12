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

	public st(String name) {
		super(name);
		setIngame();
		setMinArgs(1);
		setUsage("/st <msg>");
		setDesc("Send Messages to other Staffs");
	}

	public void perform(CommandSender sender, String[] args) {
		Player p = (Player) sender;
		String msg = "";
		for (int i = 0; i < args.length; i++)
			msg += args[i] + " ";

		Message.certain(ChatColor.GOLD + "St - " + ChatColor.GRAY + p.getDisplayName() + ": " + ChatColor.WHITE + ChatColor.translateAlternateColorCodes('&', msg), "tcore.st");
	}

}