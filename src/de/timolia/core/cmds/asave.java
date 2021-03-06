/*
 *  Copyright:
 *  2013 Darius Mewes
 */

package de.timolia.core.cmds;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.CommandSender;

import de.timolia.core.Message;

public class asave extends TCommand {

    public static boolean running = false;
    public static int taskID;

    protected void prepare() {
        permission();
        minArgs(1);
        maxArgs(2);
    }

    public void perform(CommandSender sender, String[] args) {
        if (args[0].equalsIgnoreCase("start")) {
            if (startAutoSave()) {
                sender.sendMessage(_("saveStart"));
                Message.console("Autosave started!");
                instance.getConfig().set("autosave", true);
                instance.saveConfig();
            } else
                sender.sendMessage(prefix + "Autosave " + __("running"));

        } else if (args[0].equalsIgnoreCase("stop")) {
            if (stopAutoSave()) {
                sender.sendMessage(_("saveStop"));
                Message.console("Autosave stopped!");
                instance.getConfig().set("autosave", false);
                instance.saveConfig();
            } else
                sender.sendMessage(prefix + "Autosave " + __("notRunning"));

        } else if (args[0].equalsIgnoreCase("delay")) {
            if (args.length == 1) {
                sender.sendMessage(_("enterTime"));
                return;
            }

            long delay;
            try {
                delay = Long.valueOf(args[1]);
            } catch (NumberFormatException e) {
                sender.sendMessage(_("noInt2", args[1]));
                return;
            }

            instance.getConfig().set("autosavedelay", delay);
            instance.saveConfig();
            sender.sendMessage(_("saveTimeSet"));
            if (running) {
                stopAutoSave();
                startAutoSave();
                sender.sendMessage(_("asaveRestarted"));
                Message.console("Restarted Autosave-Timer with a delay of " + delay + " minutes");
            }

        } else if (args[0].equalsIgnoreCase("status")) {
            sender.sendMessage(prefix + "Autosave " + (running ? __("running") : __("notRunning")));

        } else
            sender.sendMessage(usage());
    }

    public static boolean startAutoSave() {
        if (running)
            return false;

        long delay;
        try {
            delay = Long.valueOf(instance.getConfig().getString("autosavedelay"));
        } catch (NumberFormatException e) {
            delay = 5;
            Message.console("Could not parse autosave-delay from config. Using 5 Minutes...");
        }

        if (delay < 1)
            delay = 1;

        delay = delay * 20 * 60;

        running = true;
        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(instance, new Runnable() {
            public void run() {
                Bukkit.savePlayers();
                for (World w : Bukkit.getWorlds())
                    w.save();

                Message.console(__("autosavebcast"));
                if (instance.getConfig().getBoolean("autosavebcast")) {
                    Message.online(ChatColor.GOLD + __("autosavebcast"));
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