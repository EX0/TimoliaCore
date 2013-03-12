/*
 *  Copyright:
 *  2013 Darius Mewes
 */

package de.timolia.core.cmds;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class cblock extends TCommand {

	public cblock(String name) {
		super(name);
		setMaxArgs(1);
		setUsage("/cblock [Player]");
		setDesc("Gives you or someone else a commandblock");
	}

	public void perform(CommandSender sender, String[] args) {
		Player target;
		if (args.length == 1) {
			target = Bukkit.getPlayer(args[0]);
			if (target == null) {
				sender.sendMessage(_("notonline"));
				return;
			}

		} else {
			if (!(sender instanceof Player)) {
				sender.sendMessage(_("ingame"));
				return;
			}

			target = (Player) sender;
		}

		target.getInventory().addItem(new ItemStack(Material.COMMAND, 1));
		sender.sendMessage(_("cblock"));
	}

}