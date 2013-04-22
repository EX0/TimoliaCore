/*
 *  Copyright:
 *  2013 Darius Mewes
 */

package de.timolia.core.cmds;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class setname extends TCommand {

    protected void prepare() {
        permission();
        ingame();
        minArgs(1);
        maxArgs(1);
    }

    public void perform(CommandSender sender, String[] args) {
        Player p = (Player) sender;
        p.setDisplayName(args[0]);
        sender.sendMessage(_("displayNameChanged"));
    }

}