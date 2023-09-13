package dev.sanhak.kit.configuration.impl;

import java.io.File;
import java.io.IOException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import dev.sanhak.kit.configuration.IConfiguration;

public class SettingsConfiguration implements IConfiguration {

	private File file;
	private FileConfiguration configurationFile;


	public SettingsConfiguration(JavaPlugin javaPlugin, boolean defaultsave, String fileName) {
		file = new File(javaPlugin.getDataFolder(), fileName);
		file.getParentFile().mkdirs();
		if (!(file.exists())) {
			if (defaultsave) {
				javaPlugin.saveResource(fileName, defaultsave);
			} else {
				try {
					file.createNewFile();
				} catch (IOException ioexception) {
					ioexception.printStackTrace();
				}
			}

		}
		configurationFile = YamlConfiguration.loadConfiguration(file);

	}

	public void save() {
		try {
			configurationFile.save(file);
		} catch (IOException ioexception) {
			ioexception.printStackTrace();
		}
	}

	public void reload() {
		configurationFile = YamlConfiguration.loadConfiguration(file);
	}

	public FileConfiguration get() {
		return configurationFile;
	}
}