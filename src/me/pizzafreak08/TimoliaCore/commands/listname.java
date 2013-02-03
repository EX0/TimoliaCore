package me.pizzafreak08.TimoliaCore.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class listname extends TCommand {

	public listname() {
		setPermission("timolia.listname");
		setMinArgs(1);
		setMaxArgs(2);
		setUsage("/listname [Player] <Name>");
	}

	public void perform(CommandSender sender, String[] args) {
		if (args[0].equalsIgnoreCase("reset")) {
			if (!sender.hasPermission("timolia.listname.reset")) {
				sender.sendMessage(_("noperm"));
				return;
			}

			for (Player p : Bukkit.getOnlinePlayers())
				p.setPlayerListName(p.getName());

			sender.sendMessage(_("listReset"));
			return;
		}

		Player target;
		if (args.length == 1) {
			if (!(sender instanceof Player)) {
				sender.sendMessage(_("ingame"));
				return;
			}
			target = (Player) sender;

		} else {
			target = Bukkit.getPlayer(args[0]);
			if (target == null) {
				sender.sendMessage(_("notonline"));
				return;
			}
		}

		target.setPlayerListName(args.length == 1 ? args[0] : args[1]);
		sender.sendMessage(_("listNameSet"));
	}
}