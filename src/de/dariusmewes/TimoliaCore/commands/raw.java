package de.dariusmewes.TimoliaCore.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import de.dariusmewes.TimoliaCore.Message;

public class raw extends TCommand {

	public raw(String name) {
		super(name);
		setMinArgs(1);
		setUsage("/raw [-s] <msg>");
	}

	public void perform(CommandSender sender, String[] args) {
		int start;

		if (args[0].equalsIgnoreCase("-s")) {
			if (args.length < 2) {
				sender.sendMessage(usage);
				return;
			}

			start = 1;

		} else {
			Message.certain(_("fakemsgNotify"), "timolia.raw.notify");
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