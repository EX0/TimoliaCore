/*
 *  Copyright:
 *  2013 Darius Mewes
 */

package de.timolia.core.cmds;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

public class visible extends TCommand {

	public visible(String name) {
		super(name);
		setMaxArgs(1);
		setUsage("/visible [player/all]");
		setDesc("Remove someones/everyones invisibility-effect");
	}

	public void perform(CommandSender sender, String[] args) {
		if (args.length == 0) {
			String names = "";
			for (Player p : Bukkit.getOnlinePlayers()) {
				if (!p.hasPotionEffect(PotionEffectType.INVISIBILITY))
					continue;

				names += p.getName() + " ";
			}

			sender.sendMessage(_("invisibleList", names));
			return;
		}

		if (!args[0].equalsIgnoreCase("all")) {
			Player p = Bukkit.getPlayer(args[0]);
			if (p == null) {
				sender.sendMessage(_("notonline"));
				return;
			}

			if (!p.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
				sender.sendMessage(_("notInvisible"));
				return;
			}

			p.removePotionEffect(PotionEffectType.INVISIBILITY);
			sender.sendMessage(_("madevisible", p.getName()));

		} else if (args[0].equalsIgnoreCase("all")) {
			if (!sender.hasPermission("tcore.sichtbar.all")) {
				sender.sendMessage(_("noperm"));
				return;
			}

			String names = "";
			for (Player p : Bukkit.getOnlinePlayers()) {
				if (!p.hasPotionEffect(PotionEffectType.INVISIBILITY))
					continue;

				p.removePotionEffect(PotionEffectType.INVISIBILITY);
				names += p.getName() + " ";
			}

			sender.sendMessage(_("madeVisibleList", names));
		}
	}

}