/*
 *  Copyright:
 *  2013 Darius Mewes
 */

package de.dariusmewes.TimoliaCore.commands;

import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class mode extends TCommand {

	public mode(String name) {
		super(name);
		setIngame();
		setMaxArgs(0);
		setUsage("/mode");
	}

	public void perform(CommandSender sender, String[] args) {
		Player p = (Player) sender;

		if (p.getGameMode() == GameMode.CREATIVE) {
			p.setGameMode(GameMode.SURVIVAL);
			p.sendMessage(_("gamemodeChanged"));
		} else {
			p.setGameMode(GameMode.CREATIVE);
			p.sendMessage(_("gamemodeChanged"));
		}
	}

}