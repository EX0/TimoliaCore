/*
 *  Copyright:
 *  2013 Darius Mewes
 */

package de.timolia.core.cmds;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class pingmsg extends TCommand {

    protected void prepare() {
        permission();
    }

    public void perform(CommandSender sender, String[] args) {
        if (args.length == 0) {
            instance.getConfig().set("motd", "");
            instance.saveConfig();
            sender.sendMessage(_("motdReset"));
            return;
        }

        String msg = "";
        for (int i = 0; i < args.length; i++)
            msg += args[i] + " ";

        instance.getConfig().set("motd", ChatColor.translateAlternateColorCodes('&', msg));
        instance.saveConfig();

        sender.sendMessage(_("motdSet"));
    }

}