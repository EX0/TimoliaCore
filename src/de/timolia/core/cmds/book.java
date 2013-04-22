/*
 *  Copyright:
 *  2013 Darius Mewes
 */

package de.timolia.core.cmds;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.BookMeta;

public class book extends TCommand {

    protected void prepare() {
        permission();
        ingame();
        minArgs(2);
    }

    public void perform(CommandSender sender, String[] args) {
        Player p = (Player) sender;

        if (args[0].equalsIgnoreCase("title")) {
            if (p.getItemInHand().getType() != Material.WRITTEN_BOOK) {
                sender.sendMessage(_("itemnotbook"));
                return;
            }

            String msg = "";
            for (int i = 1; i < args.length; i++)
                msg += args[i] + " ";

            BookMeta meta = (BookMeta) p.getItemInHand().getItemMeta();
            meta.setTitle(ChatColor.translateAlternateColorCodes('&', msg));
            p.getItemInHand().setItemMeta(meta);
            sender.sendMessage(_("bookTitleChanged"));
        }

        else if (args[0].equalsIgnoreCase("author")) {
            if (p.getItemInHand().getType() != Material.WRITTEN_BOOK) {
                sender.sendMessage(_("itemnotbook"));
                return;
            }

            String msg = "";
            for (int i = 1; i < args.length; i++)
                msg += args[i] + " ";

            BookMeta meta = (BookMeta) p.getItemInHand().getItemMeta();
            meta.setAuthor(msg);
            p.getItemInHand().setItemMeta(meta);
            sender.sendMessage(_("bookAuthorChanged"));
        }
    }

}