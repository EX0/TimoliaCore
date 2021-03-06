/*
 *  Copyright:
 *  2013 Darius Mewes
 */

package de.timolia.core.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.bukkit.event.player.PlayerQuitEvent;

import de.timolia.core.TimoliaCore;
import de.timolia.core.UpdateChecker;
import de.timolia.core.cmds.access;
import de.timolia.core.cmds.deaths;
import de.timolia.core.cmds.listname;

public class PlayerListener implements Listener {

    public static String joinMsg;
    public static String quitMsg;
    private TimoliaCore instance;

    public PlayerListener(TimoliaCore instance) {
        this.instance = instance;
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onPlayerJoin(PlayerJoinEvent event) {
        // custom quit-msg
        if (!quitMsg.equalsIgnoreCase("")) {
            String tjoinMsg = joinMsg.replaceAll("@p", event.getPlayer().getName());
            tjoinMsg = tjoinMsg.replaceAll("@a", String.valueOf(Bukkit.getOnlinePlayers().length));
            event.setJoinMessage(tjoinMsg);
        }

        // update-checking
        if (TimoliaCore.updateAvailable && (event.getPlayer().isOp() || event.getPlayer().hasPermission("tcore.update"))) {
            event.getPlayer().sendMessage(TimoliaCore.PREFIX + "A new version is available!");
            event.getPlayer().sendMessage(TimoliaCore.PREFIX + "Get it at http://dev.bukkit.org/server-mods/" + UpdateChecker.name);
        }

        // colored listname
        if (event.getPlayer().hasPermission("tcore.listname.join")) {
            String name = event.getPlayer().getName();
            if (event.getPlayer().hasPermission("tcore.listname.black"))
                name = "§0" + name;
            else if (event.getPlayer().hasPermission("tcore.listname.darkblue"))
                name = "§1" + name;
            else if (event.getPlayer().hasPermission("tcore.listname.darkgreen"))
                name = "§2" + name;
            else if (event.getPlayer().hasPermission("tcore.listname.darkcyan"))
                name = "§3" + name;
            else if (event.getPlayer().hasPermission("tcore.listname.darkred"))
                name = "§4" + name;
            else if (event.getPlayer().hasPermission("tcore.listname.purple"))
                name = "§5" + name;
            else if (event.getPlayer().hasPermission("tcore.listname.orange"))
                name = "§6" + name;
            else if (event.getPlayer().hasPermission("tcore.listname.gray"))
                name = "§7" + name;
            else if (event.getPlayer().hasPermission("tcore.listname.darkgray"))
                name = "§8" + name;
            else if (event.getPlayer().hasPermission("tcore.listname.blue"))
                name = "§9" + name;
            else if (event.getPlayer().hasPermission("tcore.listname.green"))
                name = "§a" + name;
            else if (event.getPlayer().hasPermission("tcore.listname.cyan"))
                name = "§b" + name;
            else if (event.getPlayer().hasPermission("tcore.listname.red"))
                name = "§c" + name;
            else if (event.getPlayer().hasPermission("tcore.listname.pink"))
                name = "§d" + name;
            else if (event.getPlayer().hasPermission("tcore.listname.yellow"))
                name = "§e" + name;
            else
                return;

            if (name.length() > 14)
                name = name.substring(0, 13);

            name += "§r";
            for (int i = 0; i < name.length(); i++)
                if (!listname.allowed.contains(String.valueOf(name.toLowerCase().charAt(i))))
                    return;

            try {
                event.getPlayer().setPlayerListName(name);
            } catch (Exception e) {

            }
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        if (!quitMsg.equalsIgnoreCase("")) {
            String tquitMsg = quitMsg.replaceAll("@p", event.getPlayer().getName());
            tquitMsg = tquitMsg.replaceAll("@a", String.valueOf(Bukkit.getOnlinePlayers().length - 1));
            event.setQuitMessage(tquitMsg);
        }
    }

    // Wartung
    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onPlayerLogin(PlayerLoginEvent event) {
        if (instance.getConfig().getBoolean("maintenance") && !(access.isAllowed(event.getPlayer()))) {
            event.setKickMessage(ChatColor.translateAlternateColorCodes('&', instance.getConfig().getString("servername")) + ChatColor.WHITE + " " + instance.getConfig().getString("maintenancemsg"));
            event.setResult(Result.KICK_OTHER);
        }
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onPlayerDeath(PlayerDeathEvent event) {
        if (deaths.hidingEnabled) {
            String vanillaMsg = event.getDeathMessage();
            event.setDeathMessage("");
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (deaths.shuttedOff.contains(p.getName()))
                    continue;

                if (instance.getConfig().getBoolean("darkerDeathMessages"))
                    p.sendMessage(ChatColor.DARK_GRAY + vanillaMsg);
                else
                    p.sendMessage(vanillaMsg);
            }
        }
    }

}