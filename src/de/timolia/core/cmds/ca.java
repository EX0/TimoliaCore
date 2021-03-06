/*
 *  Copyright:
 *  2013 Darius Mewes
 */

package de.timolia.core.cmds;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ca extends TCommand {

	protected void prepare() {
		permission();
		ingame();
		maxArgs(1);
	}

	public void perform(CommandSender sender, String[] args) {
		Player p = (Player) sender;

		if (args.length == 0) {
			if (!p.hasPermission("tcore.ca")) {
				sender.sendMessage(_("noperm"));
				return;
			}
			p.getInventory().clear();
			p.getInventory().setArmorContents(null);
			p.sendMessage(_("caDel"));
		} else if (args.length == 1) {
			if (!p.hasPermission("tcore.ca.other")) {
				p.sendMessage(_("noperm"));
				return;
			}

			if (args[0].equalsIgnoreCase("all")) {
				for (Player one : Bukkit.getOnlinePlayers()) {
					one.getInventory().clear();
					one.getInventory().setArmorContents(null);
				}
				sender.sendMessage(_("caDelAll"));
				return;
			}

			Player target = Bukkit.getPlayer(args[0]);

			if (target == null) {
				p.sendMessage(_("notonline"));
				return;
			}

			target.getInventory().clear();
			target.getInventory().setArmorContents(null);
			target.sendMessage(_("caDelOther", p.getName()));

			p.sendMessage(_("caDelOtherConfirm", target.getName()));
		} else {
			p.sendMessage(usage());
		}
	}

}