/*
 *  Copyright:
 *  2013 Darius Mewes
 */

package de.timolia.core.cmds;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import de.timolia.core.Message;

public class raw extends TCommand {

    protected void prepare() {
        permission();
        minArgs(1);
    }

    public void perform(CommandSender sender, String[] args) {
        int start;

        if (args[0].equalsIgnoreCase("-s")) {
            if (args.length < 2) {
                sender.sendMessage(usage());
                return;
            }

            start = 1;

        } else {
            Message.certain(_("fakeMsgNotify"), "tcore.raw.notify");
            start = 0;
        }

        String msg = "";
        for (int i = start; i < args.length; i++)
            msg += args[i] + " ";

        msg = ChatColor.translateAlternateColorCodes('&', msg);

        Message.online(msg);
        Message.console("[FAKEMESSAGE] " + msg);
    }

}