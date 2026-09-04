package com.retroimmersive.ultimatefantasy.registry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Registro de entidades. Cubre, entre otros:
 * - Sección 3: fauna real (ciervos, osos, tiburones, ballenas, mariposas, etc.).
 * - Sección 4: hadas, ninfas, pixies, unicornios, pegasos, grifos, fénix,
 *   dragones de bosque/montaña, ents, goblins, golems de musgo.
 * - Sección 7: NPCs con rol (guardias, agricultores, arqueros élficos,
 *   arcanias, gobernadores).
 */
public final class ModEntities {
	private static final Logger LOGGER = LoggerFactory.getLogger("RetroImmersiveUltimateFantasy/Entities");

	private ModEntities() {}

	public static void register() {
		LOGGER.info("Registrando entidades (fauna real, criaturas míticas, NPCs con rol)...");
		// TODO: registrar cada EntityType vía Registry.register(Registries.ENTITY_TYPE, ...)
	}
}
