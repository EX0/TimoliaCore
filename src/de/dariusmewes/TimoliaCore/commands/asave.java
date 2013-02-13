package de.dariusmewes.TimoliaCore.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.CommandSender;

import de.dariusmewes.TimoliaCore.Message;

public class asave extends TCommand {

	public static boolean running = false;
	public static int taskID;

	public asave(String name) {
		super(name);
		setMinArgs(1);
		setMaxArgs(2);
	}

	public void perform(CommandSender sender, String[] args) {
		if (args[0].equalsIgnoreCase("start")) {
			if (startAutoSave()) {
				sender.sendMessage("gestartet");
				instance.getConfig().set("autosave", true);
				instance.saveConfig();
			} else
				sender.sendMessage("läuft schon!");

		} else if (args[0].equalsIgnoreCase("stop")) {
			if (stopAutoSave()) {
				sender.sendMessage("gestoppt");
				instance.getConfig().set("autosave", false);
				instance.saveConfig();
			} else
				sender.sendMessage("läuft nicht!");

		} else if (args[0].equalsIgnoreCase("delay")) {
			if (args.length == 1) {
				sender.sendMessage("gib eine zeit an!");
				return;
			}

			long delay;
			try {
				delay = Long.valueOf(args[1]);
			} catch (NumberFormatException e) {
				sender.sendMessage("keine zahl");
				return;
			}

			instance.getConfig().set("autosavedelay", delay);
			instance.saveConfig();
			sender.sendMessage("zeit gesetzt!");
			if (running) {
				stopAutoSave();
				startAutoSave();
				sender.sendMessage("neu gestartet");
			}

		} else if (args[0].equalsIgnoreCase("status")) {
			sender.sendMessage(running ? "läuft" : "läuft nicht");

		} else
			sender.sendMessage(prefix + usage);
	}

	public static boolean startAutoSave() {
		if (running)
			return false;

		long delay;
		try {
			delay = Long.valueOf(instance.getConfig().getString("autosavedelay")) * 20L * 60L;
		} catch (NumberFormatException e) {
			delay = 5L * 20L * 60L;
			Message.console("Could not parse autosave-delay from config. Using 5 Minutes...");
		}

		running = true;
		taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(instance, new Runnable() {
			public void run() {
				Bukkit.savePlayers();
				for (World w : Bukkit.getWorlds())
					w.save();

				if (instance.getConfig().getBoolean("autosavebcast")) {
					Message.online(ChatColor.DARK_BLUE + _("autosavebcast"));
					Message.console(_("autosavebcast"));
				}
			}
		}, delay, delay);
		return true;
	}

	public static boolean stopAutoSave() {
		if (!running)
			return false;

		running = false;
		Bukkit.getScheduler().cancelTask(taskID);
		return true;
	}

}