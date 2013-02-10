package de.dariusmewes.TimoliaCore.commands;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.dariusmewes.TimoliaCore.TimoliaCore;

public class access extends TCommand {

	private static List<String> players = new ArrayList<String>();

	public access(String name) {
		super(name);
		setMinArgs(1);
		setUsage("/access <allow/deny> [message] OR <add/remove> <name> OR reload OR setmsg <msg>");
	}

	public void perform(CommandSender sender, String[] args) {
		if (args[0].equalsIgnoreCase("reload")) {
			try {
				load();
				sender.sendMessage(_("listReloaded"));
			} catch (IOException e) {
				sender.sendMessage(_("errorLoadList"));
			}
			return;

		} else if (args[0].equalsIgnoreCase("allow")) {
			instance.getConfig().set("maintenance", false);
			instance.saveConfig();
			sender.sendMessage(_("accessOff"));
			return;

		} else if (args[0].equalsIgnoreCase("deny")) {
			instance.getConfig().set("maintenance", true);
			sender.sendMessage(_("accessOn"));
			if (args.length > 1) {
				String msg = "";
				for (int i = 1; i < args.length; i++)
					msg += args[i] + " ";

				instance.getConfig().set("maintenancemsg", ChatColor.translateAlternateColorCodes('&', msg));
				sender.sendMessage(_("msgSet"));
			}
			instance.saveConfig();
			return;
		}

		if (args.length == 1) {
			sender.sendMessage(prefix + usage);
			return;
		}

		// mind. 2 args

		if (args[0].equalsIgnoreCase("add")) {
			if (players.contains(args[1].toLowerCase())) {
				sender.sendMessage(_("alreadyOnTheList", args[1]));
				return;
			}

			try {
				players.add(args[1].toLowerCase());
				save();
				sender.sendMessage(_("addedToList", args[1]));
			} catch (IOException e) {
				sender.sendMessage(_("errorSaveList"));
			}

		} else if (args[0].equalsIgnoreCase("remove")) {
			if (!players.contains(args[1].toLowerCase())) {
				sender.sendMessage(_("notOnTheList", args[1]));
				return;
			}

			try {
				players.remove(args[1].toLowerCase());
				save();
				sender.sendMessage(_("removedFromList", args[1]));
			} catch (IOException e) {
				sender.sendMessage(_("errorSaveList"));
			}

		} else if (args[0].equalsIgnoreCase("setmsg")) {
			String msg = "";
			for (int i = 1; i < args.length; i++)
				msg += args[i] + " ";

			instance.getConfig().set("maintenancemsg", ChatColor.translateAlternateColorCodes('&', msg));
			instance.saveConfig();
			sender.sendMessage(_("msgSet"));

		} else
			sender.sendMessage(prefix + usage);
	}

	private void load() throws IOException {
		File file = new File(TimoliaCore.dataFolder + File.separator + "access.txt");
		if (!file.exists()) {
			file.createNewFile();
			return;
		}

		BufferedReader input = new BufferedReader(new FileReader(file));
		String zeile;
		while ((zeile = input.readLine()) != null)
			players.add(zeile.toLowerCase());

		input.close();
	}

	private void save() throws IOException {
		File file = new File(TimoliaCore.dataFolder + File.separator + "access.txt");
		if (!file.exists())
			file.createNewFile();

		BufferedWriter output = new BufferedWriter(new FileWriter(file));
		for (String pName : players) {
			output.append(pName);
			output.newLine();
		}
		output.close();
	}

	public static boolean isAllowed(Player p) {
		return players.contains(p.getName().toLowerCase());
	}

}