/*
 *  Copyright:
 *  2013 Darius Mewes
 */

package de.dariusmewes.TimoliaCore.commands;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class drop extends TCommand {

	public drop(String name) {
		super(name);
		setIngame();
		setMaxArgs(1);
		setUsage("/drop [all/item-id]");
	}

	public void perform(CommandSender sender, String[] args) {
		Player p = (Player) sender;

		boolean all = false;
		int count = 0;

		if (args.length == 0) {
			if (p.getItemInHand().getType() == Material.AIR) {
				p.sendMessage(_("emptyHand"));
				return;
			}

			Item drop = p.getWorld().dropItem(p.getEyeLocation(), p.getItemInHand());
			Vector vec = new Vector(p.getLocation().getDirection().getX() * 0.3, p.getLocation().getDirection().getY() * 0.3, p.getLocation().getDirection().getZ() * 0.3);
			drop.setVelocity(drop.getVelocity().add(vec));
			p.getInventory().remove(p.getItemInHand());
			p.sendMessage(_("droppedHand"));

		} else if (args.length == 1) {
			if (args[0].equalsIgnoreCase("all")) {
				all = true;
			} else if (!isNumber(args[0])) {
				p.sendMessage(_("invalidItemID"));
				return;
			}

			for (ItemStack item : p.getInventory().getContents()) {
				if (item != null) {
					if (all || item.getTypeId() == Integer.valueOf(args[0])) {
						Item drop = p.getWorld().dropItem(p.getEyeLocation(), item);
						Vector vec = new Vector(p.getLocation().getDirection().getX() * 0.3, p.getLocation().getDirection().getY() * 0.3, p.getLocation().getDirection().getZ() * 0.3);
						drop.setVelocity(drop.getVelocity().add(vec));
						count += item.getAmount();
						p.getInventory().remove(item);
					}
				}
			}

			p.sendMessage(count == 1 ? _("droppedOne") : _("droppedMultiple", String.valueOf(count)));
		}
	}

	private boolean isNumber(String number) {
		try {
			Integer.valueOf(number);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

}