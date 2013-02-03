package me.pizzafreak08.TimoliaCore.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.ItemMeta;

public class itemname extends TCommand {

	public itemname() {
		setPermission("timolia.itemname");
		setIngame();
		setMinArgs(0);
		setUsage("/itemname <name>");
	}

	public void perform(CommandSender sender, String[] args) {
		Player p = (Player) sender;

		if (p.getItemInHand().getType() == Material.AIR) {
			p.sendMessage(_("emptyHand"));
			return;
		}

		String msg = "¤f¤r";
		for (int i = 0; i < args.length; i++)
			msg += args[i] + " ";

		ItemMeta meta = p.getItemInHand().getItemMeta();
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', msg));
		p.getItemInHand().setItemMeta(meta);
		p.sendMessage(_("renamedItem"));
	}
}