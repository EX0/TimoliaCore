/*
 *  Copyright:
 *  2013 Darius Mewes
 */

package de.timolia.core.cmds;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.timolia.core.TimoliaCore;

public class setloc extends TCommand {

    protected void prepare() {
        permission();
        ingame();
        minArgs(1);
        maxArgs(1);
    }

    public void perform(CommandSender sender, String[] args) {
        Player p = (Player) sender;

        Location loc = p.getLocation();
        String name = args[0];
        String world = loc.getWorld().getName();
        double x = loc.getX();
        double y = loc.getY();
        double z = loc.getZ();
        float yaw = loc.getYaw();
        float pitch = loc.getPitch();

        String save = name + "\n" + world + "\n" + x + "\n" + y + "\n" + z + "\n" + yaw + "\n" + pitch;
        File file = new File(TimoliaCore.dataFolder + File.separator + "locations" + File.separator + name + ".location");

        try {
            BufferedWriter output = new BufferedWriter(new FileWriter(file));
            output.write(save);
            output.close();
            p.sendMessage(_("locSet"));
        } catch (IOException e) {
            p.sendMessage(_("errorsaveloc"));
        }
    }

}