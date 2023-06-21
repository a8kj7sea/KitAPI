package dev.sanhak.kit.command;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import dev.sanhak.kit.main.Main;
import dev.sanhak.kit.utils.KitUtils;

public class Commands implements CommandExecutor, TabCompleter {

	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
		if (!(commandSender instanceof Player)) {
			commandSender.sendMessage(Main.getInstance().prefix + "§cThis command only for players usage !");
			return false;
		}

		Player player = (Player) commandSender;

		if ((player.isOp())) {
			if (args.length == 1) {
				switch (args[0]) {
				case "load":
					if (Main.getKitFileConfiguration().contains("kit.contents")) {
						KitUtils.loadKit(player);
						player.updateInventory();
						player.sendMessage(Main.getInstance().prefix + "§aYou have been loaded the kit successfully !");
						player.playSound(player.getLocation(), Sound.LEVEL_UP, 0.6f, 6.0f);
					} else {
						player.updateInventory();
						player.sendMessage(
								Main.getInstance().prefix + "§cMaybe you forget to setup kit by type /2wkit save !");
						player.playSound(player.getLocation(), Sound.ITEM_BREAK, 0.6f, 6.0f);
					}
					break;
				case "save":
					player.updateInventory();
					KitUtils.saveKit(player.getInventory());
					player.updateInventory();

					new BukkitRunnable() {

						@Override
						public void run() {
							player.sendMessage(Main.getInstance().prefix
									+ "§aYou have been saved the kit contents successfully !");
							player.playSound(player.getLocation(), Sound.LEVEL_UP, 0.6f, 6.0f);

						}
					}.runTaskLater(Main.getInstance(), 20L);
					break;
				case "reload":
					Main.getKitConfiguration().reload();
					Main.getSettingsConfiguration().reload();
					player.sendMessage(
							Main.getInstance().prefix + "§aYou have been reloaded configuration files successfully ! ");
					break;
				default:
					player.playSound(player.getLocation(), Sound.VILLAGER_NO, 0.6f, 6.0f);
					player.sendMessage(Main.getInstance().prefix
							+ "§cThat incorrect use please try to type §c§l/2wkit <reload/save/load>");
					break;
				}
			} else {
				player.playSound(player.getLocation(), Sound.VILLAGER_NO, 0.6f, 6.0f);
				player.sendMessage(Main.getInstance().prefix
						+ "§cThat incorrect use please try to type §c§l/2wkit <reload/save/load>>");
			}
		} else {
			player.playSound(player.getLocation(), Sound.DONKEY_ANGRY, 0.6f, 6.0f);
			player.sendMessage(Main.getInstance().prefix + "§cYou don't have nesseccary permissions to use it !");
		}

		return false;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command arg1, String arg2, String[] args) {

		Player player = (Player) sender;
		List<String> suggestions = Arrays.asList("reload", "save", "load");

		if (!(sender instanceof Player)) {
			return Collections.emptyList();
		}

		if (player.isOp()) {
			if (args.length == 1) {
				return suggestions;
			}
		}

		return Collections.emptyList();
	}

}
