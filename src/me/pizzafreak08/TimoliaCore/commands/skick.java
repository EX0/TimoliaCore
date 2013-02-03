package me.pizzafreak08.TimoliaCore.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class skick extends TCommand {

	public skick() {
		setPermission("timolia.skick");
		setMinArgs(1);
		setUsage("/skick <Spieler> <msg>");
	}

	public void perform(CommandSender sender, String[] args) {
		Player target = Bukkit.getPlayer(args[0]);

		if (target == null) {
			sender.sendMessage(_("notonline"));
			return;
		}

		String msg = "Internal Server Error";

		if (args.length > 1) {
			msg = "";
			for (int i = 1; i < args.length; i++)
				msg += args[i] + " ";
		}

		target.kickPlayer(ChatColor.translateAlternateColorCodes('&', msg));
	}
}