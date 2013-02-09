package de.dariusmewes.TimoliaCore.commands;

import org.bukkit.command.CommandSender;

import de.dariusmewes.TimoliaCore.Message;
import de.dariusmewes.TimoliaCore.TimoliaCore;

public abstract class TCommand {

	private String name;
	private String permission = "";
	private boolean onlyIngame = false;
	private int minArgs = 0;
	private int maxArgs = -1;
	protected String usage = "";
	protected String prefix = TimoliaCore.PREFIX;
	protected static TimoliaCore instance;

	public TCommand(String name) {
		this.name = name;
		this.permission = name;
	}

	public abstract void perform(CommandSender sender, String[] args);

	void setPermission(String permission) {
		this.permission = permission;
	}

	void setIngame() {
		this.onlyIngame = true;
	}

	void setMinArgs(int minArgs) {
		this.minArgs = minArgs;
	}

	void setMaxArgs(int string) {
		this.maxArgs = string;
	}

	void setUsage(String text) {
		this.usage = text;
	}

	static void setPluginInstance(TimoliaCore instance) {
		TCommand.instance = instance;
	}

	String getName() {
		return this.name;
	}

	String getPermission() {
		return this.permission;
	}

	boolean onlyIngame() {
		return onlyIngame;
	}

	int getMinArgs() {
		return this.minArgs;
	}

	int getMaxArgs() {
		return this.maxArgs;
	}

	String getUsage() {
		return _("usage") + this.usage;
	}

	String getCleanUsage() {
		return this.usage;
	}

	protected String _(String... key) {
		return Message._(key);
	}

	protected String __(String... key) {
		return Message.__(key);
	}
}