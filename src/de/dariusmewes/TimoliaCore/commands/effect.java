/*
 *  Copyright:
 *  2013 Darius Mewes
 */

package de.dariusmewes.TimoliaCore.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class effect extends TCommand {

	public effect(String name) {
		super(name);
		setMinArgs(2);
		setMaxArgs(5);
		setUsage("/effect <add/remove> <effect> <power> <duration> [player]");
	}

	public void perform(CommandSender sender, String[] args) {
		if (args[0].equalsIgnoreCase("add")) {
			if (args.length < 4) {
				sender.sendMessage(usage);
				return;
			}

			PotionEffectType type = isInt(args[1]) ? PotionEffectType.getById(Integer.valueOf(args[1])) : PotionEffectType.getByName(args[1]);
			if (type == null) {
				sender.sendMessage(_("invalidPotionEffect", args[1]));
				return;
			}

			if (!isInt(args[2]) || !isInt(args[3])) {
				sender.sendMessage(_("noInt"));
				return;
			}

			Player target;

			if (args.length == 4) {
				if (!(sender instanceof Player)) {
					sender.sendMessage(_("ingame"));
					return;
				}

				target = (Player) sender;

			} else {
				target = Bukkit.getPlayer(args[4]);
				if (target == null) {
					sender.sendMessage(_("notonline"));
					return;
				}
			}

			if (target.hasPotionEffect(type))
				target.removePotionEffect(type);

			target.addPotionEffect(new PotionEffect(type, (Integer.valueOf(args[3]) * 20), Integer.valueOf(args[2])));
			sender.sendMessage(_("gotEffect", target.getName(), type.getName()));

		} else if (args[0].equalsIgnoreCase("remove")) {
			Player target;
			if (args.length == 2) {
				if (!(sender instanceof Player)) {
					sender.sendMessage(_("ingame"));
					return;
				}

				target = (Player) sender;

			} else {
				target = Bukkit.getPlayer(args[2]);
				if (target == null) {
					sender.sendMessage(_("notonline"));
					return;
				}
			}

			PotionEffectType type = isInt(args[1]) ? PotionEffectType.getById(Integer.valueOf(args[1])) : PotionEffectType.getByName(args[1]);
			if (type == null) {
				sender.sendMessage(_("invalidPotionEffect", args[1]));
				return;
			}

			if (target.hasPotionEffect(type)) {
				target.removePotionEffect(type);
				sender.sendMessage(_("removedEffect", target.getName()));
			} else {
				sender.sendMessage(_("doesntHaveEffect", target.getName(), type.getName()));
			}

		} else {
			sender.sendMessage(usage);

		}
	}

	private boolean isInt(String tof) {
		try {
			Integer.valueOf(tof);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

}