package com.retroimmersive.ultimatefantasy.registry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Sistema social avanzado. Cubre la Sección 8:
 * Escala de Afinidad (0-1000), sistema de doble pareja multirracial
 * (máximo 2 cónyuges de especies distintas), habilidades pasivas por
 * cónyuge activo, y descendencia con rasgos híbridos.
 */
public final class ModSocialSystem {
	private static final Logger LOGGER = LoggerFactory.getLogger("RetroImmersiveUltimateFantasy/Social");

	private ModSocialSystem() {}

	public static void register() {
		LOGGER.info("Registrando sistema social (afinidad, vínculo doble, descendencia)...");
		// TODO: componente de datos persistente por jugador (Cardinal Components
		// o attachments nativos 1.21.2) para afinidad, cónyuges y descendencia.
	}
}
