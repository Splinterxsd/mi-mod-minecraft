package com.retroimmersive.ultimatefantasy.registry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Registro de biomas. Cubre la Sección 1:
 * Bosque Encantado, Bosque de Otoño Eterno, Praderas Fantásticas,
 * Pantano de las Brujas, Tundra de los Titanes.
 */
public final class ModBiomes {
	private static final Logger LOGGER = LoggerFactory.getLogger("RetroImmersiveUltimateFantasy/Biomes");

	private ModBiomes() {}

	public static void register() {
		LOGGER.info("Registrando biomas (Bosque Encantado, Otoño Eterno, Praderas Fantásticas, Pantano de las Brujas, Tundra de los Titanes)...");
		// TODO: registrar cada Biome vía RegistryKey<Biome> + datapack de worldgen
		// (los biomas de Fabric 1.21.x se definen mayormente por datapack/JSON,
		// no solo por código — ver src/main/resources/data/.../worldgen/biome/)
	}
}
