/*
 *  Copyright:
 *  2013 Darius Mewes
 */

package de.timolia.core;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import de.timolia.core.cmds.TCommand;
import de.timolia.core.cmds.access;
import de.timolia.core.cmds.armor;
import de.timolia.core.cmds.asave;
import de.timolia.core.cmds.book;
import de.timolia.core.cmds.ca;
import de.timolia.core.cmds.cblock;
import de.timolia.core.cmds.cc;
import de.timolia.core.cmds.chat;
import de.timolia.core.cmds.clock;
import de.timolia.core.cmds.colors;
import de.timolia.core.cmds.console;
import de.timolia.core.cmds.ctp;
import de.timolia.core.cmds.damage;
import de.timolia.core.cmds.deaths;
import de.timolia.core.cmds.drop;
import de.timolia.core.cmds.dump;
import de.timolia.core.cmds.exe;
import de.timolia.core.cmds.itemlore;
import de.timolia.core.cmds.itemname;
import de.timolia.core.cmds.listname;
import de.timolia.core.cmds.loc;
import de.timolia.core.cmds.loclist;
import de.timolia.core.cmds.mode;
import de.timolia.core.cmds.pingmsg;
import de.timolia.core.cmds.raw;
import de.timolia.core.cmds.removeloc;
import de.timolia.core.cmds.setloc;
import de.timolia.core.cmds.setname;
import de.timolia.core.cmds.skick;
import de.timolia.core.cmds.st;
import de.timolia.core.cmds.tcore;
import de.timolia.core.cmds.visible;
import de.timolia.core.events.PlayerListener;
import de.timolia.core.events.ServerListener;

public class TimoliaCore extends JavaPlugin {

    public static final String PREFIX = ChatColor.DARK_RED + "[TCore] " + ChatColor.RESET;

    public static boolean updateAvailable = false;
    public static boolean coding = false;
    public static File dataFolder;

    private Metrics m;

    public void onEnable() {
        initCommands();
        initEventHandlers();
        initConfig();
        dataFolder = getDataFolder();
        new File(dataFolder + File.separator + "locations").mkdir();

        if (coding)
            Message.console("PLUGIN RUNNING IN CODING-MODE!!! BE CAREFUL!!!");

        if (getConfig().getBoolean("checkForUpdates"))
            UpdateChecker.start(this);
        else
            Message.console("Update-Checking disabled! Change the config.yml to activate it.");

        try {
            m = new Metrics(this);
            m.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            access.load();
        } catch (IOException e) {
            Message.console("Could not load whitelist: " + e.getMessage());
        }
    }

    public void onDisable() {
        if (asave.stopAutoSave())
            Message.console("Autosave stopped!");
    }

    private void initCommands() {
        TCommand.setPluginInstance(this);
        TCommand.add("access", new access());
        TCommand.add("armor", new armor());
        TCommand.add("asave", new asave());
        TCommand.add("book", new book());
        TCommand.add("ca", new ca());
        TCommand.add("cblock", new cblock());
        TCommand.add("cc", new cc());
        TCommand.add("chat", new chat());
        TCommand.add("clock", new clock());
        TCommand.add("colors", new colors());
        TCommand.add("console", new console());
        TCommand.add("ctp", new ctp());
        TCommand.add("damage", new damage());
        TCommand.add("deaths", new deaths());
        TCommand.add("drop", new drop());
        TCommand.add("dump", new dump());
        TCommand.add("exe", new exe());
        TCommand.add("itemlore", new itemlore());
        TCommand.add("itemname", new itemname());
        TCommand.add("listname", new listname());
        TCommand.add("loc", new loc());
        TCommand.add("loclist", new loclist());
        TCommand.add("mode", new mode());
        TCommand.add("pingmsg", new pingmsg());
        TCommand.add("raw", new raw());
        TCommand.add("removeloc", new removeloc());
        TCommand.add("setloc", new setloc());
        TCommand.add("setname", new setname());
        TCommand.add("skick", new skick());
        TCommand.add("st", new st());
        TCommand.add("tcore", new tcore());
        TCommand.add("visible", new visible());
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
        conf.addDefault("darkerDeathMessages", false);
        conf.addDefault("defaultSkick", "You have been kicked");
        conf.addDefault("maintenance", false);
        conf.addDefault("maintenancemsg", "This server is currently under maintenance");
        conf.addDefault("servername", "&4[Server]");
        conf.addDefault("autosave", false);
        conf.addDefault("autosavedelay", 5);
        conf.addDefault("autosavebcast", true);
        conf.addDefault("language", "en");
        conf.addDefault("checkForUpdates", true);
        conf.options().copyDefaults(true);
        saveConfig();

        loadConfig();
    }

    public void loadConfig() {
        PlayerListener.joinMsg = ChatColor.translateAlternateColorCodes('&', getConfig().getString("joinmsg"));
        PlayerListener.quitMsg = ChatColor.translateAlternateColorCodes('&', getConfig().getString("quitmsg"));

        deaths.hidingEnabled = getConfig().getBoolean("deathHiding");

        if (asave.stopAutoSave())
            Message.console("Autosave stopped!");

        if (getConfig().getBoolean("autosave")) {
            asave.startAutoSave();
            Message.console("Autosave started!");
        }

        loadMSGs();
    }

    private void loadMSGs() {
        String language = getConfig().getString("language");
        if (!(language.equalsIgnoreCase("de")))
            language = "en";

        Message.loadLanguageFile(language, coding);
    }

    public void reload() {
        this.reloadConfig();
        if (!getConfig().getBoolean("checkForUpdates") && UpdateChecker.task != null) {
            UpdateChecker.task.cancel();
            UpdateChecker.task = null;
            Message.console("UpdateChecker stopped");
        } else if (getConfig().getBoolean("checkForUpdates") && UpdateChecker.task == null)
            UpdateChecker.start(this);
    }

}