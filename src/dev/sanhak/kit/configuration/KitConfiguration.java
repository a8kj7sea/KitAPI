package dev.sanhak.kit.configuration;
 
import java.io.File;
import java.io.IOException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import dev.sanhak.kit.configuration.impl.Impl;

public class KitConfiguration implements Impl {

	private File file;
	private FileConfiguration configurationFile;

	public KitConfiguration(JavaPlugin javaPlugin, boolean defaultsave, String fileName) {
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