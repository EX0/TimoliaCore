package de.dariusmewes.TimoliaCore.commands;

import java.util.Vector;


import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.dariusmewes.TimoliaCore.Message;
import de.dariusmewes.TimoliaCore.TimoliaCore;

public class CommandHandler {

	private static Vector<TCommand> commands = new Vector<TCommand>();

	public static void handleCommand(CommandSender sender, Command cmd, String args[]) {
		TCommand c = getCommand(cmd);
		if (!isvalidCommand(sender, c, args))
			return;

		c.perform(sender, args);
	}

	public static boolean isvalidCommand(CommandSender sender, TCommand cmd, String args[]) {
		if (cmd.onlyIngame() && !(sender instanceof Player)) {
			sender.sendMessage(Message._("ingame"));
			return false;
		}

		if (!cmd.getPermission().equalsIgnoreCase("") && !sender.hasPermission(cmd.getPermission())) {
			sender.sendMessage(Message._("noperm"));
			return false;
		}

		if (args.length >= cmd.getMinArgs() && (args.length <= cmd.getMaxArgs() || cmd.getMaxArgs() == -1))
			return true;

		else
			sender.sendMessage(cmd.getUsage());
		return false;
	}

	public static void addCommand(TCommand command) {
		command.setName(command.getClass().getSimpleName());
		commands.add(command);
	}

	public static TCommand getCommand(Command cmd) {
		for (TCommand c : commands) {
			if (cmd.getName().equalsIgnoreCase(c.getName()))
				return c;
		}
		
		return null;
	}

	public static void setPluginInstance(TimoliaCore instance) {
		TCommand.setPluginInstance(instance);
	}
}