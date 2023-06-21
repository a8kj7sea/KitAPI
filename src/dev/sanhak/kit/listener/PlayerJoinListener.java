package dev.sanhak.kit.listener;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import dev.sanhak.kit.main.Main;
import dev.sanhak.kit.utils.KitUtils;

public class PlayerJoinListener implements Listener {

	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		if (Main.getSettingsFileConfiguration().getBoolean("JoinEvent.Permission")) {
			if (player.hasPermission(Main.getSettingsFileConfiguration().getString("JoinEvent.KitPermission"))) {
				if (Main.getKitFileConfiguration().contains("kit.contents")) {
					player.updateInventory();
					KitUtils.loadKit(player);
					player.updateInventory();
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
