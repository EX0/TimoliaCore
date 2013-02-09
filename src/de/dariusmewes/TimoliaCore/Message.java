package de.dariusmewes.TimoliaCore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Message {

	private static HashMap<String, String> values = new HashMap<String, String>();

	public static void console(String message) {
		Logger.getLogger("Minecraft").info("[Timolia] " + message);
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
		String msg = TimoliaCore.PREFIX + values.get(key[0]);
		for (int i = 1; i < key.length; i++)
			msg = msg.replaceAll("%a" + i, key[i]);
		return msg;
	}

	public static String __(String... key) {
		if (key.length == 0)
			return null;
		String msg = values.get(key[0]);
		for (int i = 1; i < key.length; i++)
			msg = msg.replaceAll("%a" + i, key[i]);
		return msg;
	}

	public static void loadLanguageFile(InputStream stream) {
		try {
			InputStreamReader streamReader = new InputStreamReader(stream);
			BufferedReader input = new BufferedReader(new InputStreamReader(stream));
			String line;
			values.clear();
			while ((line = input.readLine()) != null) {
				if (line.equalsIgnoreCase(""))
					continue;

				line = line.replaceFirst(": ", "%%%");
				String[] data = line.split("%%%");
				values.put(data[0], data[1]);
			}

			stream.close();
			streamReader.close();
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}