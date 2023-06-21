package dev.sanhak.kit.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import dev.sanhak.kit.main.Main;
import net.md_5.bungee.api.ChatColor;

public class KitUtils {

	public static void saveKit(Inventory inventory) {
		ConfigurationSection inventorySection = Main.getKitFileConfiguration().getConfigurationSection("kit.contents");
		if (inventorySection == null) {
			inventorySection = Main.getKitFileConfiguration().createSection("kit.contents");
		}

		for (int i = 0; i < inventory.getSize(); i++) {
			ItemStack item = inventory.getItem(i);
			if (item == null || item.getType() == Material.AIR) {
				continue;
			}

			ConfigurationSection itemSection = inventorySection.createSection(String.valueOf(i));

			itemSection.set("itemid", item.getType().toString());
			itemSection.set("itemamount", item.getAmount());
			itemSection.set("itemdata", item.getDurability());
			if (item.hasItemMeta()) {
				ItemMeta meta = item.getItemMeta();
				if (meta.hasDisplayName()) {
					itemSection.set("name", meta.getDisplayName());
				}
				if (meta.hasLore()) {
					itemSection.set("lore", meta.getLore());
				}
				if (meta.hasEnchants()) {
					List<String> enchantments = new ArrayList<>();
					for (Map.Entry<Enchantment, Integer> entry : meta.getEnchants().entrySet()) {
						enchantments.add(entry.getKey().getName() + "#" + entry.getValue());
					}
					itemSection.set("enchantments", enchantments);
				}
			}
		}

		Main.getKitConfiguration().save();
	}

