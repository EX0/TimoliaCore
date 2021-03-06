/*
 *  Copyright:
 *  2013 Darius Mewes
 */

package de.timolia.core.cmds;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class chat extends TCommand {

    protected void prepare() {
        permission();
        minArgs(2);
    }

    public void perform(CommandSender sender, String[] args) {
        Player target = Bukkit.getPlayer(args[0]);

        if (target == null) {
            sender.sendMessage(_("notonline"));
            return;
        }

        String output = "";

        for (int i = 1; i < args.length; i++)
            output += args[i] + " ";

        target.chat(output);
    }

}