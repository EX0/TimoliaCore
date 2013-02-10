package de.dariusmewes.TimoliaCore;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import de.dariusmewes.TimoliaCore.commands.CommandHandler;
import de.dariusmewes.TimoliaCore.commands.deaths;
import de.dariusmewes.TimoliaCore.events.PlayerListener;
import de.dariusmewes.TimoliaCore.events.ServerListener;

public class TimoliaCore extends JavaPlugin {

	public static final String PREFIX = ChatColor.DARK_RED + "[Timolia] " + ChatColor.WHITE;
	public static boolean updateAvailable = false;
	public static boolean check = false;
	public static File dataFolder;
	public static boolean debug = true;

	public void onEnable() {
		CommandHandler.init(this);
		initEventHandlers();
		initConfig();
		dataFolder = getDataFolder();
		if (check)
			UpdateChecker.start(this);

		// new File(dataFolder + File.separator + "books").mkdir();
		new File(dataFolder + File.separator + "locations").mkdir();
	}

	public void onDisable() {

	}

	private void initEventHandlers() {
		Bukkit.getPluginManager().registerEvents(new PlayerListener(this), this);
		Bukkit.getPluginManager().registerEvents(new ServerListener(this), this);
	}

	private void initConfig() {
		FileConfiguration conf = getConfig();
		conf.addDefault("joinmsg", "");
		conf.addDefault("quitmsg", "");
		conf.addDefault("motd", "");
		conf.addDefault("deathHiding", false);
		conf.addDefault("maintenance", false);
		conf.addDefault("maintenancemsg", "This server is currently under maintenance");
		conf.addDefault("servername", "&4[Server]");
		conf.addDefault("language", "en");
		conf.addDefault("checkForUpdates", true);
		conf.options().copyDefaults(true);
		saveConfig();

		loadConfig();
	}

	public void loadConfig() {
		PlayerListener.joinMsg = ChatColor.translateAlternateColorCodes('&', getConfig().getString("joinmsg"));
		PlayerListener.quitMsg = ChatColor.translateAlternateColorCodes('&', getConfig().getString("quitmsg"));
		check = getConfig().getBoolean("checkForUpdates");

		deaths.hidingEnabled = getConfig().getBoolean("deathHiding");

		String language = getConfig().getString("language");
		if (!(language.equalsIgnoreCase("de")))
			language = "en";

		Message.loadLanguageFile(language, debug);
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		CommandHandler.handleCommand(sender, cmd, args);
		return true;
	}

	public static String getCorrectName(String name) {
		String[] replacer = { "a", "b", "c", "d", "e", "f", "k", "l", "m", "n", "o", "r", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
		for (int i = 0; i < replacer.length; i++)
			name = name.replaceAll("&" + replacer[i], "");

		return name;
	}
}