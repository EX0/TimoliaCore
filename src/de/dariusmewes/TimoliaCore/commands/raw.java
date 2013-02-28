/*
 *  Copyright:
 *  2013 Darius Mewes
 */

package de.dariusmewes.TimoliaCore.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import de.dariusmewes.TimoliaCore.Message;

public class raw extends TCommand {

	public raw(String name) {
		super(name);
		setMinArgs(1);
		setUsage("/raw [-s] <msg>");
		setDesc("Broadcast a raw text-message");
	}

	public void perform(CommandSender sender, String[] args) {
		int start;

		if (args[0].equalsIgnoreCase("-s")) {
			if (args.length < 2) {
				sender.sendMessage(getUsage());
				return;
			}

			start = 1;

		} else {
			Message.certain(_("fakeMsgNotify"), "tcore.raw.notify");
			start = 0;
		}

		String msg = "";
		for (int i = start; i < args.length; i++)
			msg += args[i] + " ";

		msg = ChatColor.translateAlternateColorCodes('&', msg);

		Message.online(msg);
		Message.console("[FAKEMESSAGE] " + msg);
	}

}