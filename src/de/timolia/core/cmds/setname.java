/*
 *  Copyright:
 *  2013 Darius Mewes
 */

package de.timolia.core.cmds;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class setname extends TCommand {

	public setname(String name) {
		super(name);
		setIngame();
		setMinArgs(1);
		setMaxArgs(1);
		setUsage("/setname <name>");
		setDesc("Sets your name to a given string");
	}

	public void perform(CommandSender sender, String[] args) {
		Player p = (Player) sender;
		p.setDisplayName(args[0]);
		sender.sendMessage(_("displayNameChanged"));
	}

}