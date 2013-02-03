package me.pizzafreak08.TimoliaCore.commands;

import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class mode extends TCommand {

	public mode() {
		setPermission("timolia.mode");
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