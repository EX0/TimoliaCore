package me.pizzafreak08.TimoliaCore.commands;

import me.pizzafreak08.TimoliaCore.Message;
import me.pizzafreak08.TimoliaCore.TimoliaCore;

import org.bukkit.command.CommandSender;

public abstract class TCommand {

	private String name;
	private String permission = "";
	private boolean onlyIngame = false;
	private int minArgs = 0;
	private int maxArgs = -1;
	protected String usage;
	protected String prefix = TimoliaCore.PREFIX;
	protected static TimoliaCore instance;

	public abstract void perform(CommandSender sender, String[] args);

	public void setName(String name) {
		this.name = name;
	}

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