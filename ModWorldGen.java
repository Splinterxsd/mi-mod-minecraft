package com.retroimmersive.ultimatefantasy.registry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Generación de mundo. Cubre la Sección 6:
 * Nether renovado (Bioma de Ácido, Bioma de Cristales) y restauración de
 * las Far Lands a ±12.550.821 bloques del spawn.
 */
public final class ModWorldGen {
	private static final Logger LOGGER = LoggerFactory.getLogger("RetroImmersiveUltimateFantasy/WorldGen");

	private ModWorldGen() {}

	public static void register() {
		LOGGER.info("Registrando generación de mundo (Nether renovado, Far Lands)...");
		// TODO: ChunkGenerator / DensityFunction custom para terreno monumental
		// y para la distorsión de Far Lands a distancia extrema del spawn.
	}
}
