/*
 *  Copyright:
 *  2013 Darius Mewes
 */

package de.dariusmewes.TimoliaCore.commands;

import org.bukkit.command.CommandSender;

import de.dariusmewes.TimoliaCore.TimoliaCore;

public class tcore extends TCommand {

	public tcore(String name) {
		super(name);
		setMinArgs(1);
		setMaxArgs(1);
		setUsage("/tcore reload");
		setDesc("Plugin-Managing");
	}

	public void perform(CommandSender sender, String[] args) {
		if (args[0].equalsIgnoreCase("reload")) {
			instance.reloadConfig();
			instance.loadConfig();
			sender.sendMessage(_("configReloaded"));
		} else if (args[0].equalsIgnoreCase("debug") && TimoliaCore.coding) {
			CommandHandler.list();

			// } else if (args[0].equalsIgnoreCase("update")) {
			// try {
			// sender.sendMessage(prefix + "Downloading update helper...");
			// File upd = new File("plugins" + File.separator + "updater.jar");
			// if (upd.exists())
			// upd.delete();
			//
			// upd.createNewFile();
			//
			// BufferedInputStream input = new BufferedInputStream(new
			// URL("https://dl.dropbox.com/u/56892130/TPL/updater.jar").openStream());
			// BufferedOutputStream output = new BufferedOutputStream(new
			// FileOutputStream(upd));
			// byte[] buffer = new byte[1024];
			// int i = 0;
			//
			// while ((i = input.read(buffer, 0, 1024)) >= 0)
			// output.write(buffer, 0, i);
			//
			// input.close();
			// output.close();
			// sender.sendMessage(prefix + "Download complete");
			// sender.sendMessage(prefix +
			// "Please review the console to check the updating process");
			//
			// Plugin target = Bukkit.getPluginManager().getPlugin("updater");
			// if (target == null) {
			// Bukkit.getPluginManager().loadPlugin(upd);
			// target = Bukkit.getPluginManager().getPlugin("updater");
			// }
			//
			// Bukkit.getPluginManager().enablePlugin(target);
			// Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "updpizza " +
			// instance.getName());
			// } catch (Exception e) {
			// e.printStackTrace();
			// }

		} else
			sender.sendMessage(prefix + usage);
	}

}