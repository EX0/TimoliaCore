/*
 *  Copyright:
 *  2013 Darius Mewes
 */

package de.timolia.core.cmds;

import java.io.File;

import org.bukkit.command.CommandSender;

import de.timolia.core.TimoliaCore;

public class removeloc extends TCommand {

    protected void prepare() {
        permission();
        minArgs(1);
        maxArgs(1);
    }

    public void perform(CommandSender sender, String[] args) {
        String name = args[0];
        File file = new File(TimoliaCore.dataFolder + File.separator + "locations" + File.separator + name + ".location");

        if (!file.exists()) {
            sender.sendMessage(_("locNotFound"));
            return;
        }

        file.delete();
        sender.sendMessage(_("locRemoved"));
    }

}