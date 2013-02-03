package me.pizzafreak08.TimoliaCore.commands;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import me.pizzafreak08.TimoliaCore.TimoliaCore;

import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class setloc extends TCommand {

	public setloc() {
		setPermission("timolia.setloc");
		setIngame();
		setMinArgs(1);
		setMaxArgs(1);
		setUsage("/setloc <name>");
	}

	public void perform(CommandSender sender, String[] args) {
		Player p = (Player) sender;

		Location loc = p.getLocation();
		String name = args[0];
		String world = loc.getWorld().getName();
		double x = loc.getX();
		double y = loc.getY();
		double z = loc.getZ();
		float yaw = loc.getYaw();
		float pitch = loc.getPitch();

		String save = name + "\n" + world + "\n" + x + "\n" + y + "\n" + z + "\n" + yaw + "\n" + pitch;
		File file = new File(TimoliaCore.dataFolder + File.separator + "locations" + File.separator + name + ".location");

		try {
			BufferedWriter output = new BufferedWriter(new FileWriter(file));
			output.write(save);
			output.close();
			p.sendMessage(_("locSet"));
		} catch (IOException e) {
			p.sendMessage(_("errorsaveloc"));
		}
	}
}