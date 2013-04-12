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

public class armor extends TCommand {

	protected void prepare() {
		permission();
		minArgs(2);
		maxArgs(3);
	}

	public void perform(CommandSender sender, String[] args) {
		if (args.length == 2) {
			if (!(sender instanceof Player)) {
				sender.sendMessage(_("ingame"));
				return;
			}

			Player p = (Player) sender;
			applyArmor(p, args[0], args[1], p);

		} else if (args.length == 3) {
			Player target = Bukkit.getPlayer(args[0]);
			if (target == null) {
				sender.sendMessage(_("notonline"));
				return;
			}

			applyArmor(target, args[1], args[2], sender);
		}
	}

	private void applyArmor(Player target, String slot, String id, CommandSender sender) {
		if (!isInt(slot) || !isInt(id)) {
			sender.sendMessage(_("noInt"));
			return;
		}

		Material mat = Material.getMaterial(Integer.valueOf(id));

		if (mat == null) {
			sender.sendMessage(_("invalidItemID"));
			return;
		}

		switch (Integer.valueOf(slot)) {
		case 1:
			target.getInventory().setHelmet(new ItemStack(mat, 1));
			break;
		case 2:
			target.getInventory().setChestplate(new ItemStack(mat, 1));
			break;
		case 3:
			target.getInventory().setLeggings(new ItemStack(mat, 1));
			break;
		case 4:
			target.getInventory().setBoots(new ItemStack(mat, 1));
			break;
		default:
			sender.sendMessage(_("invalidSlot"));
			return;
		}

		sender.sendMessage(_("armorSet"));
	}

	private boolean isInt(String object) {
		try {
			Integer.valueOf(object);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

}