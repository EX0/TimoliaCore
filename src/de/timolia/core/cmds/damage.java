/*
 *  Copyright:
 *  2013 Darius Mewes
 */

package de.timolia.core.cmds;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class damage extends TCommand {

	protected void prepare() {
		permission();
		minArgs(2);
		maxArgs(2);
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