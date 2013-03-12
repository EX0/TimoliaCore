/*
 *  Copyright:
 *  2013 Darius Mewes
 */

package de.timolia.core.cmds;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class dump extends TCommand {

	public dump(String name) {
		super(name);
		setIngame();
		setMaxArgs(0);
		setUsage("/dump");
		setDesc("Dump the item in your hand");
	}

	public void perform(CommandSender sender, String[] args) {
		Player p = (Player) sender;

		if (p.getItemInHand().getType() == Material.AIR) {
			p.sendMessage(_("emptyHand"));
			return;
		}

		p.sendMessage(_("dumped", p.getItemInHand().getType().toString()));
		p.setItemInHand(new ItemStack(Material.AIR));
	}

}