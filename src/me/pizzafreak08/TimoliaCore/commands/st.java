package me.pizzafreak08.TimoliaCore.commands;

import me.pizzafreak08.TimoliaCore.Message;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class st extends TCommand {

	public st() {
		setPermission("timolia.st");
		setIngame();
		setMinArgs(1);
		setUsage("/st <msg>");
	}

	public void perform(CommandSender sender, String[] args) {
		Player p = (Player) sender;
		String msg = "";
		for (int i = 0; i < args.length; i++)
			msg += args[i] + " ";

		Message.certain(ChatColor.GOLD + "St - " + ChatColor.GRAY + p.getDisplayName() + ": " + ChatColor.WHITE + ChatColor.translateAlternateColorCodes('&', msg), "timolia.st");
	}
}