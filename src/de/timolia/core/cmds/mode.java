/*
 *  Copyright:
 *  2013 Darius Mewes
 */

package de.timolia.core.cmds;

import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class mode extends TCommand {

	protected void prepare() {
		permission();
		ingame();
		maxArgs(0);
	}

	public void perform(CommandSender sender, String[] args) {
		Player p = (Player) sender;

		if (p.getGameMode() == GameMode.CREATIVE)
			p.setGameMode(GameMode.SURVIVAL);
		else
			p.setGameMode(GameMode.CREATIVE);

		p.sendMessage(_("gamemodeChanged"));
	}

}