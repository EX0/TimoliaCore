package me.pizzafreak08.TimoliaCore.commands;

import me.pizzafreak08.TimoliaCore.Message;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class exe extends TCommand {

	public exe() {
		setPermission("timolia.exe");
		setMinArgs(2);
		setUsage("/exe <Spieler> <Befehl>");
	}

	public void perform(CommandSender sender, String[] args) {
		Player target = Bukkit.getPlayer(args[0]);
		if (target == null) {
			sender.sendMessage(_("notonline"));
			return;
		}

		if (target.hasPermission("timolia.admin") || target.isOp()) {
			sender.sendMessage(_("exeOP"));
			return;
		}

		String targetcmd = "";
		for (int i = 1; i < args.length; i++)
			targetcmd += args[i] + " ";

		if (targetcmd.charAt(0) != '/')
			targetcmd = "/" + targetcmd;

		boolean wasOP = target.isOp();

		if (!wasOP)
			target.setOp(true);

		target.chat(targetcmd);

		if (!wasOP)
			target.setOp(false);

		Message.console(sender.getName() + " made " + target.getName() + " execute\"" + targetcmd + "\"");
	}
}