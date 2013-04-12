/*
 *  Copyright:
 *  2013 Darius Mewes
 */

package de.timolia.core.cmds;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.ItemMeta;

import de.timolia.core.TimoliaCore;

public class itemlore extends TCommand {

	protected void prepare() {
		permission();
		ingame();
		minArgs(2);
	}

	public void perform(CommandSender sender, String[] args) {
		Player p = (Player) sender;

		if (p.getItemInHand().getType() == Material.AIR) {
			p.sendMessage(_("emptyHand"));
			return;
		}

		ItemMeta meta = p.getItemInHand().getItemMeta();
		List<String> list = meta.getLore();

		String msg = "";
		for (int i = 1; i < args.length; i++)
			msg += args[i] + " ";

		msg = ChatColor.translateAlternateColorCodes('&', msg);

		if (args[0].equalsIgnoreCase("add")) {
			if (list == null)
				list = new ArrayList<String>();

			list.add(msg);
			sender.sendMessage(_("addedNote"));

		} else if (args[0].equalsIgnoreCase("remove")) {
			msg = TimoliaCore.getCorrectName(msg);

			if (list == null) {
				sender.sendMessage(_("noNotes"));
				return;
			}

			if (!list.contains(msg)) {
				sender.sendMessage(_("noNote"));
				return;
			}

			list.remove(msg);
			sender.sendMessage(_("removedNote"));

		} else {
			sender.sendMessage(usage());
			return;
		}

		meta.setLore(list);
		p.getItemInHand().setItemMeta(meta);
	}

}