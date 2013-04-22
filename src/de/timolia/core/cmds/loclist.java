/*
 *  Copyright:
 *  2013 Darius Mewes
 */

package de.timolia.core.cmds;

import java.io.File;

import org.bukkit.command.CommandSender;

import de.timolia.core.TimoliaCore;

public class loclist extends TCommand {

    protected void prepare() {
        permission();
        maxArgs(0);
    }

    public void perform(CommandSender sender, String[] args) {
        File dir = new File(TimoliaCore.dataFolder + File.separator + "locations" + File.separator);
        File[] list = dir.listFiles();

        if (list.length == 0) {
            sender.sendMessage(_("noLocsFound"));
            return;
        }

        String output = "";

        int i = 0;

        while (i < (list.length - 1)) {
            String name = list[i].getName();
            name = name.substring(0, name.length() - 9);
            output += name + ", ";
            i++;
        }

        String name = list[i].getName();
        name = name.substring(0, name.length() - 9);
        output += name;

        sender.sendMessage(_("locs", output));
    }

}