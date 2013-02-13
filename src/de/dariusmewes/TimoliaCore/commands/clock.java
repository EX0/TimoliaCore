/*
 *  Copyright:
 *  2013 Darius Mewes
 */

package de.dariusmewes.TimoliaCore.commands;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.command.CommandSender;

public class clock extends TCommand {

	public clock(String name) {
		super(name);
		setMaxArgs(0);
		setUsage("/clock");
	}

	public void perform(CommandSender sender, String[] args) {
		SimpleDateFormat uhr = new SimpleDateFormat("dd. MMM. yyyy, HH:mm");
		long current = System.currentTimeMillis();
		Date datum = new Date(current);
		String date = uhr.format(datum);
		sender.sendMessage(_("dateTime", date));
	}
	
}