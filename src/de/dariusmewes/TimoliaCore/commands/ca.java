package de.dariusmewes.TimoliaCore.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ca extends TCommand {

	public ca(String name) {
		super(name);
		setMaxArgs(1);
		setIngame();
		setUsage("/ca [Player]");
	}

	public void perform(CommandSender sender, String[] args) {
		Player p = (Player) sender;

		if (args.length == 0) {
			if (!p.hasPermission("tcore.ca")) {
				sender.sendMessage(_("noperm"));
				return;
			}
			p.getInventory().clear();
			p.getInventory().setArmorContents(null);
			p.sendMessage(_("caDel"));
		} else if (args.length == 1) {
			if (!p.hasPermission("tcore.ca.other")) {
				p.sendMessage(_("noperm"));
				return;
			}

			Player target = Bukkit.getPlayer(args[0]);

			if (target == null) {
				p.sendMessage(_("notonline"));
				return;
			}

			target.getInventory().clear();
			target.getInventory().setArmorContents(null);
			target.sendMessage(_("caDelOtherConfirm", p.getName()));

			p.sendMessage(_("caDelOther", target.getName()));
		} else {
			p.sendMessage(usage);
		}
	}

}