	public static void loadKit(Player player) {

		ConfigurationSection inventorySection = Main.getKitFileConfiguration().getConfigurationSection("kit.contents");
		if (inventorySection == null) {
			return;
		}

		for (String slotString : inventorySection.getKeys(false)) {
			ConfigurationSection itemSection = inventorySection.getConfigurationSection(slotString);

			Material material = Material.getMaterial(itemSection.getString("itemid"));
			int amount = itemSection.getInt("itemamount");
			short durability = (short) itemSection.getInt("itemdata");
			ItemStack item = new ItemStack(material, amount, durability);
			ItemMeta meta = item.getItemMeta();
			if (itemSection.contains("itemname")) {
				meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', itemSection.getString("itemname")));
			}
			if (itemSection.contains("lore")) {
				List<String> lore = new ArrayList<>();
				for (String loreLine : itemSection.getStringList("lore")) {
					lore.add(ChatColor.translateAlternateColorCodes('&', loreLine));
				}
				meta.setLore(lore);
			}
			if (itemSection.contains("enchantments")) {
				for (String enchantmentString : itemSection.getStringList("enchantments")) {
					String[] parts = enchantmentString.split("#");
					Enchantment enchantment = Enchantment.getByName(parts[0]);
					int level = Integer.parseInt(parts[1]);
					meta.addEnchant(enchantment, level, true);
				}
			}
			item.setItemMeta(meta);

			if (Main.getSettingsFileConfiguration().getBoolean("Options.AutoArmor")) {

				if (item.getType().name().toUpperCase().contains("_CHESTPLATE")) {
					if (item.isSimilar(new ItemStack(Material.DIAMOND_CHESTPLATE))) {
						ItemStack chestplate = player.getInventory().getChestplate();
						if (chestplate != null) {
							player.getInventory().addItem(item);
						} else {
							player.getInventory().setChestplate(item);
						}
					} else if (item.isSimilar(new ItemStack(Material.IRON_CHESTPLATE))) {
						ItemStack chestplate = player.getInventory().getChestplate();
						if (chestplate != null) {
							player.getInventory().addItem(item);
						} else {
							player.getInventory().setChestplate(item);
						}
					} else if (item.isSimilar(new ItemStack(Material.GOLD_CHESTPLATE))) {
						ItemStack chestplate = player.getInventory().getChestplate();
						if (chestplate != null) {
							player.getInventory().addItem(item);
						} else {
							player.getInventory().setChestplate(item);
						}
					} else if (item.isSimilar(new ItemStack(Material.CHAINMAIL_CHESTPLATE))) {
						ItemStack chestplate = player.getInventory().getChestplate();
						if (chestplate != null) {
							player.getInventory().addItem(item);
						} else {
							player.getInventory().setChestplate(item);
						}
					} else {
						ItemStack chestplate = player.getInventory().getChestplate();
						if (chestplate != null) {
							player.getInventory().addItem(item);
						} else {
							player.getInventory().setChestplate(item);
						}
					}
				} else if (item.getType().name().toUpperCase().contains("_LEGGINGS")) {
					if (item.isSimilar(new ItemStack(Material.DIAMOND_LEGGINGS))) {
						ItemStack leggings = player.getInventory().getLeggings();
						if (leggings != null) {
							player.getInventory().addItem(item);
						} else {
							player.getInventory().setLeggings(item);
						}
					} else if (item.isSimilar(new ItemStack(Material.IRON_LEGGINGS))) {
						ItemStack leggings = player.getInventory().getLeggings();
						if (leggings != null) {
							player.getInventory().addItem(item);
						} else {
							player.getInventory().setLeggings(item);
						}
					} else if (item.isSimilar(new ItemStack(Material.GOLD_LEGGINGS))) {
						ItemStack leggings = player.getInventory().getLeggings();
						if (leggings != null) {
							player.getInventory().addItem(item);
						} else {
							player.getInventory().setLeggings(item);
						}
					} else if (item.isSimilar(new ItemStack(Material.CHAINMAIL_LEGGINGS))) {
						ItemStack leggings = player.getInventory().getLeggings();
						if (leggings != null) {
							player.getInventory().addItem(item);
						} else {
							player.getInventory().setLeggings(item);
						}
					} else {
						ItemStack leggings = player.getInventory().getLeggings();
						if (leggings != null) {
							player.getInventory().addItem(item);
						} else {
							player.getInventory().setLeggings(item);
						}
					}
				} else if (item.getType().name().toUpperCase().contains("_HELMET")) {
					if (item.isSimilar(new ItemStack(Material.DIAMOND_HELMET))) {
						ItemStack helmet = player.getInventory().getHelmet();
						if (helmet != null) {
							player.getInventory().addItem(item);
						} else {
							player.getInventory().setHelmet(item);
						}
					} else if (item.isSimilar(new ItemStack(Material.IRON_HELMET))) {
						ItemStack helmet = player.getInventory().getHelmet();
						if (helmet != null) {
							player.getInventory().addItem(item);
						} else {
							player.getInventory().setHelmet(item);
						}
					} else if (item.isSimilar(new ItemStack(Material.GOLD_HELMET))) {
						ItemStack helmet = player.getInventory().getHelmet();
						if (helmet != null) {
							player.getInventory().addItem(item);
						} else {
							player.getInventory().setHelmet(item);
						}
					} else if (item.isSimilar(new ItemStack(Material.CHAINMAIL_HELMET))) {
						ItemStack helmet = player.getInventory().getHelmet();
						if (helmet != null) {
							player.getInventory().addItem(item);
						} else {
							player.getInventory().setHelmet(item);
						}
					} else {
						ItemStack helmet = player.getInventory().getHelmet();
						if (helmet != null) {
							player.getInventory().addItem(item);
						} else {
							player.getInventory().setHelmet(item);
						}
					}
				} else if (item.getType().name().toUpperCase().contains("_BOOTS")) {
					if (item.isSimilar(new ItemStack(Material.DIAMOND_BOOTS))) {
						ItemStack boots = player.getInventory().getBoots();
						if (boots != null) {
							player.getInventory().addItem(item);
						} else {
							player.getInventory().setBoots(item);
						}
					} else if (item.isSimilar(new ItemStack(Material.IRON_BOOTS))) {
						ItemStack boots = player.getInventory().getBoots();
						if (boots != null) {
							player.getInventory().addItem(item);
						} else {
							player.getInventory().setBoots(item);
						}
					} else if (item.isSimilar(new ItemStack(Material.GOLD_BOOTS))) {
						ItemStack boots = player.getInventory().getBoots();
						if (boots != null) {
							player.getInventory().addItem(item);
						} else {
							player.getInventory().setBoots(item);
						}
					} else if (item.isSimilar(new ItemStack(Material.CHAINMAIL_BOOTS))) {
						ItemStack boots = player.getInventory().getBoots();
						if (boots != null) {
							player.getInventory().addItem(item);
						} else {
							player.getInventory().setBoots(item);
						}
					} else {
						ItemStack boots = player.getInventory().getBoots();
						if (boots != null) {
							player.getInventory().addItem(item);
						} else {
							player.getInventory().setBoots(item);
						}
					}
				} else {
					player.getInventory().addItem(item);
				}
			} else {
				player.getInventory().addItem(item);
			}
		}
	}

}
