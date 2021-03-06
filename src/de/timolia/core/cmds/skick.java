/*
 *  Copyright:
 *  2013 Darius Mewes
 */

package de.timolia.core.cmds;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class skick extends TCommand {

    protected void prepare() {
        permission();
        minArgs(1);
    }

    public void perform(CommandSender sender, String[] args) {
        Player target = Bukkit.getPlayer(args[0]);

        if (target == null) {
            sender.sendMessage(_("notonline"));
            return;
        }

        String msg = instance.getConfig().getString("defaultSkick");

        if (args.length > 1) {
            msg = "";
            for (int i = 1; i < args.length; i++)
                msg += args[i] + " ";
        }

        target.kickPlayer(ChatColor.translateAlternateColorCodes('&', msg));
    }

}