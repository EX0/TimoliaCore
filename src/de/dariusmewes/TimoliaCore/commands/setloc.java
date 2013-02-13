/*
 *  Copyright:
 *  2013 Darius Mewes
 */

package de.dariusmewes.TimoliaCore.commands;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.dariusmewes.TimoliaCore.TimoliaCore;

public class setloc extends TCommand {

	public setloc(String name) {
		super(name);
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