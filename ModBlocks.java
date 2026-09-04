package com.retroimmersive.ultimatefantasy.registry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Registro de bloques. Cubre, entre otros:
 * - Sección 2 (BTA): Alto Horno de Fusión, Trommel.
 * - Sección 6: fluido de Ácido, bloques de Bioma de Cristales.
 * <p>
 * Stub: define el punto de entrada de registro; los bloques concretos se
 * agregan incrementalmente sin tocar el resto del mod.
 */
public final class ModBlocks {
	private static final Logger LOGGER = LoggerFactory.getLogger("RetroImmersiveUltimateFantasy/Blocks");

	private ModBlocks() {}

	public static void register() {
		LOGGER.info("Registrando bloques (Alto Horno de Fusión, Trommel, Ácido, Bioma de Cristales)...");
		// TODO: registrar cada Block vía Registry.register(Registries.BLOCK, ...)
	}
}
