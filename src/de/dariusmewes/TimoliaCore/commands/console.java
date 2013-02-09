package de.dariusmewes.TimoliaCore.commands;


import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import de.dariusmewes.TimoliaCore.Message;

public class console extends TCommand {

	public console() {
		setPermission("timolia.console");
		setMinArgs(1);
		setUsage("/console <cmd>");
	}

	public void perform(CommandSender sender, String[] args) {
		String command = "";

		for (int i = 0; i < args.length; i++)
			command += args[i] + " ";

		if (command.charAt(0) == '/') command = command.substring(1, command.length());

		Message.console(sender.getName() + " executed console-command: " + command);
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);

		sender.sendMessage(_("consoleExecute", command));
	}
}