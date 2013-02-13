/*
 *  Copyright:
 *  2013 Darius Mewes
 */

package de.dariusmewes.TimoliaCore.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class colors extends TCommand {

	public colors(String name) {
		super(name);
		setMinArgs(0);
		setMaxArgs(1);
		setUsage("/colors [Player]");
	}

	public void perform(CommandSender sender, String[] args) {
		Player p;
		if (args.length == 1) {
			p = Bukkit.getPlayer(args[0]);

			if (p == null) {
				sender.sendMessage(_("notonline"));
				return;
			}

			sender.sendMessage(_("colorsShown"));

		} else {
			if (sender instanceof Player)
				p = (Player) sender;

			else {
				sender.sendMessage(_("ingame"));
				return;
			}
		}

		p.sendMessage(_("colorList"));
		p.sendMessage("1 - " + ChatColor.DARK_BLUE + __("darkBlue"));
		p.sendMessage("2 - " + ChatColor.DARK_GREEN + __("darkGreen"));
		p.sendMessage("3 - " + ChatColor.DARK_AQUA + __("darkAqua"));
		p.sendMessage("4 - " + ChatColor.DARK_RED + __("darkRed"));
		p.sendMessage("5 - " + ChatColor.DARK_PURPLE + __("purple"));
		p.sendMessage("6 - " + ChatColor.GOLD + __("orange"));
		p.sendMessage("7 - " + ChatColor.GRAY + __("gray"));
		p.sendMessage("8 - " + ChatColor.DARK_GRAY + __("darkGray"));
		p.sendMessage("9 - " + ChatColor.BLUE + __("blue"));
		p.sendMessage("A - " + ChatColor.GREEN + __("green"));
		p.sendMessage("B - " + ChatColor.AQUA + __("aqua"));
		p.sendMessage("C - " + ChatColor.RED + __("red"));
		p.sendMessage("D - " + ChatColor.LIGHT_PURPLE + __("pink"));
		p.sendMessage("E - " + ChatColor.YELLOW + __("yellow"));
		p.sendMessage(ChatColor.RESET + "K - " + ChatColor.MAGIC + __("magic"));
		p.sendMessage("L - " + ChatColor.BOLD + __("bold"));
		p.sendMessage("M - " + ChatColor.STRIKETHROUGH + __("strikeThrough"));
		p.sendMessage("N - " + ChatColor.UNDERLINE + __("underline"));
		p.sendMessage("O - " + ChatColor.ITALIC + __("italic"));
	}

}