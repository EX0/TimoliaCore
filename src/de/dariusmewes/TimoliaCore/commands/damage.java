package de.dariusmewes.TimoliaCore.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class damage extends TCommand {

	public damage(String name) {
		super(name);
		setMinArgs(2);
		setMaxArgs(2);
		setUsage("/damage <Player> <amount>");
	}

	public void perform(CommandSender sender, String[] args) {
		Player target = Bukkit.getPlayer(args[0]);
		if (target == null) {
			sender.sendMessage(_("notonline"));
			return;
		}

		int amount;
		try {
			amount = Integer.valueOf(args[1]);
		} catch (NumberFormatException e) {
			sender.sendMessage(_("noInt"));
			return;
		}

		target.damage(amount);
		sender.sendMessage(_("playerDamaged"));
	}

}