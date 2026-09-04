package com.retroimmersive.ultimatefantasy.item;

import java.util.function.Supplier;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import com.retroimmersive.ultimatefantasy.RetroImmersiveUltimateFantasy;

/** Pestaña creativa "Retro-Immersive Ultimate Fantasy". */
public final class ModItemGroups {

	private ModItemGroups() {}

	public static final RegistryKey<ItemGroup> MAIN = RegistryKey.of(
			RegistryKeys.ITEM_GROUP,
			Identifier.of(RetroImmersiveUltimateFantasy.MOD_ID, "main"));

	public static void register(Supplier<ItemStack> icon) {
		Registry.register(Registries.ITEM_GROUP, MAIN, FabricItemGroup.builder()
				.icon(icon::get)
				.displayName(Text.translatable("itemGroup.retroimmersiveultimatefantasy.main"))
				.build());
	}
}
