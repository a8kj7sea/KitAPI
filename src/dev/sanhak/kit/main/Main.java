package dev.sanhak.kit.main;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import dev.sanhak.kit.api.KitAPI;
import dev.sanhak.kit.command.Commands;
import dev.sanhak.kit.configuration.impl.KitConfiguration;
import dev.sanhak.kit.configuration.impl.SettingsConfiguration;
import dev.sanhak.kit.listener.PlayerCommandPreprocessListener;
import dev.sanhak.kit.listener.PlayerJoinListener;

public class Main extends JavaPlugin {

	private static KitConfiguration kitConfiguration;
	private static FileConfiguration kitFileConfiguration;

	private static SettingsConfiguration settingsConfiguration;
	private static FileConfiguration settingsFileConfiguration;

	private static Main instance;
	public String prefix = "�8\ufe33 �e2wKit �8\ufe33�r ";

	@Override
	public void onEnable() {
		instance = this;

		kitConfiguration = new KitConfiguration(this, true, "kit-items.yml");
		kitFileConfiguration = kitConfiguration.get();

		settingsConfiguration = new SettingsConfiguration(this, true, "settings.yml");
		settingsFileConfiguration = settingsConfiguration.get();

		KitAPI.initialize(this);

		getCommand("2wKit").setTabCompleter(new Commands());
		getCommand("2wKit").setExecutor(new Commands());

		if (Main.getSettingsFileConfiguration().getBoolean("Options.JoinEvent")) {
			getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
			Bukkit.getConsoleSender().sendMessage(prefix + "�a�lSuccessfully enabled join kit event !");
		}

		if (Main.getSettingsFileConfiguration().getBoolean("Options.KitCommand")) {
			getServer().getPluginManager().registerEvents(new PlayerCommandPreprocessListener(), this);
			Bukkit.getConsoleSender().sendMessage(prefix + "�a�lSuccessfully enabled Kit command !");
		}

		Bukkit.getConsoleSender().sendMessage(prefix + "�a�lSuccessfully enabled plugin !");

	}

	public static Main getInstance() {
		return instance;
	}

	public static KitConfiguration getKitConfiguration() {
		return kitConfiguration;
	}

	public static SettingsConfiguration getSettingsConfiguration() {
		return settingsConfiguration;
	}

	public static FileConfiguration getKitFileConfiguration() {
		return kitFileConfiguration;
	}

	public static FileConfiguration getSettingsFileConfiguration() {
		return settingsFileConfiguration;
	}

}
