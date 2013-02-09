package de.dariusmewes.TimoliaCore.commands;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;


import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.dariusmewes.TimoliaCore.TimoliaCore;

public class loc extends TCommand {

	public loc() {
		setPermission("timolia.loc");
		setMinArgs(1);
		setMaxArgs(2);
		setUsage("/loc <Location> [Player]");
	}

	public void perform(CommandSender sender, String[] args) {
		Player p;
		if (args.length == 1) {
			if (!(sender instanceof Player)) {
				sender.sendMessage(_("ingame"));
				return;
			}

			p = (Player) sender;
		} else {
			p = Bukkit.getPlayer(args[1]);
			if (p == null) {
				sender.sendMessage(_("notonline"));
				return;
			}
		}

		File file = new File(TimoliaCore.dataFolder + File.separator + "locations" + File.separator + args[0] + ".location");

		if (!file.exists()) {
			sender.sendMessage(_("locNotFound"));
			return;
		}

		String save = "";

		try {
			BufferedReader input = new BufferedReader(new FileReader(file));
			String zeile;
			while ((zeile = input.readLine()) != null)
				save += zeile + " ";

			input.close();
		} catch (Exception e) {
			sender.sendMessage(_("errorLoadLoc"));
		}

		String[] data = save.split(" ");
		String world = data[1];
		double x = Double.valueOf(data[2]);
		double y = Double.valueOf(data[3]);
		double z = Double.valueOf(data[4]);
		float yaw = Float.valueOf(data[5]);
		float pitch = Float.valueOf(data[6]);

		if (Bukkit.getWorld(world) == null) {
			sender.sendMessage(_("unloadedWorld"));
			return;
		}

		Location loc = new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);
		p.teleport(loc);
		sender.sendMessage(_("tpMsg", p.getName(), args[0]));
	}
}