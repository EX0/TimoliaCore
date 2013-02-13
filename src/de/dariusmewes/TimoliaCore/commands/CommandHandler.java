/*
 *  Copyright:
 *  2013 Darius Mewes
 */

package de.dariusmewes.TimoliaCore.commands;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.dariusmewes.TimoliaCore.Message;
import de.dariusmewes.TimoliaCore.TimoliaCore;

public class CommandHandler {

	private static List<TCommand> commands = new ArrayList<TCommand>();

	public static void init(TimoliaCore instance) {
		add(new access("access"));
		add(new armor("armor"));
		add(new asave("asave"));
		add(new book("book"));
		add(new ca("ca"));
		add(new cblock("cblock"));
		add(new cc("cc"));
		add(new chat("chat"));
		add(new clock("clock"));
		add(new colors("colors"));
		add(new console("console"));
		add(new ctp("ctp"));
		add(new damage("damage"));
		add(new deaths("deaths"));
		add(new drop("drop"));
		add(new dump("dump"));
		add(new effect("effect"));
		add(new exe("exe"));
		add(new itemlore("itemlore"));
		add(new itemname("itemname"));
		add(new listname("listname"));
		add(new loc("loc"));
		add(new loclist("loclist"));
		add(new mode("mode"));
		add(new pingmsg("pingmsg"));
		add(new raw("raw"));
		add(new removeloc("removeloc"));
		add(new setloc("setloc"));
		add(new skick("skick"));
		add(new st("st"));
		add(new tcore("tcore"));
		add(new visible("visible"));

		TCommand.setPluginInstance(instance);
	}

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

	public static void add(TCommand command) {
		commands.add(command);
	}

	public static void list() {
		try {
			File file = new File(System.getProperty("user.home") + File.separator + "commands.txt");
			BufferedWriter output = new BufferedWriter(new FileWriter(file));
			for (TCommand cmd : commands) {
				output.append("    " + cmd.getName() + ":");
				output.newLine();
				output.append("        usage: " + cmd.getCleanUsage());
				output.newLine();
				output.append("        description: ");
				output.newLine();
			}

			output.close();
			Message.console("DONE");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static TCommand getCommand(Command cmd) {
		for (TCommand c : commands) {
			if (cmd.getName().equalsIgnoreCase(c.getName()))
				return c;
		}

		return null;
	}

}