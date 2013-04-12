/*
 *  Copyright:
 *  2013 Darius Mewes
 */

package de.timolia.core.cmds;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ctp extends TCommand {

	protected void prepare() {
		permission();
		minArgs(3);
		maxArgs(4);
	}

	public void perform(CommandSender sender, String[] args) {
		Player p;
		if (args.length == 3) {
			if (!(sender instanceof Player)) {
				sender.sendMessage(_("ingame"));
				return;
			}

			p = (Player) sender;
		} else {
			p = Bukkit.getPlayer(args[3]);
			if (p == null) {
				sender.sendMessage(_("notonline"));
				return;
			}
		}

		double x, y, z;
		try {
			x = Double.valueOf(args[0].replaceAll(",", "."));
			y = Double.valueOf(args[1].replaceAll(",", "."));
			z = Double.valueOf(args[2].replaceAll(",", "."));
		} catch (NumberFormatException e) {
			sender.sendMessage(_("noInt"));
			return;
		}

		x = x >= 0 ? x + 0.5 : x - 0.5;
		z = z >= 0 ? z + 0.5 : z - 0.5;

		Location loc = new Location(p.getWorld(), x, y, z);

		if (loc.getBlock().getType() != Material.AIR && loc.getBlock().getType() != Material.WATER)
			loc.setY(p.getWorld().getHighestBlockYAt(loc));

		p.teleport(loc);
		sender.sendMessage(_("teleport", p.getName()));
	}

}