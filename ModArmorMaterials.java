package com.retroimmersive.ultimatefantasy.item;

import java.util.EnumMap;
import java.util.Map;

import net.minecraft.component.type.EquipmentAssetKeys;
import net.minecraft.entity.EquipmentType;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

import com.retroimmersive.ultimatefantasy.RetroImmersiveUltimateFantasy;

/**
 * Materiales de armadura de la Sección 2 (BTA):
 * - ACERO: punto medio entre hierro y diamante, alta resistencia a explosiones.
 * - CUERO_REFORZADO: alta resistencia a daño por caída.
 * - COTA_DE_MALLA: alta resistencia a proyectiles.
 * <p>
 * NOTA DE COMPATIBILIDAD: la API de {@code ArmorMaterial} cambió varias veces
 * entre 1.20.5 y 1.21.x (paso a EquipmentAsset + componentes de equipo). El
 * patrón de abajo sigue la forma vigente para 1.21.2, pero si Loom marca
 * error de firma acá, es el primer lugar a revisar contra los mappings de
 * Yarn actuales (probablemente solo cambien nombres de método, no la lógica).
 */
public final class ModArmorMaterials {

	private ModArmorMaterials() {}

	public static final RegistryKey<ArmorMaterial> ACERO = key("acero");
	public static final RegistryKey<ArmorMaterial> CUERO_REFORZADO = key("cuero_reforzado");
	public static final RegistryKey<ArmorMaterial> COTA_DE_MALLA = key("cota_de_malla");

	private static RegistryKey<ArmorMaterial> key(String name) {
		return RegistryKey.of(RegistryKeys.ARMOR_MATERIAL,
				Identifier.of(RetroImmersiveUltimateFantasy.MOD_ID, name));
	}

	/** Defensa por pieza: BOOTS, LEGGINGS, CHESTPLATE, HELMET (y BODY p/ mobs, no usado acá). */
	private static Map<EquipmentType, Integer> defense(int boots, int leggings, int chestplate, int helmet) {
		Map<EquipmentType, Integer> map = new EnumMap<>(EquipmentType.class);
		map.put(EquipmentType.BOOTS, boots);
		map.put(EquipmentType.LEGGINGS, leggings);
		map.put(EquipmentType.CHESTPLATE, chestplate);
		map.put(EquipmentType.HELMET, helmet);
		return map;
	}

	public static ArmorMaterial buildAcero() {
		// Punto medio hierro (2/5/6/2) - diamante (3/6/8/3): defensa entre ambos.
		return new ArmorMaterial(
				18, // durabilidad base (multiplicador por pieza), similar a hierro/diamante
				defense(3, 5, 7, 3),
				10, // encantabilidad
				SoundEvents.ITEM_ARMOR_EQUIP_IRON, // TODO: sonido custom de equipar acero
				2.5f, // toughness (resistencia extra a explosiones, por encima de diamante)
				0.1f, // knockback resistance
				ItemTags.REPAIRS_IRON_ARMOR, // TODO: tag custom "repairs_acero_armor" con lingote de acero
				EquipmentAssetKeys.IRON // TODO: asset propio para el modelo de acero
		);
	}

	public static ArmorMaterial buildCueroReforzado() {
		return new ArmorMaterial(
				12,
				defense(2, 3, 5, 1),
				9,
				SoundEvents.ITEM_ARMOR_EQUIP_LEATHER,
				0.0f,
				0.0f,
				ItemTags.REPAIRS_LEATHER_ARMOR,
				EquipmentAssetKeys.LEATHER
				// Nota: la resistencia a daño de caída ("alta resistencia a daños
				// por caída") no es un campo de ArmorMaterial — se implementa con
				// un mixin/callback en LivingEntity#handleFallDamage que detecte
				// esta armadura equipada. Queda para la entrega de mecánicas.
		);
	}

	public static ArmorMaterial buildCotaDeMalla() {
		return new ArmorMaterial(
				15,
				defense(2, 4, 5, 2),
				9,
				SoundEvents.ITEM_ARMOR_EQUIP_CHAIN,
				1.0f,
				0.05f,
				ItemTags.REPAIRS_IRON_ARMOR, // TODO: tag custom (se repara con hierro por ahora)
				EquipmentAssetKeys.CHAIN
				// Nota: "alta resistencia a proyectiles" tampoco es un campo nativo —
				// se implementa reduciendo el DamageSource de tipo proyectil vía
				// mixin en LivingEntity#modifyAppliedDamage. Queda para la entrega
				// de mecánicas junto con el resto del combate.
		);
	}
}
