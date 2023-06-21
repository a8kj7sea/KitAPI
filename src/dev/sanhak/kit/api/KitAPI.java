package dev.sanhak.kit.api;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;

import dev.sanhak.kit.utils.KitUtils;

public class KitAPI {

	private static KitAPI instance;
	private final Plugin plugin;

	private KitAPI(Plugin plugin) {
		this.plugin = plugin;
	}

	public Plugin getPlugin() {
		return plugin;
	}

	public static void initialize(Plugin plugin) {
		if (instance != null) {
			throw new IllegalStateException("KitAPI already initialized");
		}
		instance = new KitAPI(plugin);
	}

	public static KitAPI getInstance() {
		if (instance == null) {
			throw new IllegalStateException("KitAPI not initialized");
		}
		return instance;
	}

	public void loadKit(Player player) {
		KitUtils.loadKit(player);
	}

	public void saveKit(Inventory inventory) {
		KitUtils.saveKit(inventory);
	}

}
