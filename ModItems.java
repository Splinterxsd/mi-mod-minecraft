package com.retroimmersive.ultimatefantasy.registry;

import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.entity.EquipmentType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

import com.retroimmersive.ultimatefantasy.RetroImmersiveUltimateFantasy;
import com.retroimmersive.ultimatefantasy.item.ModArmorMaterials;
import com.retroimmersive.ultimatefantasy.item.ModItemGroups;

/**
 * Registro de ítems — Sección 2 (BTA), primera entrega:
 * materiales base (Azufre, Carbón de Acero, Lingote de Acero) y las 3
 * armaduras completas (Acero, Cuero Reforzado, Cota de Malla — 4 piezas c/u).
 * <p>
 * Pendiente para la próxima entrega de la Sección 2: Cañón Portátil (Bazuca),
 * Carcaj, Flechas de Fuego — ver {@link ModBlocks} para el Alto Horno de
 * Fusión / Trommel, que van junto con esos ítems.
 */
public final class ModItems {
	private static final Logger LOGGER = LoggerFactory.getLogger("RetroImmersiveUltimateFantasy/Items");
	private static final String MOD_ID = RetroImmersiveUltimateFantasy.MOD_ID;

	private ModItems() {}

	// --- Materiales base ---
	public static final Item AZUFRE = register("azufre", Item::new, new Item.Settings());
	public static final Item CARBON_DE_ACERO = register("carbon_de_acero", Item::new, new Item.Settings());
	public static final Item LINGOTE_DE_ACERO = register("lingote_de_acero", Item::new, new Item.Settings());

	// --- Armadura de Acero ---
	public static final Item ACERO_CASCO = registerArmor("acero_casco", ModArmorMaterials.ACERO, EquipmentType.HELMET);
	public static final Item ACERO_PECHERA = registerArmor("acero_pechera", ModArmorMaterials.ACERO, EquipmentType.CHESTPLATE);
	public static final Item ACERO_PANTALONES = registerArmor("acero_pantalones", ModArmorMaterials.ACERO, EquipmentType.LEGGINGS);
	public static final Item ACERO_BOTAS = registerArmor("acero_botas", ModArmorMaterials.ACERO, EquipmentType.BOOTS);

	// --- Armadura de Cuero Reforzado (resistencia a caída) ---
	public static final Item CUERO_REFORZADO_CASCO = registerArmor("cuero_reforzado_casco", ModArmorMaterials.CUERO_REFORZADO, EquipmentType.HELMET);
	public static final Item CUERO_REFORZADO_PECHERA = registerArmor("cuero_reforzado_pechera", ModArmorMaterials.CUERO_REFORZADO, EquipmentType.CHESTPLATE);
	public static final Item CUERO_REFORZADO_PANTALONES = registerArmor("cuero_reforzado_pantalones", ModArmorMaterials.CUERO_REFORZADO, EquipmentType.LEGGINGS);
	public static final Item CUERO_REFORZADO_BOTAS = registerArmor("cuero_reforzado_botas", ModArmorMaterials.CUERO_REFORZADO, EquipmentType.BOOTS);

	// --- Armadura de Cota de Malla (resistencia a proyectiles) ---
	public static final Item COTA_DE_MALLA_CASCO = registerArmor("cota_de_malla_casco", ModArmorMaterials.COTA_DE_MALLA, EquipmentType.HELMET);
	public static final Item COTA_DE_MALLA_PECHERA = registerArmor("cota_de_malla_pechera", ModArmorMaterials.COTA_DE_MALLA, EquipmentType.CHESTPLATE);
	public static final Item COTA_DE_MALLA_PANTALONES = registerArmor("cota_de_malla_pantalones", ModArmorMaterials.COTA_DE_MALLA, EquipmentType.LEGGINGS);
	public static final Item COTA_DE_MALLA_BOTAS = registerArmor("cota_de_malla_botas", ModArmorMaterials.COTA_DE_MALLA, EquipmentType.BOOTS);

	public static void register() {
		LOGGER.info("Registrando ítems (materiales base + armaduras de Acero/Cuero Reforzado/Cota de Malla)...");

		ModItemGroups.register(() -> new ItemStack(ACERO_PECHERA));

		ItemGroupEvents.modifyEntriesEvent(ModItemGroups.MAIN).register(entries -> {
			entries.add(AZUFRE);
			entries.add(CARBON_DE_ACERO);
			entries.add(LINGOTE_DE_ACERO);

			entries.add(ACERO_CASCO);
			entries.add(ACERO_PECHERA);
			entries.add(ACERO_PANTALONES);
			entries.add(ACERO_BOTAS);

			entries.add(CUERO_REFORZADO_CASCO);
			entries.add(CUERO_REFORZADO_PECHERA);
			entries.add(CUERO_REFORZADO_PANTALONES);
			entries.add(CUERO_REFORZADO_BOTAS);

			entries.add(COTA_DE_MALLA_CASCO);
			entries.add(COTA_DE_MALLA_PECHERA);
			entries.add(COTA_DE_MALLA_PANTALONES);
			entries.add(COTA_DE_MALLA_BOTAS);
		});
	}

	private static Item register(String name, Function<Item.Settings, Item> factory, Item.Settings settings) {
		RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(MOD_ID, name));
		Item item = factory.apply(settings.registryKey(key));
		return Registry.register(Registries.ITEM, key, item);
	}

	private static Item registerArmor(String name, RegistryKey<ArmorMaterial> material, EquipmentType type) {
		return register(name, settings -> new ArmorItem(
				RegistryEntry.of(materialFor(material)),
				type,
				settings), new Item.Settings());
	}

	// Puente temporal: ArmorMaterial se resuelve como RegistryEntry directo
	// (no dinámico) porque acá los tres materiales se construyen en código,
	// no vía datapack. Si Loom pide que estén en el DynamicRegistryManager,
	// hay que moverlos a data/.../worldgen/armor_material/*.json en la
	// próxima pasada — queda anotado para la entrega de mecánicas.
	private static ArmorMaterial materialFor(RegistryKey<ArmorMaterial> key) {
		if (key == ModArmorMaterials.ACERO) return ModArmorMaterials.buildAcero();
		if (key == ModArmorMaterials.CUERO_REFORZADO) return ModArmorMaterials.buildCueroReforzado();
		if (key == ModArmorMaterials.COTA_DE_MALLA) return ModArmorMaterials.buildCotaDeMalla();
		throw new IllegalArgumentException("Material de armadura desconocido: " + key);
	}
}
