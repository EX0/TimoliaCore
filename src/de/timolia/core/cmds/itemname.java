/*
 *  Copyright:
 *  2013 Darius Mewes
 */

package de.timolia.core.cmds;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.ItemMeta;

public class itemname extends TCommand {

    protected void prepare() {
        permission();
        ingame();
        minArgs(1);
    }

    public void perform(CommandSender sender, String[] args) {
        Player p = (Player) sender;

        if (p.getItemInHand().getType() == Material.AIR) {
            p.sendMessage(_("emptyHand"));
            return;
        }

        String msg = "";
        for (int i = 0; i < args.length; i++)
            msg += args[i] + " ";

        ItemMeta meta = p.getItemInHand().getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', msg));
        p.getItemInHand().setItemMeta(meta);
        p.sendMessage(_("renamedItem"));
    }

}