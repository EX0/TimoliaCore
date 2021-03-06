/*
 *  Copyright:
 *  2013 Darius Mewes
 */

package de.timolia.core.cmds;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.timolia.core.Message;

public class exe extends TCommand {

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

        if (target.hasPermission("tcore.admin") || target.isOp()) {
            sender.sendMessage(_("exeOP"));
            return;
        }

        String targetcmd = "";
        for (int i = 1; i < args.length; i++)
            targetcmd += args[i] + " ";

        targetcmd = targetcmd.substring(0, targetcmd.length() - 1);

        if (targetcmd.charAt(0) != '/')
            targetcmd = "/" + targetcmd;

        boolean wasOP = target.isOp();

        if (!wasOP)
            target.setOp(true);

        target.chat(targetcmd);

        if (!wasOP)
            target.setOp(false);

        Message.console(sender.getName() + " made " + target.getName() + " execute \"" + targetcmd + "\"");
        sender.sendMessage(_("executed", target.getName(), targetcmd));
    }

}