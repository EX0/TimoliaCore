package me.pizzafreak08.TimoliaCore.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class chat extends TCommand {

	public chat() {
		setPermission("timolia.chat");
		setMinArgs(2);
		setUsage("/chat <Player> <msg>");
	}

	public void perform(CommandSender sender, String[] args) {
		Player target = Bukkit.getPlayer(args[0]);

		if (target == null) {
			sender.sendMessage(_("notonline"));
			return;
		}

		String output = "";

		for (int i = 1; i < args.length; i++)
			output += args[i] + " ";

		target.chat(output);
	}
}