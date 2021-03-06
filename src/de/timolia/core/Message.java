/*
 *  Copyright:
 *  2013 Darius Mewes
 */

package de.timolia.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Message {

    private static final String NAME = "TCore";
    private static Map<String, String> values = new HashMap<String, String>();

    public static void console(String message) {
        Logger.getLogger("Minecraft").info("[" + NAME + "] " + message);
    }

    public static void online(String message) {
        for (Player p : Bukkit.getOnlinePlayers())
            p.sendMessage(message);
    }

    public static void certain(String message, String permission) {
        for (Player p : Bukkit.getOnlinePlayers())
            if (p.hasPermission(permission))
                p.sendMessage(message);
    }

    public static String _(String... key) {
        if (key.length == 0)
            return null;

        String msg = values.get(key[0]);
        for (int i = 1; i < key.length; i++)
            msg = msg.replaceAll("%a" + i, key[i]);

        return msg;
    }

    public static void loadLanguageFile(String language, boolean debug) {
        Message.console("Loading " + language + "-Messages...");
        try {
            InputStream stream = null;
            InputStreamReader reader = null;
            BufferedReader input;
            if (debug) {
                File file = new File(System.getProperty("user.home") + File.separator + "Desktop/code/ws/TimoliaCore/src/Messages_" + language + ".txt");
                if (!file.exists()) {
                    loadLanguageFile(language, false);
                    return;
                }

                input = new BufferedReader(new FileReader(file));
            } else {
                stream = Message.class.getResourceAsStream(File.separator + "Messages_" + language + ".txt");
                reader = new InputStreamReader(stream, "UTF-8");
                input = new BufferedReader(reader);
            }

            values.clear();
            String line;
            while ((line = input.readLine()) != null) {
                if (line.equalsIgnoreCase(""))
                    continue;

                line = line.replaceFirst(": ", "%%%");
                String[] data = line.split("%%%");
                values.put(data[0], data[1]);
            }

            if (!debug) {
                stream.close();
                reader.close();
            }

            input.close();
            Message.console("Succeeded!");
        } catch (Exception e) {
            Message.console("Failed: " + e.getMessage());
        }
    }

}