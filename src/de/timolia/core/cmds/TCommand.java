/*
 *  Copyright:
 *  2013 Darius Mewes
 */

package de.timolia.core.cmds;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;

import de.timolia.core.Message;
import de.timolia.core.TimoliaCore;

public abstract class TCommand implements CommandExecutor {

	protected static final String prefix = TimoliaCore.PREFIX;
	protected static final String PERMISSION_PREFIX = "tcore.";
	protected static TimoliaCore instance;

	private String name;
	private String permission = "";
	private boolean ingame = false;
	private int minArgs = 0;
	private int maxArgs = -1;
	protected String usage;

	public static final void add(String commandName, TCommand tCmd) {
		PluginCommand cmd = instance.getCommand(commandName);
		tCmd.name = commandName;
		tCmd.usage = cmd.getUsage();
		tCmd.prepare();
		cmd.setExecutor(tCmd);
	}

	public static final void setPluginInstance(TimoliaCore instance) {
		TCommand.instance = instance;
	}

	protected static final String _(String... key) {
		return prefix + Message._(key);
	}

	protected static final String __(String... key) {
		return Message._(key);
	}

	protected abstract void prepare();

	public abstract void perform(final CommandSender sender, String[] args);

	public final boolean onCommand(final CommandSender sender, final Command cmd, final String label, String[] args) {
		if (!permission.equalsIgnoreCase("") && !sender.hasPermission(this.permission)) {
			sender.sendMessage(_("noperm"));
			return true;
		}

		if (ingame && !(sender instanceof Player)) {
			sender.sendMessage(_("ingame"));
			return false;
		}

		if (args.length >= minArgs && (args.length <= maxArgs || maxArgs == -1))
			perform(sender, args);
		else
			sender.sendMessage(_("usage") + this.usage);

		return true;
	}

	protected final void permission() {
		this.permission(this.name);
	}

	protected final void permission(String permission) {
		this.permission = PERMISSION_PREFIX + permission;
	}

	protected final void ingame() {
		this.ingame = true;
	}

	protected final void minArgs(int minArgs) {
		this.minArgs = minArgs;
	}

	protected final void maxArgs(int maxArgs) {
		this.maxArgs = maxArgs;
	}

	protected final String usage() {
		return prefix + _("usage") + this.usage;
	}

}