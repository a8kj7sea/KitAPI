package dev.sanhak.kit.listener;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import dev.sanhak.kit.main.Main;
import dev.sanhak.kit.utils.KitUtils;

public class PlayerCommandPreprocessListener implements Listener {

	@EventHandler
	public void onPlayerCommandPreprocessEvent(PlayerCommandPreprocessEvent event) {
		String command = Main.getSettingsFileConfiguration().getString("KitCommand.Command");

		boolean condition = Main.getSettingsFileConfiguration().getBoolean("KitCommand.Permission");

		Player player = event.getPlayer();

		if (condition) {
			if (player.hasPermission(Main.getSettingsFileConfiguration().getString("KitCommand.KitPermission"))) {
				if (event.getMessage().startsWith("/" + command)) {
					event.setCancelled(true);
					if (Main.getKitFileConfiguration().contains("kit.contents")) {
						player.updateInventory();
						KitUtils.loadKit(player);
						player.updateInventory();
						player.sendMessage(Main.getInstance().prefix + "§aYou have been loaded the kit successfully !");
						player.playSound(player.getLocation(), Sound.LEVEL_UP, 0.6f, 6.0f);
					} else {
						if (player.isOp()) {
							player.sendMessage("§cKit is empty , you must be setup kit by type /2wkit save !");
							player.playSound(player.getLocation(), Sound.BAT_DEATH, 0.6f, 6.0f);
						}
					}
				}
			}
		} else {
			if (event.getMessage().startsWith("/" + command)) {
				event.setCancelled(true);
				if (Main.getKitFileConfiguration().contains("kit.contents")) {
					player.updateInventory();
					KitUtils.loadKit(player);
					player.updateInventory();
					player.sendMessage(Main.getInstance().prefix + "§aYou have been loaded the kit successfully !");
					player.playSound(player.getLocation(), Sound.LEVEL_UP, 0.6f, 6.0f);
				} else {
					if (player.isOp()) {
						player.sendMessage("§cKit is empty , you must be setup kit by type /2wkit save !");
						player.playSound(player.getLocation(), Sound.BAT_DEATH, 0.6f, 6.0f);
					}
				}
			}
		}
	}

}
