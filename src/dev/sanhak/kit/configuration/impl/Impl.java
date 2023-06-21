package dev.sanhak.kit.configuration.impl;

import org.bukkit.configuration.file.FileConfiguration;

public interface Impl {

	void save();

	FileConfiguration get();

	void reload();

}
