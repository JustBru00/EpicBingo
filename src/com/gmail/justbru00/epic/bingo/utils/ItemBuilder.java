package com.gmail.justbru00.epic.bingo.utils;

import java.util.Arrays;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class ItemBuilder {

	private ItemStack item;
	private ItemMeta itemMeta;
	private LeatherArmorMeta leatherMeta;
	
	/**
	 * Creates a new {@link ItemBuilder} object
	 * @param m The material the item should be.
	 */
	public ItemBuilder(Material m) {
		item = new ItemStack(m);
		itemMeta = item.getItemMeta();
		if (m.toString().toLowerCase().contains("leather")) {
			leatherMeta = (LeatherArmorMeta) item.getItemMeta();
		}
	}
	
	public ItemBuilder setAmount(int i) {
		item.setAmount(i);
		return this;
	}
	
	public ItemBuilder setGlowing(boolean value) {
		if (value) {
			if (item.getType().equals(Material.FISHING_ROD)) {
				item.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 4341);
				itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
				return this;
			} else {
				item.addUnsafeEnchantment(Enchantment.LURE, 4341);
				itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
				return this;
			}
		} else {
			// Remove Glowing
			if (item.getType().equals(Material.FISHING_ROD)) {
				item.removeEnchantment(Enchantment.ARROW_INFINITE);
				itemMeta.removeItemFlags(ItemFlag.HIDE_ENCHANTS);
				return this;
			} else {
				item.removeEnchantment(Enchantment.LURE);
				itemMeta.removeItemFlags(ItemFlag.HIDE_ENCHANTS);
				return this;
			}
		}
	}
	
	public ItemBuilder setUnbreakable(boolean value) {
		if (item.getType().toString().toLowerCase().contains("leather")) {
			leatherMeta.setUnbreakable(value);
		} else {
			itemMeta.setUnbreakable(value);
		}
		return this;
	}
	
	public ItemBuilder setName(String name) {
		if (item.getType().toString().toLowerCase().contains("leather")) {
			leatherMeta.setDisplayName(Messager.color(name));
		} else {
			itemMeta.setDisplayName(Messager.color(name));
		}
		return this;
	}
	
	public ItemBuilder setLeatherArmorColor(Color c) {
		if (item.getType().toString().toLowerCase().contains("leather")) {
			leatherMeta.setColor(c);
		} 
		
		return this;
	}
	
	public ItemBuilder setFirstLoreLine(String lore) {
		if (item.getType().toString().toLowerCase().contains("leather")) {
			leatherMeta.setLore(Arrays.asList(Messager.color(lore)));
		} else {
			itemMeta.setLore(Arrays.asList(Messager.color(lore)));
		}
		return this;
	}
	
	public ItemStack build() {
		
		if (item.getType().toString().toLowerCase().contains("leather")) {
			item.setItemMeta(leatherMeta);
		} else {
			item.setItemMeta(itemMeta);
		}
		
		return item;
	}
	
}
